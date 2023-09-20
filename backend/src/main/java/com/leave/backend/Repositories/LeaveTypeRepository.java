package com.leave.backend.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leave.backend.Entities.LeaveType;
import org.springframework.stereotype.Repository;


@Repository
public interface LeaveTypeRepository extends JpaRepository<LeaveType, Long>{
    
}
