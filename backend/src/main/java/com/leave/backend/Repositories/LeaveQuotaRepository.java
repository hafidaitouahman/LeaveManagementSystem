package com.leave.backend.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.leave.backend.Entities.LeaveQuota;
import com.leave.backend.Entities.User;
@Repository
public interface LeaveQuotaRepository extends JpaRepository<LeaveQuota, Long> {
    //   @Query("SELECT l FROM LeaveQuota l JOIN l.users u WHERE u = :user")
    // List<LeaveQuota> findByUser(@Param("user") User user);
   // LeaveQuota findByYear(int year);
    // @Query("SELECT DISTINCT lq FROM LeaveQuota lq JOIN FETCH lq.users")
    // List<LeaveQuota> findAllWithUsers();
    // @Query("SELECT DISTINCT lq FROM LeaveQuota lq JOIN FETCH lq.users")
    // List<LeaveQuota> findAllWithUsers();
}
