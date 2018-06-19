/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.musiccollection.controller;

import com.test.musiccollection.MessageResponse;
import com.test.musiccollection.repository.PeopleRepository;
import com.test.musiccollection.service.ServiceAddElements;
import com.test.musiccollection.service.ServiceDeleteElements;
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
public class PeopleController {
    
    @Autowired
    PeopleRepository peopleRepo;
    @Autowired
    private ServiceAddElements addElements;
    @Autowired
    private ServiceDeleteElements deleteElements;
    public  MessageResponse messageResponse = new MessageResponse();
    
    @RequestMapping(value="/people", method=RequestMethod.GET)
    public String allPeople(Model model) {
        
        model.addAttribute("elements", peopleRepo.findAll());
        return "showElements";
    }
    
    @RequestMapping(value="/people/add", method=RequestMethod.GET)
    public String formPeople() {
        return "newPeople";
    }
    
    @RequestMapping(value="/people/add", method=RequestMethod.POST)
    public String addPeople(
            @RequestParam(value="name") String name,
            @RequestParam(value="year") Integer years,
            Model model) {
                
        String message;
        //Delete action
        String action  = "/people/delete";
        
        MessageResponse response = addElements.newPeople(years,name);
        
        message = response.getContent();
                
        model.addAttribute("elements", peopleRepo.findAll());
        model.addAttribute("message", message);
        model.addAttribute("action", action);
        
        return "showElements";
    }
    
    @RequestMapping(value="/people/delete", method=RequestMethod.POST)
    public String deletePeople(
            @RequestParam(value="id") String id,
            Model model) {
        
        // TODO: I have to make a service that save new styles.
        
        String action   = "/people/delete";
        
        Long peopleId = Long.valueOf(id).longValue();

        MessageResponse response = deleteElements.deletePeople(peopleId);
        String message = response.getContent();
                        
        model.addAttribute("elements", peopleRepo.findAll());
        model.addAttribute("message", message);
        model.addAttribute("action", action);
        
        return "showElements";
    }
    
}
