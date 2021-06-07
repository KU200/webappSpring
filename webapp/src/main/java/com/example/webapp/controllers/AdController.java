package com.example.webapp.controllers;

import com.example.webapp.entity.Ad;
import com.example.webapp.entity.Campaign;
import com.example.webapp.repo.AdRepository;
import com.example.webapp.repo.CampaignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@Controller
public class AdController {
    @Autowired
    private AdRepository adRepository;
    @Autowired
    private CampaignRepository campaignRepository;

    @GetMapping("/ads")
    public String adsInfo(Model model) {
        List<Ad> ads = adRepository.findAll();
        model.addAttribute("ads", ads);
        return "ads";
    }

    @GetMapping("/ads/add")
    public String adsAdd(Model model) {
        return "add-ad";
    }

    @PostMapping("/ads/add")
    public String addAd(@RequestParam String name, @RequestParam String assetUrl, @RequestParam int campaignId, Model model) {
        Campaign campaign = campaignRepository.findById(campaignId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Campaign having id " + campaignId + " not found"));
        Ad ad = new Ad(name, assetUrl,campaign);
        adRepository.save(ad);
        return "redirect:/ads";
    }

    @GetMapping("/ads/{id}/edit")
    public String adEdit(@PathVariable(value = "id") int id, Model model) {
        Ad ad = adRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Ad having id " + id + " not found"));
        model.addAttribute("ad", ad);
        return "ad-edit";
    }

    @PostMapping("/ads/{id}/edit")
    public String adUpdate(@PathVariable(value = "id") int id, @RequestParam String name, @RequestParam String assetUrl, @RequestParam int campaignId, Model model) {
        Ad ad = adRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Campaign having id " + id + " not found"));
        Campaign campaign = campaignRepository.findById(campaignId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Campaign having id " + id + " not found"));
        ad.setName(name);
        ad.setAssetUrl(assetUrl);
        ad.setCampaign(campaign);
        adRepository.save(ad);
        return "redirect:/ads";
    }
}
