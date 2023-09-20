package com.leave.backend.Services;

import java.util.List;

import com.leave.backend.Dtos.LeaveTypeDTO;
import com.leave.backend.Exceptions.LeaveTypeNotFoundException;

public interface LeaveTypeService {
    LeaveTypeDTO addLeaveType(LeaveTypeDTO leaveTypeDTO);
    LeaveTypeDTO updateLeaveType(Long id,LeaveTypeDTO leaveTypeDTO) throws LeaveTypeNotFoundException;
         List<LeaveTypeDTO> getAllLeaveTypes();
     void deleteLeaveTypeById(Long id);
     LeaveTypeDTO findById(Long id) throws LeaveTypeNotFoundException;
}
