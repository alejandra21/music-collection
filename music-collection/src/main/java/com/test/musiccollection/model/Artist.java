/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.musiccollection.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author alejandra
 */

@Entity
@Table(
    indexes= @Index(
            name="id_artist",
            columnList="id",
            unique=true
    )
)
public class Artist{

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Long id;

    // People associated with an artist.
    @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<People> members = new ArrayList<>();

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "Artist_Style", 
        joinColumns = { @JoinColumn(name = "id_artist") }, 
        inverseJoinColumns = { @JoinColumn(name = "id_style") }
    )
    private List<Style> styles = new ArrayList<>();

    @Column(name="name", unique=true)
    private String name;

    @Column(name="years", unique=true)
    private Integer years;

    // Constructor
    public Artist(){
    }

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

    public List<Style> getStyles() {
        return styles;
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

    public void addStyle(Style style) {
        styles.add(style);
        //member.setArtist(this);
    }
    
    public void removeStyle(Style style) {
        styles.remove(style);
        //member.setArtist(null);
    }

}
