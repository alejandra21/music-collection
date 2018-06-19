/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.musiccollection.controller;

import com.test.musiccollection.model.Style;
import com.test.musiccollection.repository.StyleRepository;
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
public class StyleController {
    
    @Autowired
     StyleRepository styleRepo;
    
    
    @RequestMapping(value="/style", method=RequestMethod.GET)
    public String allStyles(Model model) {
        
        System.out.println("ESTE ES EL RESULTADO");
        System.out.println(styleRepo.findAll());
        model.addAttribute("elements", styleRepo.findAll());
        return "showElements";
    }
    
    @RequestMapping(value="/style/add", method=RequestMethod.GET)
    public String formStyles() {
        System.out.println("Voy a mostrar el form");
        return "newStyle";
    }
    
    @RequestMapping(value="/style/add", method=RequestMethod.POST)
    public String addStyles(
            @RequestParam(value="name") String name,
            Model model) {
        
        // TODO: I have to make a service that save new styles.
        
        Style newStyle = new Style();
        newStyle.setName(name);
        newStyle = styleRepo.save(newStyle);
        
        System.out.println("ESTE ES EL RESULTADO");
        System.out.println(name);
        
        model.addAttribute("elements", styleRepo.findAll());
        return "showElements";
    }
    
        @RequestMapping(value="/style/delete", method=RequestMethod.POST)
    public String deleteStyles(
            @RequestParam(value="id") String id,
            Model model) {
        
        // TODO: I have to make a service that save new styles.
        
        System.out.println("id");
        System.out.println(id);
        
        Long styleId = Long.valueOf(id).longValue();
        Optional<Style> optionalStyle = styleRepo.findById(styleId);
        
        String  message = "";
                
        if (!optionalStyle.isPresent()) {
            System.out.println("No existe el style");
            message = "No existe el Style";
        }
        
        Style style = optionalStyle.get();
        styleRepo.delete(style);
        message = "Se eliminó el elemento satisfactoriamente.";
                
        model.addAttribute("elements", styleRepo.findAll());
        model.addAttribute("message", message);
        return "showElements";
    }
    
}
