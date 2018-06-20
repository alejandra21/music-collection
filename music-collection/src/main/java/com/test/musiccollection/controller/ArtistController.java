/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.musiccollection.controller;

import com.test.musiccollection.MessageResponse;
import com.test.musiccollection.model.People;
import com.test.musiccollection.model.Style;
import com.test.musiccollection.repository.ArtistRepository;
import com.test.musiccollection.repository.PeopleRepository;
import com.test.musiccollection.repository.StyleRepository;
import com.test.musiccollection.service.ServiceAddElements;
import com.test.musiccollection.service.ServiceDeleteElements;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author alejandra
 */

@Controller
public class ArtistController {
    
    @Autowired
    ArtistRepository artistRepo;
    @Autowired
    PeopleRepository peopleRepo;
    @Autowired
    StyleRepository styleRepo;
    @Autowired
    private ServiceAddElements addElements;
    @Autowired
    private ServiceDeleteElements deleteElements;
    public  MessageResponse messageResponse = new MessageResponse();
    
    @RequestMapping(value="/artist", method=RequestMethod.GET)
    public String allArtists(Model model) {
        
        model.addAttribute("elements", artistRepo.findAll());
        return "showArtists";
    }
    
    @RequestMapping(value="/artist/add", method=RequestMethod.GET)
    public String formArtist(Model model) {
        
        model.addAttribute("members", peopleRepo.findAll());
        model.addAttribute("styles", styleRepo.findAll());
        return "newArtist";
    }
    
    @RequestMapping(value="/artist/add", method=RequestMethod.POST)
    public String addArtist(
        @RequestParam(value="name") String name,
        @RequestParam(value="year") Integer years,
        @RequestParam("members") Optional<List<String>> members,
        @RequestParam("styles") Optional<List<String>> styles,
        Model model) {
          
        List<String> stylesList = new ArrayList<>();
        List<String> memberList = new ArrayList<>();
        
        if (members.isPresent()){
            memberList =  members.get();
        }
        
        if (styles.isPresent()){
            stylesList = styles.get();
        }

        String message;
        //Delete action
        String action  = "/artist/delete";
        
        MessageResponse response = addElements.newArtist(years,name,memberList,stylesList);
        
        message = response.getContent();
        
                
        model.addAttribute("elements", artistRepo.findAll());
        model.addAttribute("message", message);
        model.addAttribute("action", action);
        
        System.out.print(artistRepo.findAll());
        
        List<People> lista = new ArrayList<>();
            
        artistRepo.findAll().forEach(artist->{
            artist.getMembers().forEach(gente->{
                
                System.out.println(gente.getName());
                System.out.println(gente.getYears());
            
            });
            
        });
               
        artistRepo.findAll().forEach(artist->{
            artist.getStyles().forEach(gente->{
                
                System.out.println(gente.getName());
            
            });
            
        });
                
        return "showArtists";
    }
    
    @RequestMapping(value="/artist/delete", method=RequestMethod.POST)
    public String deleteArtist(
            @RequestParam(value="id") String id,
            Model model) {
        
        // TODO: I have to make a service that save new styles.
        
        String action   = "/artist/delete";
        
        Long artistId = Long.valueOf(id).longValue();

        MessageResponse response = deleteElements.deleteArtist(artistId);
        String message = response.getContent();
                        
        model.addAttribute("elements", artistRepo.findAll());
        model.addAttribute("message", message);
        model.addAttribute("action", action);
        
        return "showElements";
    }
}
