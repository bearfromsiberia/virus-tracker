package com.lost_bear.virustracker.controllers;

import com.lost_bear.virustracker.services.VirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

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
}
