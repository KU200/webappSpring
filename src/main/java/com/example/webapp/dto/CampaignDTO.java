package com.example.webapp.dto;

import com.example.webapp.entity.Ad;
import com.example.webapp.entity.Campaign;
import com.example.webapp.entity.Platform;
import com.example.webapp.entity.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CampaignDTO {
    private int id;
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String name;
    @NotNull(message = "The end date should be after the start date")
    private Status status;
    @JsonIgnore
    @Future(message = "Start date must be in future")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @JsonIgnore
    @Future(message = "End date must be in future")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    @JsonIgnore
    private List<Integer> adsIds;
    @JsonIgnore
    private List<String> adsNames;
    private Integer adsNumber;

    public Integer getAdsNumber() {
        return adsIds.size();
    }

    public CampaignDTO(Campaign campaign) {
        this.id = campaign.getId();
        this.name = campaign.getName();
        this.status = campaign.getStatus();
        this.startDate = campaign.getStartDate();
        this.endDate = campaign.getEndDate();
        this.adsIds = campaignAdsIds(campaign.getAds());
        this.adsNames = campaignAdsNames(campaign.getAds());
    }
    public static CampaignDTO toDTO(Campaign campaign) {
        if (campaign == null)
            return null;
        CampaignDTO campaignDTO = new CampaignDTO();
        campaignDTO.id = campaign.getId();
        campaignDTO.name = campaign.getName();
        campaignDTO.status = campaign.getStatus();
        campaignDTO.startDate = campaign.getStartDate();
        campaignDTO.endDate = campaign.getEndDate();
        campaignDTO.adsIds = campaignAdsIds(campaign.getAds());
        campaignDTO.adsNames = campaignAdsNames(campaign.getAds());
        return campaignDTO;
    }
    static List<Integer> campaignAdsIds(List<Ad> ads){
        List<Integer> adsIdsList = ads.stream().map(ad -> ad.getId()).collect(Collectors.toList());
        return adsIdsList;
    }
    static List<String> campaignAdsNames(List<Ad> ads) {
        List<String> adNamesList = ads.stream().map(ad -> ad.getName()).collect(Collectors.toList());
        return adNamesList;
    }
}
