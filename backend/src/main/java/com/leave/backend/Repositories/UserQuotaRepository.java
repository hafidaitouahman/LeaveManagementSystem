package com.leave.backend.Repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.leave.backend.Entities.LeaveQuota;
import com.leave.backend.Entities.User;
import com.leave.backend.Entities.UserQuota;



@Repository
public interface UserQuotaRepository extends JpaRepository<UserQuota, UserQuota.UserQuotaId>{
    List<UserQuota> findByLeaveQuota(LeaveQuota leaveQuota);
    void deleteByLeaveQuota(LeaveQuota leaveQuota);
    UserQuota findByUserIdAndYear(Long userId, int year);
    List<UserQuota> findByUser(User user);
    



}