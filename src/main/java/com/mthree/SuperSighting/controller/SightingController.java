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
public class SightingController {
    
    @Autowired
    SightingDao sightingDao;
    
    @Autowired
    SuperDao superDao;
    
    @Autowired
    LocationDao locDao;
    
    @GetMapping("sightings")
    public String displaySightings(Model model) {
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
        return "sightings";
    }
    
    @PostMapping("/addSighting")
    public String addSuper(HttpServletRequest request) {
        String datetime = request.getParameter("datetime");
        int supId = Integer.parseInt(request.getParameter("super"));
        int locId = Integer.parseInt(request.getParameter("location"));
        
        Sighting sight = new Sighting();
        sight.setDateTime(datetime);
        sight.setSup(supId);
        sight.setLoc(locId);
        
        sightingDao.addSighting(sight);
        
        return "redirect:/sightings";
    }
    
    @GetMapping("/editSighting")
    public String editSuper(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("id"));
        Super sup = superDao.getSuperById(id);
        
        model.addAttribute("super", sup);
        return "/editSuper";
    }
    
    @PostMapping("editSighting")
    public String performEditSuper(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        Super sup = superDao.getSuperById(id);
        
        sup.setName(request.getParameter("name"));
        sup.setDesc(request.getParameter("description"));
        sup.setPowerId(Integer.parseInt(request.getParameter("power_id")));
            
        superDao.updateSuper(sup);
        
        return "redirect:/supers";
    }
}
