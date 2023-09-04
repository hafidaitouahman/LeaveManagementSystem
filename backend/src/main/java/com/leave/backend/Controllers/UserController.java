package com.leave.backend.Controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.leave.backend.Dtos.SiteDTO;
import com.leave.backend.Entities.LeaveQuota;
import com.leave.backend.Entities.LeaveRequest;
import com.leave.backend.Entities.User;
import com.leave.backend.Exceptions.SiteNotFoundException;
import com.leave.backend.Services.SiteService;
import com.leave.backend.Services.UserService;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
    @GetMapping("/{userId}")
        public ResponseEntity<User> getUserById(@PathVariable Long userId) {
            try {
                User user = userService.getUserById(userId);
                return ResponseEntity.ok(user);
            } catch (EntityNotFoundException e) {
                // Handle the case where the user is not found
                return ResponseEntity.notFound().build();
            }
        }
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok("User deleted successfully");
    }
    // @PutMapping("/{userId}")
    // @PreAuthorize("hasRole('RH')")
    // public ResponseEntity<User> updateUserProfile(
    //         @PathVariable Long userId,
        
    //         @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date hireDate) {
    //     User updatedUser = userService.updateUserProfile(userId, hireDate);
    //     return ResponseEntity.ok(updatedUser);
    // }

    // @PutMapping("/{userId}")
    // public ResponseEntity<User> updateUserProfile(@PathVariable Long userId, @RequestBody User updatedUser) {
    //     User user = userService.updateUserProfile(userId, updatedUser);
    //     return ResponseEntity.ok(user);
    // }


    @PutMapping("/{userId}")
public ResponseEntity<User> updateUserProfile(
        @PathVariable Long userId,
        @RequestParam int departementId,
        @RequestParam int teamId,
        @RequestParam int siteId,
        @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date hireDate) {
    try {
        User updatedUser = userService.updateUserProfile(userId, departementId, teamId, siteId, hireDate);
        return ResponseEntity.ok(updatedUser);
    } catch (EntityNotFoundException e) {
        // Handle the case where the user is not found
        return ResponseEntity.notFound().build();
    }
}


    @PutMapping("/{userId}/deactivate")
    public ResponseEntity<User> deactivateUser(@PathVariable Long userId) {
        User user = userService.deactivateUser(userId);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{userId}/activate")
    public ResponseEntity<User> activateUser(@PathVariable Long userId) {
        User user = userService.activateUser(userId);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/{userId}/quota")
    public ResponseEntity<List<LeaveQuota>> getQuotaByEmployeeId(@PathVariable Long userId) {
        List<LeaveQuota> quotas = userService.getQuotaByEmployeeId(userId);
        return ResponseEntity.ok(quotas);
    }

    @GetMapping("/{userId}/leave-requests")
    public ResponseEntity<List<LeaveRequest>> getLeaveRequestsByEmployeeId(@PathVariable Long userId) {
        List<LeaveRequest> leaveRequests = userService.getLeaveRequestsByEmployeeId(userId);
        return ResponseEntity.ok(leaveRequests);
    }
}
