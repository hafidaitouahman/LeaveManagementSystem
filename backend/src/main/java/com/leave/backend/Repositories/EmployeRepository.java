package com.leave.backend.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.leave.backend.Entities.Employe;
import com.leave.backend.Enumeration.Role;

public interface EmployeRepository extends JpaRepository<Employe, Integer> {
    
    @Query("SELECT e FROM Employe e WHERE e.role = :role AND e.name = :name")
    Employe findByRoleAndName(Role role, String name);
    
}
