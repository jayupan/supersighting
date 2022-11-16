/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.SuperSighting.controller;

import com.mthree.SuperSighting.dao.SuperPowerDao;
import com.mthree.SuperSighting.dto.SuperPower;
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
public class SuperPowerController {
    @Autowired
    SuperPowerDao superPowerDao;
    
    
    @GetMapping("superpowers")
    public String displaySuperPowers(Model model) {
        List<SuperPower> superpowers = superPowerDao.getAllSuperPowers();
        model.addAttribute("superpowers", superpowers);
        return "superpowers";
    }
    
    @PostMapping("/addSuperPower")
    public String addSuperPower(HttpServletRequest request) {
        String power = request.getParameter("power");
        if(power == null || power.isEmpty() || power.isBlank())
        {
            System.out.println("WRONG STRING");
        }
        else
        {
            SuperPower sup = new SuperPower();
            sup.setPower(power);
            superPowerDao.addSuperPower(sup);
        }
        
        return "redirect:/superpowers";
    }
    
    @GetMapping("/deleteSuperPower")
    public String deleteSuperPower(HttpServletRequest request) {
        String power = request.getParameter("power");
        SuperPower sup = new SuperPower();
        sup.setPower(power);
        superPowerDao.deleteSuperPower(sup);
        
        return "redirect:/superpowers";
    }
    
    @GetMapping("/editSuperPower")
    public String editSuperPower(HttpServletRequest request, Model model) {
        String power = request.getParameter("power");
        SuperPower sup= new SuperPower();
        sup.setPower(power);
        
        model.addAttribute("superpower", sup);
        return "/editSuperPower";
    }
    
    @PostMapping("editSuperPower")
    public String performEditPower(HttpServletRequest request) {
        String power1 = request.getParameter("power1");
        SuperPower sup1 = new SuperPower();
        sup1.setPower(power1);
        
        String power2 = request.getParameter("power2");
        SuperPower sup2 = new SuperPower();
        sup2.setPower(power2);
        
        superPowerDao.updateSuperPower(sup1, sup2);
        
        return "redirect:/superpowers";
    }
}
