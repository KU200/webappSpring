package com.example.webapp.controllers.rest;

import com.example.webapp.dto.CampaignDTO;
import com.example.webapp.entity.Campaign;
import com.example.webapp.repo.CampaignRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/campaign")
public class RestCampaignController {

    private final CampaignRepository campaignRepository;

    @GetMapping("/summaries")
    public List<CampaignDTO> campaignsSummaries() {
        return campaignRepository.findAll().stream()
                .map(CampaignDTO::new)
                .collect(Collectors.toList());
    }

    @PostMapping
    public CampaignDTO create(CampaignDTO campaignDto) {
        Campaign campaign = new Campaign(campaignDto.getName(), campaignDto.getStartDate(), campaignDto.getEndDate());
        campaign = campaignRepository.save(campaign);
        return new CampaignDTO(campaign);
    }
}
