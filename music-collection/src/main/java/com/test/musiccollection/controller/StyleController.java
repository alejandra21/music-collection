/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.musiccollection.controller;

import com.test.musiccollection.MessageResponse;
import com.test.musiccollection.model.Style;
import com.test.musiccollection.repository.StyleRepository;
import com.test.musiccollection.service.ServiceAddElements;
import com.test.musiccollection.service.ServiceDeleteElements;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author alejandra
 */

@Controller
public class StyleController {
    
    @Autowired
    StyleRepository styleRepo;
    @Autowired
    private ServiceAddElements addElements;
    @Autowired
    private ServiceDeleteElements deleteElements;
    public  MessageResponse messageResponse = new MessageResponse();
    
    
    @RequestMapping(value="/style", method=RequestMethod.GET)
    public String allStyles(Model model) {
        
        //Delete action
        String action  = "/style/delete";
        model.addAttribute("action", action);
        model.addAttribute("elements", styleRepo.findAll());
        return "showStyle";
    }
    
    @RequestMapping(value="/style/add", method=RequestMethod.GET)
    public String formStyles() {
        return "newStyle";
    }
    
    @RequestMapping(value="/style/add", method=RequestMethod.POST)
    public String addStyles(
            @RequestParam(value="name") String name,
            Model model) {
                
        String message;
        //Delete action
        String action  = "/style/delete";
        
        MessageResponse response = addElements.newStyle(name);
        
        message = response.getContent();
                
        model.addAttribute("elements", styleRepo.findAll());
        model.addAttribute("message", message);
        model.addAttribute("action", action);
        
        return "showStyle";
    }
    
    @RequestMapping(value="/style/{id}/delete", method=RequestMethod.GET)
    public String deleteStyles(
            @PathVariable("id") Long id,
            Model model) {
        
        // TODO: I have to make a service that save new styles.
        
        String action   = "/style/delete";
       
        MessageResponse response = deleteElements.deleteStyle(id);
        String message = response.getContent();
                        
        model.addAttribute("elements", styleRepo.findAll());
        model.addAttribute("message", message);
        model.addAttribute("action", action);
        
        return "showStyle";
    }
    
}
