/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.musiccollection.service;

import com.test.musiccollection.MessageResponse;
import com.test.musiccollection.model.Style;
import com.test.musiccollection.repository.ArtistRepository;
import com.test.musiccollection.repository.PeopleRepository;
import com.test.musiccollection.repository.StyleRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;

/**
 *
 * @author alejandra
 */

@Service
public class ServiceAddElements {
    
    @Autowired
    private ArtistRepository artistRepo;
    @Autowired
    private PeopleRepository peopleRepo;
    @Autowired
    private StyleRepository styleRepo;
    
    public MessageResponse messageResponse = new MessageResponse();
    
    
    public MessageResponse newStyle(String name){
        
        // Check if this user is already registered.
        Optional<Style> optionalStyle = styleRepo.findByName(name);
        
        if (optionalStyle.isPresent()) {
            
            messageResponse.setStatus("401");
            messageResponse.setContent("Ya existe un estilo musical con el mismo nombre.");
            
            return messageResponse;
        }
        
        Style style = new Style();
        style.setName(name);
        try {
            style = styleRepo.save(style);
        } catch (TransactionSystemException e){
            // TODO: define the error code in the API.
            messageResponse.setStatus("401");
            messageResponse.setContent("El formato de sus datos no es correcto.");
            return messageResponse;
        }
        
        
        messageResponse.setStatus("200");
        messageResponse.setContent("El estilo ha sido almacenado de manera satisfactoria.");

        return messageResponse;
    
    }
    
    
}
