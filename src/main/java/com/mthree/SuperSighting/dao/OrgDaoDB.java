/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.SuperSighting.dao;

import com.mthree.SuperSighting.dto.Organization;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Josef
 */
@Repository
public class OrgDaoDB implements OrgDao {
    
    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Organization getOrgById(int id) {
        try {
            final String GET_ORG_BY_ID = "SELECT * FROM Organization WHERE org_id = ?";
            return jdbc.queryForObject(GET_ORG_BY_ID, new OrgMapper(), id);
            
        } catch (DataAccessException ex)
        {
            return null;
        }
    }

    @Override
    public List<Organization> getAllOrgs() {
        final String GET_ALL_ORGS = "SELECT * FROM Organization";
        return jdbc.query(GET_ALL_ORGS, new OrgMapper());
    }

    @Override
    @Transactional
    public Organization addOrg(Organization org) {
        final String INSERT_ORGANIZATION = "INSERT INTO Organization (name, description, address, contact) " + 
                "VALUES(?,?,?,?)";
        jdbc.update(INSERT_ORGANIZATION, org.getName(), org.getDesc(), org.getAddress(), org.getContact());
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        org.setId(newId);
        return org;
    }

    @Override
    public void updateOrg(Organization org) {
        final String UPDATE_ORG = "UPDATE Organization SET name = ?, description = ?, address = ?, contact = ? " +
                "WHERE org_id = ?";
        jdbc.update(UPDATE_ORG, org.getName(), org.getDesc(), org.getAddress(), org.getContact(), org.getId());
    }

    @Override
    @Transactional
    public void deleteOrgById(int id) {
        final String DELETE_MEMBER = "DELETE mem.* FROM Member mem " +
                "JOIN Organization org ON mem.org_id WHERE org.org_id = ?";
        jdbc.update(DELETE_MEMBER, id);
        final String DELETE_ORG = "DELETE FROM Organization WHERE org_id = ?";
        jdbc.update(DELETE_ORG, id);
    }
    
    public static final class OrgMapper implements RowMapper<Organization>
    {
        @Override
        public Organization mapRow(ResultSet rs, int index) throws SQLException 
        {
            Organization org = new Organization();
            org.setId(rs.getInt("org_id"));
            org.setName(rs.getString("name"));
            org.setDesc(rs.getString("description"));
            org.setAddress(rs.getString("address"));
            org.setContact(rs.getString("contact"));
            return org;
        }
    }
    
}
