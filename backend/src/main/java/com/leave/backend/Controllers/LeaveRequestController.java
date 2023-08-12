package com.leave.backend.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.leave.backend.Dtos.LeaveRequestCreationDTO;
import com.leave.backend.Exceptions.EmployeNotFoundException;
import com.leave.backend.Exceptions.RemplacantNotAvailableException;
import com.leave.backend.Services.LeaveRequestService;

@RestController @CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/leave-requests")
public class LeaveRequestController {

    private final LeaveRequestService leaveRequestService;

    @Autowired
    public LeaveRequestController(LeaveRequestService leaveRequestService) {
        this.leaveRequestService = leaveRequestService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createLeaveRequest(@RequestBody LeaveRequestCreationDTO dto) {
        try {
            LeaveRequestCreationDTO createdDto = leaveRequestService.createLeaveRequest(dto);
            return ResponseEntity.ok(createdDto);
        } catch (EmployeNotFoundException | RemplacantNotAvailableException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
