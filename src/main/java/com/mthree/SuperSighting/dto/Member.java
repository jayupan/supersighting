/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.SuperSighting.dto;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author Josef
 */
public class Member {
    private Super sup;
    private Organization org;

    public Super getSuper() {
        return sup;
    }

    public void setSup(Super sup) {
        this.sup = sup;
    }

    public Organization getOrg() {
        return org;
    }

    public void setOrg(Organization org) {
        this.org = org;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 13 * hash + Objects.hashCode(this.sup);
        hash = 13 * hash + Objects.hashCode(this.org);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Member other = (Member) obj;
        if (!Objects.equals(this.sup, other.sup)) {
            return false;
        }
        if (!Objects.equals(this.org, other.org)) {
            return false;
        }
        return true;
    }

    
    
}
