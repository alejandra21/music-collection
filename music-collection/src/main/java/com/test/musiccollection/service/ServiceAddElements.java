/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.musiccollection.service;

import com.test.musiccollection.MessageResponse;
import com.test.musiccollection.model.People;
import com.test.musiccollection.model.Style;
import com.test.musiccollection.model.Artist;
import com.test.musiccollection.repository.ArtistRepository;
import com.test.musiccollection.repository.PeopleRepository;
import com.test.musiccollection.repository.StyleRepository;
import java.util.List;
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
    
    public MessageResponse newPeople(Integer years, String name){
                        
        People people = new People();
        people.setName(name);
        people.setYears(years);
        
        try {
            people = peopleRepo.save(people);
        } catch (TransactionSystemException e){
            // TODO: define the error code in the API.
            messageResponse.setStatus("401");
            messageResponse.setContent("El formato de sus datos no es correcto.");
            return messageResponse;
        }
        
        
        messageResponse.setStatus("200");
        messageResponse.setContent("La persona se ha almacenado de forma satisfactoria.");

        return messageResponse;
    
    }
    
    public MessageResponse newArtist(Integer years, String name, 
                                    List<String> members, List<String> styles){
        
        
        Style newStyle   = new Style();
        People newMember = new People();
        
        Optional<Style>  optionalStyle;
        Optional<People> optionalPeople;
        Long memberId;
        Long styleId;
        String stringId;
        
        Artist artist = new Artist();
        artist.setName(name);
        artist.setYears(years);
        
        for (int i = 0; i < members.size(); i++) {
 
            stringId = members.get(i);
            memberId = Long.valueOf(stringId).longValue();
            optionalPeople = peopleRepo.findById(memberId);
            if (!optionalPeople.isPresent()) {
            
                messageResponse.setStatus("400");
                messageResponse.setContent("Se detecto un error al agregar miembros al staff del artista.");
                break;
            }
            
            newMember = optionalPeople.get();
            artist.addMember(newMember);
             
	}
        
        if ("400".equals(messageResponse.getStatus())){
            return messageResponse;
        }
        
        for (int i = 0; i < styles.size(); i++) {
            
            stringId = styles.get(i);
            styleId = Long.valueOf(stringId).longValue();
            optionalStyle = styleRepo.findById(styleId);
            
            if (!optionalStyle.isPresent()) {
            
                messageResponse.setStatus("400");
                messageResponse.setContent("Se detecto un error al agregar un estilo al artista.");
                break;
            }
            
            newStyle = optionalStyle.get();
            artist.addStyle(newStyle);
 
	}
        
        if ("400".equals(messageResponse.getStatus())){
            return messageResponse;
        }
        
        try {
            artist = artistRepo.save(artist);
        } catch (TransactionSystemException e){
            // TODO: define the error code in the API.
            messageResponse.setStatus("401");
            messageResponse.setContent("El formato de sus datos no es correcto.");
            return messageResponse;
        }
        
        
        messageResponse.setStatus("200");
        messageResponse.setContent("El artista se ha almacenado de forma satisfactoria.");

        return messageResponse;
 
       
    }
    
    
}
