package com.example.webapp.controllers;

import com.example.webapp.dto.AdDTO;
import com.example.webapp.dto.CampaignDTO;
import com.example.webapp.dto.PlatformDTO;
import com.example.webapp.entity.Ad;
import com.example.webapp.entity.Campaign;
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
@RequestMapping("/ads")
public class AdController {

    private final AdRepository adRepository;
    private final CampaignRepository campaignRepository;
    private final PlatformRepository platformRepository;

    @GetMapping()
    public String adsInfo(Model model) {
        List<Ad> ads = adRepository.findAll();
        model.addAttribute("ads", ads);
        return "ads";
    }

    @GetMapping("/add")
    public String adsAdd(@ModelAttribute("adDto") AdDTO adDto, Model model) {
        List<PlatformDTO> platformDTOList = platformRepository.findAll().stream().map(platform ->
                PlatformDTO.toDTO(platform)).collect(Collectors.toList());
        model.addAttribute("platforms", platformDTOList);
        model.addAttribute("campaignsList", campaignRepository.findAll().stream()
                .map(CampaignDTO::new).collect(Collectors.toList()));
        return "add-ad";
    }

    @PostMapping("/add")
    public String addAd(@ModelAttribute("adDto") @Valid AdDTO adDto, BindingResult bindingResult,
                        Model model) {
        if (bindingResult.hasErrors()) return "add-ad";
        Campaign campaign = campaignRepository.findById(adDto.getCampaignId()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Campaign having id " + adDto.getCampaignId() + " not found"));
        Ad ad = new Ad(adDto.getName(), adDto.getAssetUrl(), campaign, platformRepository.findAllById(adDto.getPlatformsIds()));
        adRepository.save(ad);
        return "redirect:/ads";
    }

    @GetMapping("/{id}/edit")
    public String adEdit(@PathVariable(value = "id") int id, Model model) {
        List<PlatformDTO> platformDTOList = platformRepository.findAll().stream().map(platform ->
                PlatformDTO.toDTO(platform)).collect(Collectors.toList());
        Ad ad = adRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Ad having id " + id + " not found"));
        AdDTO adDto = new AdDTO(ad);
        model.addAttribute("platforms", platformDTOList);
        model.addAttribute("adDto", adDto);
        return "ad-edit";
    }

    @PostMapping("/{id}/edit")
    public String adUpdate(@PathVariable(value = "id") int id,
                           @Valid AdDTO adDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) return "ads/{id}/edit";
        Ad ad = adRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Ad having id " + id + " not found"));
        ad.setPlatforms((platformRepository.findAllById(adDto.getPlatformsIds())));
        ad.setName(adDto.getName());
        ad.setAssetUrl(adDto.getAssetUrl());
        adRepository.save(ad);
        return "redirect:/ads";
    }

    @PostMapping("/{id}/remove")
    public String adDelete(@PathVariable(value = "id") int id, Model model) {
        Ad ad = adRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Ad having id " + id + " not found"));
        adRepository.delete(ad);
        return "redirect:/ads";
    }
}
