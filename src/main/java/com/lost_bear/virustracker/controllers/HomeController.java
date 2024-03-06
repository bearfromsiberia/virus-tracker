package com.lost_bear.virustracker.controllers;

import com.lost_bear.virustracker.services.VirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@RestController
public class HomeController {
    @Autowired
    VirusDataService virusDataService;

    @GetMapping("/")
    public ModelAndView home(){
        ModelAndView model = new ModelAndView();
        model.setViewName("home");
        model.addObject("locations",virusDataService.getAllStats());
        model.addObject("totalReportedCases",virusDataService.getTotal_deaths());
        return model;
    }
    @GetMapping("/hello")
    public ResponseEntity<Map<String,String>> sayHello(){
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(Map.of("message","Hello, %s".formatted(user.getUsername())));
    }
}
