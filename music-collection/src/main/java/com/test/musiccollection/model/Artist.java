/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.musiccollection.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author alejandra
 */

public class Artist{

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Long id;

    // People associated with an artist.
    @OneToMany(mappedBy = "merchant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<People> members = new ArrayList<>();

    @Column(name="name", unique=true)
    private String name;

    @Column(name="years", unique=true)
    private Integer years;

    // Constructor
    public Artist(){}

    // Getter

    public Long getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public Integer getYears(){
        return years;
    }

    public List<People> getMembers() {
        return members;
    }
    
    // Setter
    public void setName(String name){
        this.name = name;
    }

    public void setYears(Integer years){
        this.years = years;
    }

    public void addMember(People member) {
        members.add(member);
        //member.setArtist(this);
    }
    
    public void removeMember(People member) {
        members.remove(member);
        //member.setArtist(null);
    }

}
