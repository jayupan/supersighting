/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.SuperSighting.dao;

import com.mthree.SuperSighting.dto.Location;
import com.mthree.SuperSighting.dto.Sighting;
import com.mthree.SuperSighting.dto.Super;
import java.util.List;

/**
 *
 * @author Josef
 */
public interface SightingDao {
    
    List<Sighting> getAllSightings();
    Sighting addSighting(Sighting sight);
    
    void deleteSightingBySuper(Super sup);
    void deleteSightingByLoc(Location loc);
    
    List<Super> getSupersByLoc(Location loc);
    List<Location> getLocsBySuper(Super sup);
    List<Sighting> getSightingByDate(String dateTime);
}
