package com.example.webapp.controllers;

import com.example.webapp.entity.Ad;
import com.example.webapp.entity.Campaign;
import com.example.webapp.repo.CampaignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

@Controller
public class CampaignController {

    @Autowired
    private CampaignRepository campaignRepository;

    @GetMapping("/campaigns")
    public String campaignsInfo(Model model) {
        Iterable<Campaign> campaigns = campaignRepository.findAll();
        model.addAttribute("campaigns", campaigns);
        return "campaigns";
    }

    @GetMapping("/campaigns/{id}")
    public String campaignDetails(@PathVariable(value = "id") int id, Model model) {
        Campaign campaign = campaignRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Campaign having id " + id + " not found"));
        model.addAttribute("campaign", campaign);
        return "campaign-details";
    }

    @GetMapping("/campaigns/add")
    public String campaignsAdd(Model model) {
        return "add-campaign";
    }

    @PostMapping("/campaigns/add")
    public String addCampaign(@RequestParam String name, @RequestParam("startDate")
    @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate startDate, @RequestParam("endDate")
                              @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate endDate, Model model) {
        Campaign campaign = new Campaign(name, startDate, endDate);
        campaignRepository.save(campaign);
        return "redirect:/campaigns";
    }

    @GetMapping("/campaigns/{id}/edit")
    public String campaignEdit(@PathVariable(value = "id") int id, Model model) {
        Campaign campaign = campaignRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Campaign having id " + id + " not found"));
        model.addAttribute("campaign", campaign);
        return "campaign-edit";
    }

    @PostMapping("/campaigns/{id}/edit")
    public String campaignUpdate(@PathVariable(value = "id") int id, @RequestParam String name,
                                 @RequestParam("startDate") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate startDate,
                                 @RequestParam("endDate") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate endDate, Model model) {
        Campaign campaign = campaignRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Campaign having id " + id + " not found"));
        campaign.setName(name);
        campaign.setStartDate(startDate);
        campaign.setEndDate(endDate);
        campaignRepository.save(campaign);
        return "redirect:/campaigns";
    }

    @PostMapping("/campaigns/{id}/remove")
    public String campaignDelete(@PathVariable(value = "id") int id, Model model) {
        Campaign campaign = campaignRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Campaign having id " + id + " not found"));
        campaignRepository.delete(campaign);
        return "redirect:/campaigns";
    }

}
