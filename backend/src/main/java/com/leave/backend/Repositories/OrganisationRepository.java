package com.leave.backend.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leave.backend.Entities.Organisation;

public interface OrganisationRepository extends JpaRepository<Organisation, Integer>{
    
}
