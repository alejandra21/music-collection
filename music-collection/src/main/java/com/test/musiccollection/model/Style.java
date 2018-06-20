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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author alejandra
 */

@Entity
@Table(
    indexes= @Index(
            name="id_style",
            columnList="id",
            unique=true
    )
)
public class Style {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Long id;

    @ManyToMany(mappedBy = "styles")
    private List<Artist> artists = new ArrayList<>();
 
    @Column(name="name", unique=true)
    private String name;

    public Style(){}

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
    
    public void removeArtist(Artist artist) {
        artists.remove(artist);
        //style.setArtist(null);
    }
    
}
