/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.SuperSighting.dao;

import com.mthree.SuperSighting.dto.SuperPower;
import java.util.List;

/**
 *
 * @author Josef
 */
public interface SuperPowerDao {
    SuperPower getSuperPowerById(int id);
    List<SuperPower> getAllSuperPowers();
    SuperPower addSuperPower(SuperPower power);
    
    void deleteSuperPower(SuperPower power);
    void updateSuperPower(SuperPower p1, SuperPower p2);
}
