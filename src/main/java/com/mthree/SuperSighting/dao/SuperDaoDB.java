/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.SuperSighting.dao;

import com.mthree.SuperSighting.dto.Super;
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
public class SuperDaoDB implements SuperDao {

    @Autowired
    JdbcTemplate jdbc;
    
    @Override
    public Super getSuperById(int id) {
        try {
            final String GET_SUPER_BY_ID = "SELECT * FROM Super WHERE super_id = ?";
            return jdbc.queryForObject(GET_SUPER_BY_ID, new SuperMapper(), id);
            
        } catch (DataAccessException ex)
        {
            return null;
        }
    }

    @Override
    public List<Super> getAllSupers() {
        final String GET_ALL_SUPERS = "SELECT * FROM Super";
        return jdbc.query(GET_ALL_SUPERS, new SuperMapper());
    }

    @Override
    @Transactional
    public Super addSuper(Super sup) {
        final String INSERT_SUPER = "INSERT INTO Super (name, description, power_id) " + 
                "VALUES(?, ?,?)";
        jdbc.update(INSERT_SUPER, sup.getName(), sup.getDesc(), sup.getPowerId());
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        sup.setId(newId);
        return sup;
    }

    @Override
    public void updateSuper(Super sup) {
        final String UPDATE_SUPER = "UPDATE Super SET name = ?, description = ?, power_id = ?" +
                "WHERE super_id = ?";
        jdbc.update(UPDATE_SUPER, sup.getName(), sup.getDesc(), sup.getPowerId(), sup.getId());
    }

    @Override
    @Transactional
    public void deleteSuperById(int id) {
        final String DELETE_SIGHTING = "DELETE sight.* FROM Sighting sight " +
                "JOIN Super sup ON sight.super_id WHERE sup.super_id = ?";
        jdbc.update(DELETE_SIGHTING, id);
        
        final String DELETE_MEMBER = "DELETE mem.* FROM Member mem " +
                "JOIN Super sup ON mem.super_id WHERE sup.super_id = ?";
        jdbc.update(DELETE_MEMBER, id);
        
        final String DELETE_SUPER = "DELETE FROM Super WHERE super_id = ?";
        jdbc.update(DELETE_SUPER, id);
    }
    
    public static final class SuperMapper implements RowMapper<Super>
    {
        @Override
        public Super mapRow(ResultSet rs, int index) throws SQLException 
        {
            Super sup = new Super();
            sup.setId(rs.getInt("super_id"));
            sup.setName(rs.getString("name"));
            sup.setDesc(rs.getString("description"));
            sup.setPowerId(Integer.parseInt(rs.getString("power_id")));
            return sup;
        }
    }
}
