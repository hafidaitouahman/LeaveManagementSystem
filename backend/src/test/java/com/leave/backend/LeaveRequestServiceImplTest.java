package com.leave.backend;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.leave.backend.Dtos.LeaveRequestCreationDTO;
import com.leave.backend.Entities.Employe;
import com.leave.backend.Entities.LeaveRequest;
import com.leave.backend.Enumeration.Role;
import com.leave.backend.Exceptions.EmployeNotFoundException;
import com.leave.backend.Exceptions.RemplacantNotAvailableException;
import com.leave.backend.Repositories.EmployeRepository;
import com.leave.backend.Repositories.LeaveRequestRepository;
import com.leave.backend.Services.Implementation.LeaveRequestServiceImpl;
import com.leave.backend.mappers.LeaveRequestMapper;

@RunWith(MockitoJUnitRunner.class)
public class LeaveRequestServiceImplTest {

    @Mock
    private LeaveRequestMapper leaveRequestMapper;

    @Mock
    private EmployeRepository employeRepository;

    @Mock
    private LeaveRequestRepository leaveRequestRepository;

    @InjectMocks
    private LeaveRequestServiceImpl leaveRequestService;

    @Test
    public void testCreateLeaveRequest() throws EmployeNotFoundException, RemplacantNotAvailableException {
        // Create your input DTO
        LeaveRequestCreationDTO dto = new LeaveRequestCreationDTO();
        dto.setApprover("rhName");
        dto.setRemplacant("remplacantName");
        // Set other attributes as needed

        // Create mock Employe entities
        Employe rhEntity = new Employe();
        rhEntity.setName("rhName");
        Employe remplacantEntity = new Employe();
        remplacantEntity.setName("remplacantName");

        // Mock the repository responses
        when(employeRepository.findByRoleAndName(eq(Role.RH), eq("rhName"))).thenReturn(rhEntity);
        when(employeRepository.findByRoleAndName(eq(Role.USER), eq("remplacantName"))).thenReturn(remplacantEntity);

        // Mock the LeaveRequestMapper
        when(leaveRequestMapper.fromLeaveRequestCreationDTO(dto)).thenReturn(new LeaveRequest());

        // Call the method being tested
        LeaveRequestCreationDTO resultDto = leaveRequestService.createLeaveRequest(dto);

        // Assertions or verifications as needed
        verify(leaveRequestRepository).save(any(LeaveRequest.class));
        // You can also add assertions on the resultDto if needed
    }
}
