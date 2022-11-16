/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.SuperSighting.dao;

import com.mthree.SuperSighting.dto.Member;
import com.mthree.SuperSighting.dto.Organization;
import com.mthree.SuperSighting.dto.Super;
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
public class MemberDaoDBTest {
    
    @Autowired
    MemberDao memberDao;
    
    @Autowired
    SuperDao superDao;
    
    @Autowired
    OrgDao orgDao;

    public MemberDaoDBTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        List<Organization> orgs = orgDao.getAllOrgs();
        for(Organization org : orgs)
        {
            orgDao.deleteOrgById(org.getId());
            memberDao.deleteMemberByOrg(org);
        }
        List<Super> supers = superDao.getAllSupers();
        for(Super sup : supers)
        {
            superDao.deleteSuperById(sup.getId());
            memberDao.deleteMemberBySuper(sup);
        }
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of addMember method, of class MemberDaoDB.
     */
    @Test
    public void testAddDeleteAndGetAllMember() {
        Organization org = new Organization();
        org.setName("Justice League");
        org.setDesc("A league of heroes");
        org.setAddress("Hall of Justice");
        org.setContact("justiceleague@gmail.com");
        org = orgDao.addOrg(org);
        
        Super sup = new Super();
        sup.setName("Test");
        sup.setDesc("Test");
        sup.setPower("Test");
        sup = superDao.addSuper(sup);
        
        Member mem = new Member();
        mem.setOrg(org);
        mem.setSup(sup);
        mem = memberDao.addMember(mem);
        List<Member> mems = memberDao.getAllMembers();
        assertEquals(mem.getSuper().getId(), mems.get(0).getSuper().getId());
        assertEquals(mem.getOrg().getId(), mems.get(0).getOrg().getId());
        
        List<Super> supers = memberDao.getSupersByOrg(org);
        assertEquals(mem.getSuper().getId(), supers.get(0).getId());
        
        List<Organization> orgs = memberDao.getOrgsBySuper(sup);
        assertEquals(mem.getOrg().getId(), orgs.get(0).getId());
        
        memberDao.deleteMemberBySuper(sup);
        memberDao.deleteMemberByOrg(org);
        
        
        List<Member> mems2 = memberDao.getAllMembers();
        
        assertNotEquals(mems.size(), mems2.size());
        
    }

    /**
     * Test of getAllMembers method, of class MemberDaoDB.
     */
    @Test
    public void testGetAllMembers() {
    }

    /**
     * Test of getSupersByOrg method, of class MemberDaoDB.
     */
    @Test
    public void testGetSupersByOrg() {
        
    }

    /**
     * Test of getOrgsBySuper method, of class MemberDaoDB.
     */
    @Test
    public void testGetOrgsBySuper() {
        
    }
    
  
    
}
