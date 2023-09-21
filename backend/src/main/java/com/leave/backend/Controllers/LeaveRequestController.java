 package com.leave.backend.Controllers;
 import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.leave.backend.Dtos.LeaveRequestCreationDTO;
import com.leave.backend.Dtos.LeaveRequestDTOResponse;
import com.leave.backend.Entities.LeaveRequest;
import com.leave.backend.Exceptions.EmployeNotFoundException;
import com.leave.backend.Exceptions.InsufficientLeaveQuotaException;
import com.leave.backend.Exceptions.LeaveRequestNotFoundException;
import com.leave.backend.Exceptions.LeaveTypeNotFoundException;
import com.leave.backend.Exceptions.RemplacantNotAvailableException;
import com.leave.backend.Exceptions.UserNotFoundException;
import com.leave.backend.Security.services.UserDetailsImpl;
import com.leave.backend.Services.LeaveRequestService;
import com.leave.backend.Services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/leaverequests")
public class LeaveRequestController {
    @Autowired
    private LeaveRequestService leaveRequestService;
    @GetMapping("/calculate-planned-days")
    public double calculatePlannedDays(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        // Appelez la méthode de calcul des jours planifiés en utilisant les dates fournies
        return calculatePlannedDays(startDate, endDate);
    }
    @GetMapping("/pending/{userId}")
    @PreAuthorize("hasRole('RH')")
    public ResponseEntity<List<LeaveRequestDTOResponse>> getPendingLeaveRequestsByUserId(@PathVariable Long userId) {
        List<LeaveRequestDTOResponse> leaveRequests = leaveRequestService.getPendingLeaveRequestsByUserId(userId);
        return ResponseEntity.ok(leaveRequests);
    }

    @GetMapping("/histo/{userId}")
    @PreAuthorize("hasRole('RH')")
    public ResponseEntity<List<LeaveRequestDTOResponse>> getLeaveRequestsByUserId(@PathVariable Long userId) {
        List<LeaveRequestDTOResponse> leaveRequests = leaveRequestService.getLeaveRequestsByUserId(userId);
        return ResponseEntity.ok(leaveRequests);
    }
    @GetMapping("/pending")
    @PreAuthorize("hasRole('RH')")
public ResponseEntity<List<LeaveRequestDTOResponse>> getPendingLeaveRequests() {
    List<LeaveRequestDTOResponse> pendingLeaveRequests = leaveRequestService.getPendingLeaveRequests();
    return ResponseEntity.ok(pendingLeaveRequests);
}

    @GetMapping("/user")
    public ResponseEntity<List<LeaveRequestDTOResponse>> getAllLeaveRequestsForCurrentUser(
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        try {
            Long userId = userDetails.getId();
            List<LeaveRequestDTOResponse> leaveRequests = leaveRequestService.getAllLeaveRequestsForUser(userId);
            return ResponseEntity.ok(leaveRequests);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{leaveRequestId}")
    public ResponseEntity<?> updateLeaveRequest(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable Long leaveRequestId,
            @RequestBody LeaveRequestCreationDTO updateDTO
    ) {
        try {
            Long userId = userDetails.getId();
            LeaveRequestDTOResponse response = leaveRequestService.updateLeaveRequest(userId, leaveRequestId, updateDTO);
            return ResponseEntity.ok(response);
        } catch (LeaveRequestNotFoundException | UserNotFoundException | 
                 InsufficientLeaveQuotaException | AccessDeniedException | LeaveTypeNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
 @PostMapping("/create")
    public ResponseEntity<?> createLeaveRequest(
            @RequestBody LeaveRequestCreationDTO requestDTO,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        try {
            Long userId = userDetails.getId();
            LeaveRequestDTOResponse response = leaveRequestService.createLeaveRequest(requestDTO, userId);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (LeaveTypeNotFoundException | UserNotFoundException | InsufficientLeaveQuotaException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    // @GetMapping("/user")
    // public ResponseEntity<List<LeaveRequestDTOResponse>> getLeaveRequestsForUser(Authentication authentication) {
    //     List<LeaveRequestDTOResponse> leaveRequests = leaveRequestService.getLeaveRequestsForAuthenticatedUser(authentication);
    //     return ResponseEntity.ok(leaveRequests);
    // }
    // @GetMapping("/all")
    // public ResponseEntity<List<LeaveRequestDTOResponse>> getAllLeaveRequests() {
    //     List<LeaveRequestDTOResponse> leaveRequests = leaveRequestService.getAllLeaveRequests();
    //     return ResponseEntity.ok(leaveRequests);
    // }
    @GetMapping("/all")
    public List<LeaveRequest> getAllLeaveRequests() {
        return leaveRequestService.getAllLeaveRequests();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LeaveRequest> getLeaveRequestById(@PathVariable Long id) throws LeaveRequestNotFoundException {
        LeaveRequest leaveRequest = leaveRequestService.getLeaveRequestById(id);
        return ResponseEntity.ok(leaveRequest);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteLeaveRequest(@PathVariable Long id) {
        leaveRequestService.deleteLeaveRequest(id);
        return ResponseEntity.ok("Leave request with ID " + id + " has been deleted successfully.");
    }
}

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;
// import com.leave.backend.Dtos.LeaveRequestCreationDTO;
// import com.leave.backend.Exceptions.EmployeNotFoundException;
// import com.leave.backend.Exceptions.RemplacantNotAvailableException;
// import com.leave.backend.Services.LeaveRequestService;

// @RestController @CrossOrigin(origins = "http://localhost:4200")
// @RequestMapping("/leave-requests")
// public class LeaveRequestController {

//     private final LeaveRequestService leaveRequestService;

//     @Autowired
//     public LeaveRequestController(LeaveRequestService leaveRequestService) {
//         this.leaveRequestService = leaveRequestService;
//     }

//     @PostMapping("/create")
//     public ResponseEntity<?> createLeaveRequest(@RequestBody LeaveRequestCreationDTO dto) {
//         try {
//             LeaveRequestCreationDTO createdDto = leaveRequestService.createLeaveRequest(dto);
//             return ResponseEntity.ok(createdDto);
//         } catch (EmployeNotFoundException | RemplacantNotAvailableException e) {
//             return ResponseEntity.badRequest().body(e.getMessage());
//         }
//     }
// }
