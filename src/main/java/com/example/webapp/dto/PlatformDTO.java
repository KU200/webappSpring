package com.example.webapp.dto;

import com.example.webapp.entity.Ad;
import com.example.webapp.entity.Platform;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@AllArgsConstructor

public class PlatformDTO {

    private Integer id;
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String name;
    private List<Integer> adsIds;

    public static PlatformDTO toDTO(Platform platform) {
        if (platform == null)
            return null;
        PlatformDTO platformDTO = new PlatformDTO();
        platformDTO.id = platform.getId();
        platformDTO.name = platform.getName();
        platformDTO.adsIds = platformAdsIds(platform.getAds());
        return platformDTO;
    }
   static List<Integer> platformAdsIds(List<Ad> ads){
        List<Integer> adIdsList = ads.stream().map(ad -> ad.getId()).collect(Collectors.toList());
        return adIdsList;
    }

    public PlatformDTO() {
    }
    public PlatformDTO(Platform platform) {
        this.id = platform.getId();
        this.name = platform.getName();
        this.adsIds = platformAdsIds(platform.getAds());
    }
}
