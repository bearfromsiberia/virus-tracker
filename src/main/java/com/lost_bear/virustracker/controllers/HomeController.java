package com.lost_bear.virustracker.controllers;

import com.lost_bear.virustracker.services.VirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @Autowired
    VirusDataService virusDataService;

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("locations",virusDataService.getAllStats());
        model.addAttribute("totalReportedCases",virusDataService.getTotal_deaths());
        return "home";
    }
}
