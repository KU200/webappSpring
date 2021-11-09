package com.example.webapp.repo;

import com.example.webapp.entity.Campaign;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CampaignRepository extends JpaRepository<Campaign, Integer> {

    Page<Campaign> findAll(Pageable pageable);

    @Query("select b from Campaign b where b.name = :name")
    Campaign findByName(@Param("name") String name);
}
