package com.leave.backend.Services;

import java.util.Date;

import com.leave.backend.Dtos.EmployeDTO;
import com.leave.backend.Dtos.LeaveRequestCreationDTO;
import com.leave.backend.Dtos.LeaveTypeDTO;
import com.leave.backend.Dtos.RemplacantDTO;
import com.leave.backend.Exceptions.EmployeNotFoundException;
import com.leave.backend.Exceptions.RemplacantNotAvailableException;


public interface LeaveRequestService {
        LeaveRequestCreationDTO createLeaveRequest(LeaveRequestCreationDTO dto)
        throws EmployeNotFoundException, RemplacantNotAvailableException ;  // Your implementation here

}

