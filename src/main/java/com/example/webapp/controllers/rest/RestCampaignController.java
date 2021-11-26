package com.example.webapp.controllers.rest;

import com.example.webapp.dto.CampaignDTO;
import com.example.webapp.entity.Campaign;
import com.example.webapp.repo.CampaignRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping("/rest/campaigns")
public class RestCampaignController {

    private final CampaignRepository campaignRepository;

    @GetMapping("/summaries")
    public ResponseEntity<Map<String, Object>> campaignsSummaries
            (@RequestParam(defaultValue = "0") Integer page,
             @RequestParam(defaultValue = "3") Integer size,
             @RequestParam(defaultValue = "name") String sortingParam,
             @RequestParam(defaultValue = "") String nameFilter,
             @RequestParam(defaultValue = "") String statusFilter) {
        try {
            List<CampaignDTO> summaries = new ArrayList<>();
            Page<Campaign> pageCampaigns;
            if (!nameFilter.equals("") && statusFilter.equals("")) {
                Pageable paging = PageRequest.of(page, size, Sort.by(sortingParam));
                pageCampaigns = campaignRepository.findAllByName(nameFilter, paging);
                summaries = pageCampaigns.getContent().stream()
                        .map(CampaignDTO::new)
                        .collect(Collectors.toList());
            } else if (nameFilter.equals("") && !statusFilter.equals("")) {
                Pageable paging = PageRequest.of(page, size, Sort.by(sortingParam));
                pageCampaigns = campaignRepository.findAllByStatus(statusFilter, paging);
                summaries = pageCampaigns.getContent().stream()
                        .map(CampaignDTO::new)
                        .collect(Collectors.toList());
            } else {
                Pageable paging = PageRequest.of(page, size, Sort.by(sortingParam));
                pageCampaigns = campaignRepository.findAll(paging);
                summaries = pageCampaigns.getContent().stream()
                        .map(CampaignDTO::new)
                        .collect(Collectors.toList());
            }
            Map<String, Object> response = new HashMap<>();
            response.put("summaries", summaries);
            response.put("totalPages", pageCampaigns.getTotalPages());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public CampaignDTO create(CampaignDTO campaignDto) {
        Campaign campaign = new Campaign(campaignDto.getName(),
                campaignDto.getStartDate(), campaignDto.getEndDate());
        campaign = campaignRepository.save(campaign);
        return new CampaignDTO(campaign);
    }
}
