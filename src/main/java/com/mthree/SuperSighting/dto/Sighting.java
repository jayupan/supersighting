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
public class Sighting {
    private String dateTime;
    private int sup;
    private int loc;
    private String superName, locationName;

    public String getSuperName() {
        return superName;
    }

    public void setSuperName(String superName) {
        this.superName = superName;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public int getSup() {
        return sup;
    }

    public void setSup(int sup) {
        this.sup = sup;
    }

    public int getLoc() {
        return loc;
    }

    public void setLoc(int loc) {
        this.loc = loc;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.dateTime);
        hash = 79 * hash + Objects.hashCode(this.sup);
        hash = 79 * hash + Objects.hashCode(this.loc);
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
        final Sighting other = (Sighting) obj;
        if (!Objects.equals(this.dateTime, other.dateTime)) {
            return false;
        }
        if (!Objects.equals(this.sup, other.sup)) {
            return false;
        }
        if (!Objects.equals(this.loc, other.loc)) {
            return false;
        }
        return true;
    }
    
    
}
