/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.musiccollection.repository;

import com.test.musiccollection.model.Style;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author alejandra
 */

@Repository
public interface StyleRepository extends JpaRepository<Style, Long> {
    
    /**
     * 
     * @param name
     * @return Optional User.
     */ 
    public Optional<Style> findByName(@Param("name") String name);
    
}

