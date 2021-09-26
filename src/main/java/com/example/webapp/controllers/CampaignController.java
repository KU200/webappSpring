package com.example.webapp.controllers;

import com.example.webapp.dto.AdDTO;
import com.example.webapp.dto.CampaignDTO;
import com.example.webapp.dto.PlatformDTO;
import com.example.webapp.entity.Ad;
import com.example.webapp.entity.Campaign;
import com.example.webapp.entity.Platform;
import com.example.webapp.repo.AdRepository;
import com.example.webapp.repo.CampaignRepository;
import com.example.webapp.repo.PlatformRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;


@Controller
@RequiredArgsConstructor
@RequestMapping("/campaigns")
public class CampaignController {

    private final CampaignRepository campaignRepository;
    private final AdRepository adRepository;
    private final PlatformRepository platformRepository;

    @GetMapping()
    public String campaignsInfo(Model model) {
        List<Campaign> campaigns = campaignRepository.findAll();
        model.addAttribute("campaigns", campaigns);
        return "campaigns";
    }

    @GetMapping("/{id}")
    public String campaignDetails(@PathVariable(value = "id") int id, Model model) {
        Campaign campaign = campaignRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Campaign having id " + id + " not found"));
        model.addAttribute("campaignDto", new CampaignDTO(campaign));
        return "campaign-details";
    }

    @GetMapping("/add")
    public String campaignsAdd(@ModelAttribute("campaignDto") CampaignDTO campaignDTO, Model model) {
        return "add-campaign";
    }

    @PostMapping("/add")
    public String addCampaign(@ModelAttribute("campaignDto") @Valid CampaignDTO campaignDto, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors())return "add-campaign";
        Campaign campaign = new Campaign(campaignDto.getName(), campaignDto.getStartDate(), campaignDto.getEndDate());
        campaignRepository.save(campaign);
        return "redirect:/campaigns";
    }

    @GetMapping("/{id}/ad-add")
    public String adsAdd(@PathVariable(value = "id") int campaignId,
                         @ModelAttribute("adDto") AdDTO adDto, Model model) {
        Campaign campaign = campaignRepository.findById(campaignId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Campaign having id " + campaignId + " not found"));
        List<Platform> platforms = platformRepository.findAll();
        List<PlatformDTO> platformDTOList = platforms.stream().map(platform ->
                PlatformDTO.toDTO(platform)).collect(Collectors.toList());
        model.addAttribute("platforms", platformDTOList);
        model.addAttribute("campaignDto", new CampaignDTO(campaign));
        return "campaign-ad-add";
    }

    @PostMapping("/{id}/ad-add")
    public String addAd(@PathVariable(value = "id") int campaignId,
                        @Valid AdDTO adDto,BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors())return "campaigns/{id}/ad-add";
        Campaign campaign = campaignRepository.findById(campaignId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Campaign having id " + campaignId + " not found"));
        Ad ad = new Ad(adDto.getName(), adDto.getAssetUrl(), campaign, platformRepository.findAllById(adDto.getPlatformsIds()));
        adRepository.save(ad);
        model.addAttribute("campaignDto", new CampaignDTO(campaign));
        return "redirect:/campaigns/{id}";
    }

    @GetMapping("/{id}/edit")
    public String campaignEdit(@PathVariable(value = "id") int id, Model model) {
        Campaign campaign = campaignRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Campaign having id " + id + " not found"));
        model.addAttribute("campaignDto", new CampaignDTO(campaign));
        return "campaign-edit";
    }

    @PostMapping("/{id}/edit")
    public String campaignUpdate(@PathVariable(value = "id") int id, @Valid CampaignDTO campaignDto,
//                                 @RequestParam String name, @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
//                                 @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate,
                                 BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors())return "campaigns/{id}/edit";
        Campaign campaign = campaignRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Campaign having id " + id + " not found"));
        campaign.setName(campaignDto.getName());
        campaign.setStartDate(campaignDto.getStartDate());
        campaign.setEndDate(campaignDto.getEndDate());
        campaignRepository.save(campaign);
        return "redirect:/campaigns/{id}";
    }

    @PostMapping("/{id}/remove")
    public String campaignDelete(@PathVariable(value = "id") int id, Model model) {
        Campaign campaign = campaignRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Campaign having id " + id + " not found"));
        campaignRepository.delete(campaign);
        return "redirect:/campaigns";
    }

}
