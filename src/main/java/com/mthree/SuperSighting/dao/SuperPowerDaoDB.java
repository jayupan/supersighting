/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.SuperSighting.dao;

import com.mthree.SuperSighting.dto.SuperPower;
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
public class SuperPowerDaoDB implements SuperPowerDao {
    
    @Autowired
    JdbcTemplate jdbc;

    @Override
    public SuperPower getSuperPowerById(int id)
    {
        try {
            final String GET_SUPERPOWER = "SELECT * FROM Superpower WHERE power_id = ?";
            return jdbc.queryForObject(GET_SUPERPOWER, new SuperPowerMapper(), id);
        } catch (DataAccessException ex)
        {
            return null;
        }
    }
    
    @Override
    public List<SuperPower> getAllSuperPowers() {
        final String GET_ALL_SUPERPOWERS = "SELECT * FROM SuperPower";
        return jdbc.query(GET_ALL_SUPERPOWERS, new SuperPowerMapper());
    }

    @Override
    @Transactional
    public SuperPower addSuperPower(SuperPower power) {
        final String INSERT_SUPERPOWER = "INSERT INTO SuperPower (power) " + 
                "VALUES(?)";
        jdbc.update(INSERT_SUPERPOWER, power.getPower());
        return power;   
    }

    @Override
    @Transactional
    public void deleteSuperPower(SuperPower power) {
        final String DELETE_SUPERPOWER = "DELETE FROM SuperPower WHERE power = ?";
        jdbc.update(DELETE_SUPERPOWER, power.getPower());
    }

    @Override
    public void updateSuperPower(SuperPower p1, SuperPower p2) //p1 = modifier; p2 = modified FIX LATER UPDATE SUPER POWER TO HAVE ID
    {
        final String UPDATE_SUPER = "UPDATE SuperPower SET power = ? " +
                "WHERE power = ?";
        jdbc.update(UPDATE_SUPER, p1.getPower(), p2.getPower());
    }
    
    public static final class SuperPowerMapper implements RowMapper<SuperPower>
    {
        @Override
        public SuperPower mapRow(ResultSet rs, int index) throws SQLException 
        {
            SuperPower power = new SuperPower();
            power.setId(rs.getInt("power_id"));
            power.setPower(rs.getString("power"));
            return power;
        }
    }
    
}
