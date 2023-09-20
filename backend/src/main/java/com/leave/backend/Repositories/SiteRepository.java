package com.leave.backend.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leave.backend.Entities.Site;
import org.springframework.stereotype.Repository;


@Repository
public interface SiteRepository extends JpaRepository<Site, Integer>{
    
}
