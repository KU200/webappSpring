package com.example.webapp.dto;

import com.example.webapp.entity.Ad;
import com.example.webapp.entity.Platform;
import com.example.webapp.entity.Status;
import lombok.*;
import org.hibernate.validator.constraints.URL;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdDTO {
    private int id;
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String name;
    @NotEmpty(message = "Url should not be empty")
    @URL(message = "Invalid url format")
    private String assetUrl;
    private Status status;
    @NotNull(message = "Select campaign")
    private Integer campaignId;
    @NotNull(message = "Select platform")
    private List<Integer> platformsIds;
    private List<String> platformsNames;

    public AdDTO(Ad ad) {
        this.id = ad.getId();
        this.name = ad.getName();
        this.assetUrl = ad.getAssetUrl();
        this.status = ad.getStatus();
        this.campaignId = ad.getCampaign().getId();
        this.platformsIds = adPlatformIds(ad.getPlatforms());
        this.platformsNames = adPlatformNames(ad.getPlatforms());
    }

    List<Integer> adPlatformIds(List<Platform> platforms) {
        List<Integer> platformIdsList = platforms.stream().map(platform -> platform.getId()).collect(Collectors.toList());
        return platformIdsList;
    }

    List<String> adPlatformNames(List<Platform> platforms) {
        List<String> platformNamesList = platforms.stream().map(platform -> platform.getName()).collect(Collectors.toList());
        return platformNamesList;
    }
}

