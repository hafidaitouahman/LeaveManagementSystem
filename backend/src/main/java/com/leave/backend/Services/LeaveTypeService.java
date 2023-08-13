package com.leave.backend.Services;

import java.util.List;

import com.leave.backend.Dtos.LeaveTypeDTO;
import com.leave.backend.Exceptions.LeaveTypeNotFoundException;

public interface LeaveTypeService {
    LeaveTypeDTO addLeaveType(LeaveTypeDTO leaveTypeDTO);
    LeaveTypeDTO updateLeaveType(int id,LeaveTypeDTO leaveTypeDTO) throws LeaveTypeNotFoundException;
         List<LeaveTypeDTO> getAllLeaveTypes();
     void deleteLeaveTypeById(int id);
     LeaveTypeDTO findById(int id) throws LeaveTypeNotFoundException;
}
