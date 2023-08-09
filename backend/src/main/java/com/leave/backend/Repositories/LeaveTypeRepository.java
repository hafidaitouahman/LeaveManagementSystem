package com.leave.backend.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leave.backend.Entities.LeaveType;

public interface LeaveTypeRepository extends JpaRepository<LeaveType, Integer>{
    
}
