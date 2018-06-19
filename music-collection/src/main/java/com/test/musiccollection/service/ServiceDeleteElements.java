/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.musiccollection.service;

import com.test.musiccollection.MessageResponse;
import com.test.musiccollection.model.Artist;
import com.test.musiccollection.model.People;
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
public class ServiceDeleteElements {
    
    @Autowired
    private ArtistRepository artistRepo;
    @Autowired
    private PeopleRepository peopleRepo;
    @Autowired
    private StyleRepository styleRepo;
    
    public MessageResponse messageResponse = new MessageResponse();
    
    
    public MessageResponse deleteStyle(Long id){
        
        System.out.print("Estoy aqui");
        Optional<Style> optionalStyle = styleRepo.findById(id);
        
        System.out.print("Estoy aqui2");
            
        if (!optionalStyle.isPresent()) {
            messageResponse.setStatus("401");
            messageResponse.setContent("No existe el elemento que desea eliminar.");
            return messageResponse;
        }
        
        Style style = optionalStyle.get();
        
        try {
            styleRepo.delete(style);;
        } catch (TransactionSystemException e){
            // TODO: define the error code in the API.
            messageResponse.setStatus("401");
            messageResponse.setContent("No se pudo eliminar el elemento seleccionado.");
            return messageResponse;
        }
        
        messageResponse.setStatus("200");
        messageResponse.setContent("Se ha eliminado el elemento de manera satisfactoria.");

        return messageResponse;
    
    }
    
    public MessageResponse deletePeople(Long id){
        
        Optional<People> optionalPeople = peopleRepo.findById(id);
            
        if (!optionalPeople.isPresent()) {
            messageResponse.setStatus("401");
            messageResponse.setContent("No existe el elemento que desea eliminar.");
            return messageResponse;
        }
        
        People people = optionalPeople.get();
        
        try {
            peopleRepo.delete(people);;
        } catch (TransactionSystemException e){
            // TODO: define the error code in the API.
            messageResponse.setStatus("401");
            messageResponse.setContent("No se pudo eliminar el elemento seleccionado.");
            return messageResponse;
        }
        
        messageResponse.setStatus("200");
        messageResponse.setContent("Se ha eliminado el elemento de manera satisfactoria.");

        return messageResponse;
    
    }
    
    public MessageResponse deleteArtist(Long id){
        
        Optional<Artist> optionalArtist = artistRepo.findById(id);
            
        if (!optionalArtist.isPresent()) {
            messageResponse.setStatus("401");
            messageResponse.setContent("No existe el elemento que desea eliminar.");
            return messageResponse;
        }
        
        Artist artist = optionalArtist.get();
        
        try {
            artistRepo.delete(artist);;
        } catch (TransactionSystemException e){
            // TODO: define the error code in the API.
            messageResponse.setStatus("401");
            messageResponse.setContent("No se pudo eliminar el elemento seleccionado.");
            return messageResponse;
        }
        
        messageResponse.setStatus("200");
        messageResponse.setContent("Se ha eliminado el elemento de manera satisfactoria.");

        return messageResponse;
    
    }
    
}
