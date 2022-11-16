/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.SuperSighting.dao;

import com.mthree.SuperSighting.dao.OrgDaoDB.OrgMapper;
import com.mthree.SuperSighting.dao.SuperDaoDB.SuperMapper;
import com.mthree.SuperSighting.dto.Member;
import com.mthree.SuperSighting.dto.Organization;
import com.mthree.SuperSighting.dto.Super;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Josef
 */
@Repository
public class MemberDaoDB implements MemberDao {

    @Autowired
    JdbcTemplate jdbc;
    
    @Override
    @Transactional
    public Member addMember(Member mem) {
        final String INSERT_MEMBER = "INSERT INTO Member (super_id, org_id) " + 
                "VALUES(?,?)";
        jdbc.update(INSERT_MEMBER, mem.getSuper().getId(), mem.getOrg().getId());
        return mem;
    }

    @Override
    public List<Member> getAllMembers() {
        final String GET_ALL_MEMBERS = "SELECT * FROM Member";
        return jdbc.query(GET_ALL_MEMBERS, new MemberMapper());
    }
    
    @Override
    public void deleteMemberByOrg(Organization org) {
        final String DELETE_MEMBER_BY_ORG = "DELETE FROM Member WHERE org_id = ?";
        jdbc.update(DELETE_MEMBER_BY_ORG, org.getId());
    }
    
    @Override
    public void deleteMemberBySuper(Super sup) {
        final String DELETE_MEMBER_BY_SUPER = "DELETE FROM Member WHERE super_id = ?";
        jdbc.update(DELETE_MEMBER_BY_SUPER, sup.getId());
    }

    @Override
    public List<Super> getSupersByOrg(Organization org) {
        final String GET_SUPERS_BY_ORG = "SELECT s.* FROM Super s "
                + "JOIN Member m ON m.super_id = s.super_id WHERE m.org_id = ?";
        return jdbc.query(GET_SUPERS_BY_ORG, new SuperMapper(), org.getId());
    }

    @Override
    public List<Organization> getOrgsBySuper(Super sup) {
        final String GET_ORGS_BY_SUPER = "SELECT o.* FROM Organization o "
                + "JOIN Member m ON m.super_id WHERE m.super_id = ?";
        return jdbc.query(GET_ORGS_BY_SUPER, new OrgMapper(), sup.getId());
    }
    
    public static final class MemberMapper implements RowMapper<Member>
    {
        @Override
        public Member mapRow(ResultSet rs, int index) throws SQLException 
        {
            Member mem = new Member();
            Super sup = new Super();
            Organization org = new Organization();
            int superId = rs.getInt("super_id");
            sup.setId(superId);
            int orgId = rs.getInt("org_id");
            org.setId(orgId);
            mem.setSup(sup);
            mem.setOrg(org);
            return mem;
        }
    }
    
}
