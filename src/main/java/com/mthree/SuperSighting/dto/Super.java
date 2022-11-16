/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mthree.SuperSighting.dto;

import java.util.Objects;

/**
 *
 * @author Josef
 */
public class Super {
    private int id, power_id;
    private String name, desc, power;
    
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
    public int getPowerId()
    {
        return power_id;
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
    public void setPowerId(int power_id)
    {
        this.power_id = power_id; 
    }
    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + this.id;
        hash = 83 * hash + this.power_id;
        hash = 83 * hash + Objects.hashCode(this.name);
        hash = 83 * hash + Objects.hashCode(this.desc);
        hash = 83 * hash + Objects.hashCode(this.power);
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
        final Super other = (Super) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.power_id != other.power_id) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.desc, other.desc)) {
            return false;
        }
        return Objects.equals(this.power, other.power);
    }

    

    
    
    
}
