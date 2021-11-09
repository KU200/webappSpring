package com.example.webapp.controllers.rest;

import com.example.webapp.dto.CampaignDTO;
import com.example.webapp.dto.PlatformDTO;
import com.example.webapp.entity.Campaign;
import com.example.webapp.repo.CampaignRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/rest/campaigns")
public class RestCampaignController {

    private final CampaignRepository campaignRepository;

    @GetMapping("/summaries")
    public ResponseEntity<Map<String, Object>> campaignsSummaries
            (@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "3") Integer size) {
        log.info(page);
        try {
        List<Campaign> summaries = new ArrayList<Campaign>();
        Pageable paging = PageRequest.of(page, size);
        Page<Campaign> pageCampaigns = campaignRepository.findAll(paging);
        summaries = pageCampaigns.getContent();
        Map<String, Object> response = new HashMap<>();
        response.put("summaries", summaries);
        response.put("total pages", pageCampaigns.getTotalPages());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public CampaignDTO create(CampaignDTO campaignDto) {
        Campaign campaign = new Campaign(campaignDto.getName(), campaignDto.getStartDate(), campaignDto.getEndDate());
        campaign = campaignRepository.save(campaign);
        return new CampaignDTO(campaign);
    }
}
