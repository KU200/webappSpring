package com.example.webapp.repo;

import com.example.webapp.entity.Campaign;
import com.example.webapp.entity.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CampaignRepository extends JpaRepository<Campaign, Integer> {

    Page<Campaign> findAll(Pageable pageable);
    List<Campaign> findAll(Sort sort);

    Page<Campaign> findAllByName(String name, Pageable pageable);

    Page<Campaign> findAllByStatus(String status, Pageable pageable);
//
//    Page<Campaign> findAllByAdsNumber(Integer adsNumber, Pageable pageable);

    @Query("select b from Campaign b where b.name = :name")
    Campaign findByName(@Param("name") String name);
}
