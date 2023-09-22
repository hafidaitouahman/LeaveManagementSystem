package com.leave.backend.Services;

import org.springframework.security.access.AccessDeniedException;
import java.util.Date;
import java.util.List;

import com.leave.backend.Dtos.EmployeDTO;
import com.leave.backend.Dtos.LeaveRequestCreationDTO;
import com.leave.backend.Dtos.LeaveRequestDTOResponse;
import com.leave.backend.Dtos.LeaveTypeDTO;
import com.leave.backend.Dtos.RemplacantDTO;
import com.leave.backend.Entities.LeaveRequest;
import com.leave.backend.Exceptions.EmployeNotFoundException;
import com.leave.backend.Exceptions.InsufficientLeaveQuotaException;
import com.leave.backend.Exceptions.LeaveRequestNotFoundException;
import com.leave.backend.Exceptions.LeaveTypeNotFoundException;
import com.leave.backend.Exceptions.RemplacantNotAvailableException;
import com.leave.backend.Exceptions.UserNotFoundException;


public interface LeaveRequestService {
        // LeaveRequestCreationDTO createLeaveRequest(LeaveRequestCreationDTO dto)
        // throws EmployeNotFoundException, RemplacantNotAvailableException ;  // Your implementation here
//LeaveRequest createLeaveRequest(LeaveRequestCreationDTO requestDTO, Long userId) throws LeaveTypeNotFoundException,UserNotFoundException;
LeaveRequestDTOResponse createLeaveRequest(LeaveRequestCreationDTO requestDTO, Long userId)
        throws LeaveTypeNotFoundException, UserNotFoundException, InsufficientLeaveQuotaException;
//LeaveRequest getLeaveRequestById(Long leaveRequestId) throws LeaveRequestNotFoundException;
 //List<LeaveRequest> getAllLeaveRequests();
 void deleteLeaveRequest(Long leaveRequestId);
 //List<LeaveRequestDTOResponse> getAllLeaveRequests();
 List<LeaveRequest> getAllLeaveRequests();
 LeaveRequestDTOResponse updateLeaveRequest(Long userId, Long leaveRequestId, LeaveRequestCreationDTO updateDTO)
 throws LeaveRequestNotFoundException, UserNotFoundException, InsufficientLeaveQuotaException,AccessDeniedException,LeaveTypeNotFoundException;
 List<LeaveRequestDTOResponse> getAllLeaveRequestsForUser(Long userId);
 List<LeaveRequestDTOResponse> getPendingLeaveRequests();
 List<LeaveRequestDTOResponse> getLeaveRequestsByUserId(Long userId);
 List<LeaveRequestDTOResponse> getPendingLeaveRequestsByUserId(Long userId);
 LeaveRequestDTOResponse getLeaveRequestById(Long leaveRequestId) throws LeaveRequestNotFoundException;
}

