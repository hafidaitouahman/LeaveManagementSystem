package com.leave.backend.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.leave.backend.Entities.LeaveQuota;
import com.leave.backend.Entities.User;

public interface LeaveQuotaRepository extends JpaRepository<LeaveQuota, Integer> {
      @Query("SELECT l FROM LeaveQuota l JOIN l.users u WHERE u = :user")
    List<LeaveQuota> findByUser(@Param("user") User user);
}
