/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.SuperSighting.dao;

import com.mthree.SuperSighting.dto.Organization;
import java.util.List;

/**
 *
 * @author Josef
 */
public interface OrgDao {
    Organization getOrgById(int id);
    List<Organization> getAllOrgs();
    Organization addOrg(Organization org);
    void updateOrg(Organization org);
    void deleteOrgById(int id);
}
