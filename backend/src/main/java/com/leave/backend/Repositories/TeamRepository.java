package com.leave.backend.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leave.backend.Entities.Team;

public interface TeamRepository extends JpaRepository<Team, Integer>{
    
}
