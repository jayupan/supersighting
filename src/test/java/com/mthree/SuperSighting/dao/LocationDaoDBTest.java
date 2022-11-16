/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.SuperSighting.dao;

import com.mthree.SuperSighting.dto.Location;
import com.mthree.SuperSighting.dto.Sighting;
import com.mthree.SuperSighting.dto.Super;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author Josef
 */
@SpringBootTest
public class LocationDaoDBTest {
    
    @Autowired
    LocationDao locationDao;
    
    @Autowired 
    SuperDao superDao;
    
    @Autowired
    SightingDao sightDao;
    
    public LocationDaoDBTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        List<Location> locations = locationDao.getAllLocations();
        for(Location location : locations)
        {
            locationDao.deleteLocationById(location.getId());
            sightDao.deleteSightingByLoc(location);
        }
        List<Super> supers = superDao.getAllSupers();
        for(Super sup : supers)
        {
            superDao.deleteSuperById(sup.getId());
            sightDao.deleteSightingBySuper(sup);
        }
    }
    
    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testAddAndGetLocation()
    {
        Location location = new Location();
        location.setName("Fortress of Solitude");
        location.setDesc("A large, empty ice structure.");
        location.setAddress("Somewhere in the Arctic.");
        location.setCoord("???");
        
        location = locationDao.addLocation(location);
        
        Location fromDao = locationDao.getLocationById(location.getId());
        
        assertEquals(location, fromDao);
    }
    
    
    /**
     * Test of getLocationById method, of class LocationDaoDB.
     */
    @Test
    public void testGetLocationById() {
    }

    /**
     * Test of getAllLocations method, of class LocationDaoDB.
     */
    @Test
    public void testGetAllLocations() {
        
    }

    /**
     * Test of addLocation method, of class LocationDaoDB.
     */
    @Test
    public void testAddLocation() {
        Location location = new Location();
        location.setName("Fortress of Solitude");
        location.setDesc("A large, empty ice structure.");
        location.setAddress("Somewhere in the Arctic.");
        location.setCoord("???");
        
        location = locationDao.addLocation(location);
        
        Location location2 = new Location();
        location2.setName("Test");
        location2.setDesc("Test");
        location2.setAddress("Test");
        location2.setCoord("Test");
        
        location2 = locationDao.addLocation(location2);
        
        List<Location>locations = locationDao.getAllLocations();
        
        assertEquals(2, locations.size());
        assertTrue(locations.contains(location));
        assertTrue(locations.contains(location2));
    }

    /**
     * Test of updateLocation method, of class LocationDaoDB.
     */
    @Test
    public void testUpdateLocation() {
        Location location = new Location();
        location.setName("Fortress of Solitude");
        location.setDesc("A large, empty ice structure.");
        location.setAddress("Somewhere in the Arctic.");
        location.setCoord("???");
        
        location = locationDao.addLocation(location);
        
        Location fromDao = locationDao.getLocationById(location.getId());
        assertEquals(location, fromDao);
        
        location.setName("New Test First");
        locationDao.updateLocation(location);
        
        assertNotEquals(location, fromDao);
        
        fromDao = locationDao.getLocationById(location.getId());
        
        assertEquals(location, fromDao);
    }

    /**
     * Test of deleteLocationById method, of class LocationDaoDB.
     */
    @Test
    public void testDeleteLocationById() throws ParseException {
        Location location = new Location();
        location.setName("Fortress of Solitude");
        location.setDesc("A large, empty ice structure.");
        location.setAddress("Somewhere in the Arctic.");
        location.setCoord("???");
        location = locationDao.addLocation(location);
        
        Super sup = new Super();
        sup.setName("Test");
        sup.setDesc("Test");
        sup.setPower("Test");
        sup = superDao.addSuper(sup);
        List<Super> supers = new ArrayList<>();
        supers.add(sup);
        
        Sighting sight = new Sighting();
        Date date = new Date();
        String ld = date.toString();
        sight.setDateTime(ld);
        //sight.setLoc(location);
        //sight.setSup(sup);
        sight = sightDao.addSighting(sight);
        
        Location fromDao = locationDao.getLocationById(location.getId());
        assertEquals(location, fromDao);
        
        locationDao.deleteLocationById(location.getId());
        
        fromDao = locationDao.getLocationById(location.getId());
        assertNull(fromDao);
    }
    
}