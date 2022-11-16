/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.SuperSighting.dao;

import com.mthree.SuperSighting.dto.Super;
import java.util.List;

/**
 *
 * @author Josef
 */
public interface SuperDao {
    Super getSuperById(int id);
    List<Super>getAllSupers();
    Super addSuper(Super sup);
    void updateSuper(Super sup);
    void deleteSuperById(int id);
}
