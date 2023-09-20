package com.leave.backend.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leave.backend.Entities.LeaveRequest;
import org.springframework.stereotype.Repository;


@Repository
public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long>{
    
}
