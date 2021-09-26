//package com.example.webapp.mappers;

import com.example.webapp.dto.AdDTO;
import com.example.webapp.entity.Ad;
import com.example.webapp.entity.Campaign;
import com.example.webapp.entity.Platform;
import com.example.webapp.entity.Status;
import lombok.Data;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

//@Mapper
//public interface AdMapper {
//    AdMapper INSTANCE = Mappers.getMapper(AdMapper.class);
//    AdDTO toDTO(Ad ad);
//@Data
//public class AdMapper {
//    public int id;
//    public String name;
//    public String assetUrl;
//    public Status status;
//    public Campaign campaign;
//    public List<Integer> platformIds;}
//public class AdMapperImpl implements AdMapper {
//
//    @Override
//    public AdDTO toDTO(Ad ad) {
//        if ( ad == null ) {
//            return null;
//        }
//
//        AdDTO adDTO = new AdDTO();
//
//        adDTO.id = ad.getId();
//        adDTO.name = ad.getName();
//        adDTO.assetUrl = ad.getAssetUrl();
//        adDTO.status = ad.getStatus();
//        adDTO.campaign = ad.getCampaign();
//
//        return adDTO;
//    }
//}
