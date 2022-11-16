/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.SuperSighting.dao;

import com.mthree.SuperSighting.dto.Location;
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
public class LocationDaoDB implements LocationDao {
    
    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Location getLocationById(int id) {
        try {
            final String GET_LOCATION_BY_ID = "SELECT * FROM Location WHERE loc_id = ?";
            return jdbc.queryForObject(GET_LOCATION_BY_ID, new LocationMapper(), id);
            
        } catch (DataAccessException ex)
        {
            return null;
        }
    }

    @Override
    public List<Location> getAllLocations() {
        final String GET_ALL_LOCATIONS = "SELECT * FROM Location";
        return jdbc.query(GET_ALL_LOCATIONS, new LocationMapper());
    }

    @Override
    @Transactional
    public Location addLocation(Location loc) {
        final String INSERT_LOCATION = "INSERT INTO Location (name, description, address, coordinates) " + 
                "VALUES(?,?,?,?)";
        jdbc.update(INSERT_LOCATION, loc.getName(), loc.getDesc(), loc.getAddress(), loc.getCoord());
            int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        loc.setId(newId);
        //System.out.println("test");
        //System.out.println(loc.getId());
        //System.out.println(loc.getName());
        //System.out.println(loc.getDesc());
        //System.out.println(loc.getAddress());
        //System.out.println(loc.getCoord());
        return loc;
    }

    @Override
    public void updateLocation(Location loc) {
        final String UPDATE_LOCATION = "UPDATE Location SET name = ?, description = ?, address = ?, coordinates = ? " +
                "WHERE loc_id = ?";
        jdbc.update(UPDATE_LOCATION, loc.getName(), loc.getDesc(), loc.getAddress(), loc.getCoord(), loc.getId());
    }

    @Override
    @Transactional
    public void deleteLocationById(int id) {
        final String DELETE_SIGHTING = "DELETE sight.* FROM Sighting sight " +
                "JOIN Location loc ON sight.loc_id WHERE loc.loc_id = ?";
        jdbc.update(DELETE_SIGHTING, id);
        
        final String DELETE_LOCATION = "DELETE FROM Location WHERE loc_id = ?";
        jdbc.update(DELETE_LOCATION, id);
                
    }
    
    public static final class LocationMapper implements RowMapper<Location>
    {
        @Override
        public Location mapRow(ResultSet rs, int index) throws SQLException 
        {
            Location loc = new Location();
            loc.setId(rs.getInt("loc_id"));
            loc.setName(rs.getString("name"));
            loc.setDesc(rs.getString("description"));
            loc.setAddress(rs.getString("address"));
            loc.setCoord(rs.getString("coordinates"));
            return loc;
        }
    }
    
}
