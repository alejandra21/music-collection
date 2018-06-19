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
import javax.persistence.Index;
import javax.persistence.Table;

/**
 *
 * @author alejandra
 */

@Table(
    indexes= @Index(
            name="id_people",
            columnList="id",
            unique=true
    )
)
public class People {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Long id;

    @Column(name="name", unique=true)
    private String name;

    @Column(name="years", unique=true)
    private Integer years;

    // Constructor
    public People(){}

    // Getter

    public Integer getYears(){
        return years;
    }

    public Long getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    // Setter
    public void setYears(Integer years){
        this.years = years;
    }
    
}
