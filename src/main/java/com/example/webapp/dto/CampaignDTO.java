package com.example.webapp.dto;

import com.example.webapp.entity.Ad;
import com.example.webapp.entity.Campaign;
import com.example.webapp.entity.Status;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

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
    @Future(message = "Start date must be in future")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @Future(message = "End date must be in future")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    private List<Integer> adsIds;
    private List<String> adsNames;

//    @AssertTrue
//    public boolean isValid() {
//        if (endDate.isBefore(startDate))
//            return false;
//        else return true;
//    }
    public CampaignDTO(Campaign campaign) {
        this.id = campaign.getId();
        this.name = campaign.getName();
        this.status = campaign.getStatus();
        this.startDate = campaign.getStartDate();
        this.endDate = campaign.getEndDate();
        this.adsIds = campaignAdsIds(campaign.getAds());
        this.adsNames = campaignAdsNames(campaign.getAds());
    }
    List<Integer> campaignAdsIds(List<Ad> ads){
        List<Integer> adsIdsList = ads.stream().map(ad -> ad.getId()).collect(Collectors.toList());
        return adsIdsList;
    }
    List<String> campaignAdsNames(List<Ad> ads) {
        List<String> adNamesList = ads.stream().map(ad -> ad.getName()).collect(Collectors.toList());
        return adNamesList;
    }
}
