/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.musiccollection.controller;

import com.test.musiccollection.model.Style;
import com.test.musiccollection.repository.StyleRepository;
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
    
}
