/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.SuperSighting.controller;

import com.mthree.SuperSighting.dao.LocationDao;
import com.mthree.SuperSighting.dao.OrgDao;
import com.mthree.SuperSighting.dto.Location;
import com.mthree.SuperSighting.dto.Organization;
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
public class OrganizationController {
    
    @Autowired
    OrgDao orgDao;
    
    @GetMapping("organizations")
    public String displayOrgs(Model model) {
        List<Organization> organizations = orgDao.getAllOrgs();
        model.addAttribute("organizations", organizations);
        return "organizations";
    }
    
    
    @PostMapping("/addOrganization")
    public String addOrg(HttpServletRequest request) {
        String name = request.getParameter("name");
        String desc = request.getParameter("description");
        String address = request.getParameter("address");
        String contact = request.getParameter("contact");
        
        Organization org = new Organization();
        org.setName(name);
        org.setDesc(desc);
        org.setAddress(address);
        org.setContact(contact);
        //System.out.println("test");
        //System.out.println(location.getName());
        //System.out.println(location.getDesc());
        //System.out.println(location.getAddress());
        //System.out.println(location.getCoord());
        
        orgDao.addOrg(org);
        
        return "redirect:/organizations";
    }
    @GetMapping("/deleteOrganization")
    public String deleteOrg(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        orgDao.deleteOrgById(id);
        
        return "redirect:/organizations";
    }
    
    @GetMapping("/editOrganization")
    public String editOrg(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("id"));
        Organization org = orgDao.getOrgById(id);
        
        model.addAttribute("organization", org);
        return "/editOrganization";
    }
    
    @PostMapping("editOrganization")
    public String performEditOrg(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        Organization org = orgDao.getOrgById(id);
        
        org.setName(request.getParameter("name"));
        org.setDesc(request.getParameter("description"));
        org.setAddress(request.getParameter("address"));
        org.setContact(request.getParameter("contact"));
            
        
        orgDao.updateOrg(org);
        
        return "redirect:/organizations";
    }
}
