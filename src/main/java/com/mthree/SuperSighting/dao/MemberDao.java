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

/**
 *
 * @author Josef
 */
public interface MemberDao {
    Member addMember(Member mem);
    List<Member>getAllMembers(); 
    void deleteMemberByOrg(Organization org);
    void deleteMemberBySuper(Super sup);
    
    List<Super>getSupersByOrg(Organization org);
    List<Organization>getOrgsBySuper(Super sup);
}
