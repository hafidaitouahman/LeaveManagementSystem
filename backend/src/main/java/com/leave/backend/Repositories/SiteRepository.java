package com.leave.backend.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leave.backend.Entities.Site;

public interface SiteRepository extends JpaRepository<Site, Integer>{
    
}
