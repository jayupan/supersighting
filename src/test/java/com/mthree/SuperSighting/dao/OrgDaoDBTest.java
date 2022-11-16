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
public class OrgDaoDBTest {
    
    @Autowired
    OrgDao orgDao;
    
    @Autowired 
    SuperDao superDao;
    
    @Autowired
    MemberDao memberDao;
    
    public OrgDaoDBTest() {
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
     * Test of getOrgById method, of class OrgDaoDB.
     */
    @Test
    public void testGetOrgById() {
    }

    /**
     * Test of getAllOrgs method, of class OrgDaoDB.
     */
    @Test
    public void testGetAllOrgs() {
    }

    /**
     * Test of addOrg method, of class OrgDaoDB.
     */
    @Test
    public void testAddOrg() {
    }

    /**
     * Test of updateOrg method, of class OrgDaoDB.
     */
    @Test
    public void testUpdateOrg() {
    }

    /**
     * Test of deleteOrgById method, of class OrgDaoDB.
     */
    @Test
    public void testDeleteOrgById() {
    }
    
}
