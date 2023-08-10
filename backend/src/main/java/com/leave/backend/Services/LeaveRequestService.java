package com.leave.backend.Services;

import com.leave.backend.Dtos.LeaveRequestCreationDTO;


public interface LeaveRequestService {
    LeaveRequestCreationDTO createLeaveRequest(LeaveRequestCreationDTO dto);
}

