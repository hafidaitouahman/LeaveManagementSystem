package com.leave.backend.Services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.leave.backend.Entities.LeaveQuota;
import com.leave.backend.Entities.LeaveRequest;
import com.leave.backend.Entities.User;
import com.leave.backend.Exceptions.UserNotFoundException;

public interface UserService {
     List<User> getAllUsers();
     void deleteUser(Long userId);
     User activateUser(Long userId);
     User deactivateUser(Long userId);
     List<LeaveQuota> getQuotaByEmployeeId(Long userId);
     List<LeaveRequest> getLeaveRequestsByEmployeeId(Long employeeId);
     User updateUserProfile(Long userId, int departementId, int teamId, int siteId, Date hireDate);
     User getUserById(Long userId);
     List<User> findUsersByIds(List<Long> userIds);
     User findUserById(Long userId) throws UserNotFoundException;

}
