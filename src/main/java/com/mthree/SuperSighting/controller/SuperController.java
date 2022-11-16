/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.SuperSighting.controller;

import com.mthree.SuperSighting.dao.SuperDao;
import com.mthree.SuperSighting.dao.SuperPowerDao;
import com.mthree.SuperSighting.dto.Super;
import com.mthree.SuperSighting.dto.SuperPower;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Josef
 */
@Controller
public class SuperController {
    @Autowired
    SuperDao superDao;
    
    @Autowired
    SuperPowerDao superPowerDao;
    
    @GetMapping("supers")
    public String displaySupers(Model model) {
        List<Super> supers = superDao.getAllSupers();
        List<SuperPower> superPowers = superPowerDao.getAllSuperPowers();
        for(Super sup : supers)
        {
            String power = superPowerDao.getSuperPowerById(sup.getPowerId()).getPower();
            sup.setPower(power);
        }
        model.addAttribute("supers", supers);
        model.addAttribute("superpowers", superPowers);
        return "supers";
    }
    
    @PostMapping("/addSuper")
    public String addSuper(HttpServletRequest request) {
        String name = request.getParameter("name");
        String desc = request.getParameter("description");
        int power_id = Integer.parseInt(request.getParameter("power"));
        
        Super sup = new Super();
        sup.setName(name);
        sup.setDesc(desc);
        sup.setPowerId(power_id);
        
        superDao.addSuper(sup);
        
        return "redirect:/supers";
    }
    
    @GetMapping("/editSuper")
    public String editSuper(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("id"));
        Super sup = superDao.getSuperById(id);
        List<SuperPower> superpowers = superPowerDao.getAllSuperPowers();
        
        model.addAttribute("super", sup);
        model.addAttribute("superpowers", superpowers);
        return "/editSuper";
    }
    
    @PostMapping("editSuper")
    public String performEditSuper(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        Super sup = superDao.getSuperById(id);
        
        sup.setName(request.getParameter("name"));
        sup.setDesc(request.getParameter("description"));
        sup.setPowerId(Integer.parseInt(request.getParameter("power_id")));
            
        superDao.updateSuper(sup);
        
        return "redirect:/supers";
    }
    
    @GetMapping("/deleteSuper")
    public String deleteSuper(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        superDao.deleteSuperById(id);
        
        return "redirect:/supers";
    }

}
