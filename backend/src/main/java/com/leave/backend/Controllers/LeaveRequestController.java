package com.leave.backend.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.leave.backend.Dtos.LeaveRequestCreationDTO;
import com.leave.backend.Dtos.LeaveRequestDTO;
import com.leave.backend.Services.LeaveRequestService;

@RestController
@RequestMapping("/api/leave-requests")
public class LeaveRequestController {
    private final LeaveRequestService leaveRequestService;

    @Autowired
    public LeaveRequestController(LeaveRequestService leaveRequestService) {
        this.leaveRequestService = leaveRequestService;
    }

    @PostMapping
    public ResponseEntity<String> createLeaveRequest(@RequestBody LeaveRequestCreationDTO requestDTO) {
        leaveRequestService.createLeaveRequest(requestDTO);
        return new ResponseEntity<>("Leave request created successfully", HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LeaveRequestDTO> getLeaveRequestDetails(@PathVariable int id) {
        LeaveRequestDTO leaveRequestDTO = leaveRequestService.getLeaveRequestDetails(id);
        return new ResponseEntity<>(leaveRequestDTO, HttpStatus.OK);
    }
}
