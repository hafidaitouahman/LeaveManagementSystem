package com.leave.backend.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.leave.backend.Entities.LeaveQuota;
import com.leave.backend.Entities.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByUsername(String username);

  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);
  // @Query("SELECT u FROM User u JOIN u.quotas q WHERE q.id = :leaveQuotaId")
  // List<User> findByLeaveQuotaId(@Param("leaveQuotaId") Long leaveQuotaId);
  List<User> findByPays(String pays);

}