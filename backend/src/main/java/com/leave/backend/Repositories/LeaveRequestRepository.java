package com.leave.backend.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leave.backend.Entities.LeaveRequest;
import com.leave.backend.Enumeration.Status;

import org.springframework.stereotype.Repository;


@Repository
public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long>{
    List<LeaveRequest> findByUserId(Long userId);
    List<LeaveRequest> findByStatus(Status status);
    List<LeaveRequest> findByUser_IdAndStatus(Long userId, Status status);


}
