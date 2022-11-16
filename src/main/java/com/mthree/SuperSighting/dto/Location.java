/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.SuperSighting.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Josef
 */
public class Location implements Serializable {
    private int id;
    private String name, desc, address, coord; 
    
    public int getId()
    {
        return id;
    }
    
    public String getName()
    {
        return name;
    }
    
    public String getDesc()
    {
        return desc;
    }
    
    public String getAddress()
    {
        return address;
    }
    
    public String getCoord()
    {
        return coord; 
    }
    
    public void setId(int id)
    {
        this.id = id;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public void setDesc(String desc)
    {
        this.desc = desc;
    }
    
    public void setAddress(String address)
    {
        this.address = address; 
    }
    
    public void setCoord(String coord)
    {
        this.coord = coord;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.id;
        hash = 97 * hash + Objects.hashCode(this.name);
        hash = 97 * hash + Objects.hashCode(this.desc);
        hash = 97 * hash + Objects.hashCode(this.address);
        hash = 97 * hash + Objects.hashCode(this.coord);
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
        final Location other = (Location) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.desc, other.desc)) {
            return false;
        }
        if (!Objects.equals(this.address, other.address)) {
            return false;
        }
        if (!Objects.equals(this.coord, other.coord)) {
            return false;
        }
        return true;
    }

    
    
    
}
