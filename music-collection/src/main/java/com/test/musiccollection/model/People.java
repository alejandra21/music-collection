/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.musiccollection.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author alejandra
 */
public class People extends GenericInfo{

    @Column(name="years", unique=true)
    private Integer years;

    // Constructor
    public People(){}

    // Getter

    public Integer getYears(){
        return years;
    }

    // Setter
    public void setYears(Integer years){
        this.years = years;
    }
    
}
