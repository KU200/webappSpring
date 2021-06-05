package com.example.webapp.repo;

import com.example.webapp.entity.Ad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AdRepository extends JpaRepository <Ad, Integer>{

    @Query("select b from Ad b where b.name = :name")
   Ad findByName(@Param("name") String name);

}
