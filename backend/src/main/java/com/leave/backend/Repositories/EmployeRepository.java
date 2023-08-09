package com.leave.backend.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leave.backend.Entities.Employe;

public interface EmployeRepository extends JpaRepository<Employe, Integer> {
    
}
