package com.example.webapp.controllers;

import com.example.webapp.entity.Ad;
import com.example.webapp.entity.Campaign;
import com.example.webapp.repo.AdRepository;
import com.example.webapp.repo.CampaignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InfoController {

    @Autowired
    private AdRepository adRepository;

    @Autowired
    private CampaignRepository campaignRepository;

    @GetMapping("/info")
    public String info(Model model) {
        Iterable<Ad> ads= adRepository.findAll();
        Iterable<Campaign> campaigns= campaignRepository.findAll();
        model.addAttribute("ads", ads);
        model.addAttribute("campaigns", campaigns);
        return "info";
    }
}
