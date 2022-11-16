/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.SuperSighting.controller;

import com.mthree.SuperSighting.dao.LocationDao;
import com.mthree.SuperSighting.dto.Location;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Josef
 */
@Controller
public class LocationController {
    
    @Autowired
    LocationDao locationDao;
    
    @GetMapping("locations")
    public String displayLocations(Model model) {
        List<Location> locations = locationDao.getAllLocations();
        model.addAttribute("locations", locations);
        return "locations";
    }
    
    
    @PostMapping("/addLocation")
    public String addLocation(HttpServletRequest request) {
        String name = request.getParameter("name");
        String desc = request.getParameter("description");
        String address = request.getParameter("address");
        String coord = request.getParameter("coordinates");
        
        Location location = new Location();
        location.setName(name);
        location.setDesc(desc);
        location.setAddress(address);
        location.setCoord(coord);
        //System.out.println("test");
        //System.out.println(location.getName());
        //System.out.println(location.getDesc());
        //System.out.println(location.getAddress());
        //System.out.println(location.getCoord());
        
        locationDao.addLocation(location);
        
        return "redirect:/locations";
    }
    @GetMapping("/deleteLocation")
    public String deleteLocation(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        locationDao.deleteLocationById(id);
        
        return "redirect:/locations";
    }
    
    @GetMapping("/editLocation")
    public String editLocation(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("id"));
        Location location = locationDao.getLocationById(id);
        
        model.addAttribute("location", location);
        return "/editLocation";
    }
    
        @PostMapping("editLocation")
    public String performEditLocation(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        Location location = locationDao.getLocationById(id);
        
        location.setName(request.getParameter("name"));
        location.setDesc(request.getParameter("description"));
        location.setAddress(request.getParameter("address"));
        location.setCoord(request.getParameter("coordinates"));
            
        
        locationDao.updateLocation(location);
        
        return "redirect:/locations";
    }


}
