/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.musiccollection.model;

import javax.persistence.Column;

/**
 *
 * @author alejandra
 */
public class Artist extends GenericInfo {

    @Column(name="years", unique=true)
    private Integer years;

    // Constructor
    public Artist(){}

    // Getter

    public Integer getYears(){
        return years;
    }

    // Setter
    public void setYears(Integer years){
        this.years = years;
    }
    
}
