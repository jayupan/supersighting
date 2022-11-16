/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.SuperSighting.dao;

import com.mthree.SuperSighting.dao.LocationDaoDB.LocationMapper;
import com.mthree.SuperSighting.dao.SuperDaoDB.SuperMapper;
import com.mthree.SuperSighting.dto.Location;
import com.mthree.SuperSighting.dto.Organization;
import com.mthree.SuperSighting.dto.Sighting;
import com.mthree.SuperSighting.dto.Super;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class SightingDaoDB implements SightingDao {
    
    @Autowired
    JdbcTemplate jdbc;

    @Override
    public List<Sighting> getAllSightings() {
        final String GET_ALL_SIGHTINGS = "SELECT * FROM Sighting";
        return jdbc.query(GET_ALL_SIGHTINGS, new SightMapper());
    }

    @Override
    @Transactional
    public Sighting addSighting(Sighting sight) {
        final String INSERT_SIGHTING = "INSERT INTO Sighting (datetime, loc_id, super_id) " + 
                "VALUES(?,?,?)";
        jdbc.update(INSERT_SIGHTING, sight.getDateTime(), sight.getLoc(), sight.getSup());
        return sight;
    }

    @Override
    public List<Super> getSupersByLoc(Location loc) {
        final String GET_SUPERS_BY_LOC = "SELECT s.* FROM Super s " +
                "JOIN Sighting sight ON sight.loc_id = s.super_id WHERE sight.loc_id = ?";
        return jdbc.query(GET_SUPERS_BY_LOC, new SuperMapper(), loc.getId());
    }

    @Override
    public List<Location> getLocsBySuper(Super sup) {
        final String GET_LOCS_BY_SUPER = "SELECT l.* FROM Location l " +
                "JOIN Sighting sight ON sight.super_id = l.loc_id WHERE sight.super_id = ?";
        return jdbc.query(GET_LOCS_BY_SUPER, new LocationMapper(), sup.getId());
    }

    @Override
    public List<Sighting> getSightingByDate(String dateTime) {
        final String GET_SIGHTING_BY_DATE = "SELECT * FROM Sighting WHERE dateTime = ?";
    
        return jdbc.query(GET_SIGHTING_BY_DATE, new SightMapper(), dateTime);
    }

    @Override
    public void deleteSightingBySuper(Super sup) {
        final String DELETE_SIGHT_BY_SUPER = "DELETE FROM Sighting WHERE super_id = ?";
        jdbc.update(DELETE_SIGHT_BY_SUPER, sup.getId());
    }

    @Override
    public void deleteSightingByLoc(Location loc) {
        final String DELETE_SIGHT_BY_LOCATION = "DELETE FROM Sighting WHERE loc_id = ?";
        jdbc.update(DELETE_SIGHT_BY_LOCATION, loc.getId());
    }
    
    public static final class SightMapper implements RowMapper<Sighting>
    {
        @Override
        public Sighting mapRow(ResultSet rs, int index) throws SQLException 
        {
            Sighting sight = new Sighting();
            sight.setDateTime(rs.getString("datetime"));
            sight.setLoc(Integer.parseInt(rs.getString("loc_id")));
            sight.setSup(Integer.parseInt(rs.getString("super_id")));
            return sight;
        }
    }
}
