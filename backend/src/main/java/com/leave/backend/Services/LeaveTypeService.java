package com.leave.backend.Services;

import java.util.List;

import com.leave.backend.Dtos.LeaveTypeDTO;

public interface LeaveTypeService {
    LeaveTypeDTO addLeaveType(LeaveTypeDTO leaveTypeDTO);
    LeaveTypeDTO updateLeaveType(LeaveTypeDTO leaveTypeDTO);
     List<LeaveTypeDTO> getAllLeaveTypes();
     void deleteLeaveTypeById(int id);
}
