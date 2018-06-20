/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.musiccollection.repository;

import com.test.musiccollection.model.Artist;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author alejandra
 */

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long> {
    
    //@Query("SELECT a FROM Artist a where a.style.name = :styleName")
    //public Optional<Artist> findByStyle(@Param("styleName") String styleName);
    
}
