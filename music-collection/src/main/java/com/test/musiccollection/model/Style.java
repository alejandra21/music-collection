/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.musiccollection.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author alejandra
 */

@Entity
public class Style {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Long id;

    @Column(name="name", unique=true)
    private String name;

    public Style(String name){
        this.name = name;
    }

    // Getter

    public Long getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    // Setter

    public void setName(String name){
        this.name = name;
    }
    
}
