/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.SuperSighting.controller;

import com.mthree.SuperSighting.dao.LocationDao;
import com.mthree.SuperSighting.dao.SightingDao;
import com.mthree.SuperSighting.dao.SuperDao;
import com.mthree.SuperSighting.dto.Location;
import com.mthree.SuperSighting.dto.Sighting;
import com.mthree.SuperSighting.dto.Super;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Josef
 */
@Controller
public class IndexController {
    @Autowired
    SightingDao sightingDao;
    
    @Autowired
    LocationDao locDao;
    
    @Autowired
    SuperDao superDao;
    
    @GetMapping("/")
    public String displayIndex(Model model)
    {
        List<Sighting> sightings = sightingDao.getAllSightings();
        List<Super> supers = superDao.getAllSupers();
        List<Location> locations = locDao.getAllLocations();
        for(Sighting sight : sightings)
        {
            String superName = (superDao.getSuperById(sight.getSup())).getName();
            sight.setSuperName(superName);
            String locName = (locDao.getLocationById(sight.getLoc())).getName();
            sight.setLocationName(locName);
        }
        model.addAttribute("sightings", sightings);
        model.addAttribute("supers", supers);
        model.addAttribute("locations", locations);
        return "index";
    }
}
