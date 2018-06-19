/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.musiccollection.controller;

import com.test.musiccollection.MessageResponse;
import com.test.musiccollection.repository.ArtistRepository;
import com.test.musiccollection.service.ServiceAddElements;
import com.test.musiccollection.service.ServiceDeleteElements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author alejandra
 */

@Controller
public class ArtistController {
    
    @Autowired
    ArtistRepository artistRepo;
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
    public String formArtist() {
        return "newArtist";
    }
}
