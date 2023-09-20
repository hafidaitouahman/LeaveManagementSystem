package com.leave.backend.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leave.backend.Entities.Team;
import org.springframework.stereotype.Repository;


@Repository
public interface TeamRepository extends JpaRepository<Team, Integer>{
    
}
