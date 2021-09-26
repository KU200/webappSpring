//package com.example.webapp.mappers;
//
//import com.example.webapp.dto.PlatformDTO;
//import com.example.webapp.entity.Ad;
//import com.example.webapp.entity.Platform;
//import lombok.Data;
//
//import java.util.List;
//import java.util.Set;
//import java.util.stream.Collectors;
//
//@Data
//public class PlatformMapper {
//    public PlatformDTO toDTO(Platform platform) {
//        if (platform == null)
//            return null;
//        PlatformDTO platformDTO = new PlatformDTO();
//        platformDTO.id = platform.getId();
//        platformDTO.name = platform.getName();
//        platformDTO.adsIds = platformAdsIds(platform.getAds());
//        return platformDTO;
//    }
//    List<Integer> platformAdsIds(List<Ad> ads){
//        List<Integer> adIdsList = ads.stream().map(ad -> ad.getId()).collect(Collectors.toList());
//        return adIdsList;
//    }
//
//}
