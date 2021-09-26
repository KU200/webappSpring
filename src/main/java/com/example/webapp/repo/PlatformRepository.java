package com.example.webapp.repo;

import com.example.webapp.entity.Campaign;
import com.example.webapp.entity.Platform;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PlatformRepository extends JpaRepository<Platform, Integer> {

    @Query("select b from Platform b where b.name = :name")
    Platform findByName(@Param("name") String name);
}
