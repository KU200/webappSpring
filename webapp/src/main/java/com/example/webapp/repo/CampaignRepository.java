package com.example.webapp.repo;

import com.example.webapp.entity.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CampaignRepository extends JpaRepository<Campaign, Integer> {

    @Query("select b from Campaign b where b.name = :name")
   Campaign findByName(@Param("name") String name);


}
