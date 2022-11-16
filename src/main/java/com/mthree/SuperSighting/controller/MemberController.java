/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mthree.SuperSighting.controller;

import com.mthree.SuperSighting.dao.MemberDao;
import com.mthree.SuperSighting.dto.Member;
import com.mthree.SuperSighting.dao.OrgDao;
import com.mthree.SuperSighting.dao.SuperDao;
import com.mthree.SuperSighting.dto.Organization;
import com.mthree.SuperSighting.dto.Super;
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
public class MemberController {
    
    @Autowired
    MemberDao memberDao;
    
    @Autowired
    OrgDao orgDao;
    
    @Autowired
    SuperDao superDao; 
    
    @GetMapping("members")
    public String displayMembers(Model model)
    {
        List<Organization> organizations = orgDao.getAllOrgs();
        model.addAttribute("organizations", organizations);
        return "members";
    }
    
    @GetMapping("/viewMember")
    public String viewMembers(HttpServletRequest request, Model model)
    {
        int id = Integer.parseInt(request.getParameter("id"));
        Organization org = new Organization();
        org.setId(id);
        List<Super> supers = memberDao.getSupersByOrg(org);
        model.addAttribute("supers", supers);
        return "/viewMember";
    }
    
    @GetMapping("/addMember")
    public String addMembers(HttpServletRequest request, Model model)
    {
        int id = Integer.parseInt(request.getParameter("id"));
        Organization org = orgDao.getOrgById(id);
        model.addAttribute("org", org);
        List<Super> supers = superDao.getAllSupers();
        List<Super> supers2 = memberDao.getSupersByOrg(org);
        
        supers.removeAll(supers2);
        
        model.addAttribute("supers", supers);
        model.addAttribute("supers2", supers2);
        return "/addMember";
    }
    
    @PostMapping("/addMember")
    public String addSuper(HttpServletRequest request) {
        if(request.getParameter("id") != null && request.getParameter("super") != null)
        {
            int orgId = Integer.parseInt(request.getParameter("id"));
            int superId = Integer.parseInt(request.getParameter("super"));

            Member member = new Member();
            member.setOrg(orgDao.getOrgById(orgId));
            member.setSup(superDao.getSuperById(superId));

            memberDao.addMember(member);
        }
        
        return "redirect:/members";
    }
    
    @GetMapping("/deleteMember")
    public String deleteMember(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        Super sup = superDao.getSuperById(id);
        memberDao.deleteMemberBySuper(sup);
        
        return "redirect:/members";
    }
    
}

