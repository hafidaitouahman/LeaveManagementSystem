package com.leave.backend.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leave.backend.Entities.Departement;

public interface DepartementRepository extends JpaRepository<Departement, Integer> {
    
}
