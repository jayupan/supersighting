/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.SuperSighting.dao;

import com.mthree.SuperSighting.dto.Location;
import java.util.List;

/**
 *
 * @author Josef
 */
public interface LocationDao {
    Location getLocationById(int id);
    List<Location> getAllLocations();
    Location addLocation(Location location);
    void updateLocation(Location location);
    void deleteLocationById(int id);
}
