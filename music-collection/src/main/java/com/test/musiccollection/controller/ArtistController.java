/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.musiccollection.controller;

import com.test.musiccollection.MessageResponse;
import com.test.musiccollection.model.Artist;
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
import org.springframework.web.bind.annotation.PathVariable;
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
        
        String action  = "/artist/delete";
        
        model.addAttribute("action", action);
        model.addAttribute("elements", artistRepo.findAll());
                
        return "showArtists";
    }
    
    @RequestMapping(value="/artist/{id}/member/add", method=RequestMethod.GET)
    public String addArtistMember(@PathVariable("id") Long artistId,
                                  Model model) {
        
        List<People> artistMembers = new ArrayList<>();
        Optional<Artist> artist    =  artistRepo.findById(artistId);
        List<People> allPeople     =  peopleRepo.findAll();
        
        if (artist.isPresent()){
            artistMembers = artist.get().getMembers();
            allPeople.removeAll(artistMembers);
        }
  
        model.addAttribute("id", artistId);
        model.addAttribute("members", allPeople);
   
        return "addMember";
    }
    
    @RequestMapping(value="/artist/{id}/member/add", method=RequestMethod.POST)
    public String saveArtistMember(@PathVariable("id") Long artistId,
                    @RequestParam("members") Optional<List<String>> members,
                    Model model) {
        
        model.addAttribute("id", artistId);
        model.addAttribute("members", peopleRepo.findAll());
        
        List<String> memberList = new ArrayList<>();
        
        if (members.isPresent()){
            memberList =  members.get();
        }
   
        MessageResponse message = addElements.newArtistMember(artistId, memberList);
        
        model.addAttribute("message", message);
        
        return "redirect:/artist";
    }
    
    @RequestMapping(value="/artist/{id}/style/add", method=RequestMethod.GET)
    public String addArtistStyle(@PathVariable("id") Long artistId,
                                  Model model) {
        
        List<Style> artistStyles = new ArrayList<>();
        Optional<Artist> artist = artistRepo.findById(artistId);
        List<Style> allStyles = styleRepo.findAll();

        if (artist.isPresent()) {
            artistStyles = artist.get().getStyles();
            allStyles.removeAll(artistStyles);
        }
        
        
        
        model.addAttribute("id", artistId);
        model.addAttribute("style", allStyles);
   
        return "addStyle";
    }
    
    @RequestMapping(value="/artist/{id}/style/add", method=RequestMethod.POST)
    public String saveArtistStyle(@PathVariable("id") Long artistId,
                    @RequestParam("styles") Optional<List<String>> style,
                    Model model) {
        
        
        List<String> styleList = new ArrayList<>();
        
        if (style.isPresent()){
            styleList =  style.get();
        }
        
        MessageResponse message = addElements.newArtistStyle(artistId, styleList);
        
        model.addAttribute("message", message);
        
        return "redirect:/artist";
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

        MessageResponse response = addElements.newArtist(years,name,memberList,stylesList);
        
        message = response.getContent();
       
        model.addAttribute("message", message);
     
        return "redirect:/artist";
    }
    
    @RequestMapping(value="/artist/delete", method=RequestMethod.POST)
    public String deleteArtist(
            @RequestParam(value="id") String id,
            Model model) {
        
        Long artistId = Long.valueOf(id).longValue();

        MessageResponse response = deleteElements.deleteArtist(artistId);
        String message = response.getContent();
                        

        model.addAttribute("message", message);
       
        return "redirect:/artist";
    }
    
    @RequestMapping(value = "/artist/member", method = RequestMethod.POST)
    public String deleteMember(
            @RequestParam(value = "idMember") String idMember,
            @RequestParam(value = "idArtist") String idArtist,
            Model model) {


        
        Long memberId = Long.valueOf(idMember).longValue();
        Long artistId = Long.valueOf(idArtist).longValue();

        MessageResponse response = deleteElements.deleteArtistMember(memberId,artistId);
        String message = response.getContent();
        
        
        model.addAttribute("message", message);
  
        return "redirect:/artist";
    }
    
    @RequestMapping(value = "/artist/style", method = RequestMethod.POST)
    public String deleteStyle(
            @RequestParam(value = "idStyle") String idStyle,
            @RequestParam(value = "idArtist") String idArtist,
            Model model) {

        Long styleId = Long.valueOf(idStyle).longValue();
        Long artistId = Long.valueOf(idArtist).longValue();

        MessageResponse response = deleteElements.deleteArtistStyle(styleId,artistId);
        String message = response.getContent();

        model.addAttribute("message", message);
        

        return "redirect:/artist";
    }
    
    @RequestMapping(value = "/artist/search", method = RequestMethod.GET)
    public String searchStyle(
            @RequestParam(value = "styleName") String styleName,
            Model model) {


        Optional<Style> optionalStyle = styleRepo.findByName(styleName);
        List<Artist> lista = new ArrayList<>();
        Style style = new Style();
        
        if (optionalStyle.isPresent()){
            style = optionalStyle.get();
            lista = style.getArtist();
        }
            
        model.addAttribute("elements", lista);

        return "searchResults";
    }
}
