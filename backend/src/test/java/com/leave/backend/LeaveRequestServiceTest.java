package com.leave.backend;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.leave.backend.Dtos.LeaveRequestCreationDTO;
import com.leave.backend.Dtos.RemplacantDTO;
import com.leave.backend.Dtos.RhDTO;
import com.leave.backend.Entities.Employe;
import com.leave.backend.Entities.LeaveRequest;
import com.leave.backend.Repositories.LeaveRequestRepository;
import com.leave.backend.Services.LeaveRequestServiceImpl;
import com.leave.backend.mappers.EmployeMapper;
import com.leave.backend.mappers.LeaveRequestMapper;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;



import java.util.Date;
import java.util.Calendar;


@SpringBootTest
public class LeaveRequestServiceTest {
    @Mock
    private LeaveRequestRepository leaveRequestRepository;

    @Mock
    private LeaveRequestMapper leaveRequestMapper;

    @Mock
    private EmployeMapper employeMapper; // Assuming you have this mapper
    
    @InjectMocks
    private LeaveRequestServiceImpl leaveRequestService;

    @Test
    public void testCreateLeaveRequest() {
        // Create a sample RemplacantDTO
        RemplacantDTO remplacantDTO = new RemplacantDTO();
        remplacantDTO.setName("Salma Mouayad");
        
        // Create a sample RhDTO
        RhDTO rhDTO = new RhDTO();
        rhDTO.setName("Hala Mouayad");

        // Create a sample LeaveRequestCreationDTO
        LeaveRequestCreationDTO dto = new LeaveRequestCreationDTO();
        dto.setStartDate(new Date()); // Current date
        dto.setEndDate(addDays(new Date(), 5)); // Current date + 5 days
        dto.setComment("My Vacation");
        dto.setRemplacant(remplacantDTO);
        dto.setApprover(rhDTO);
        
        // Mock the behavior of the employe mapper
        Employe remplacantEmploye = new Employe();
        when(employeMapper.fromRemplacantDTO(remplacantDTO)).thenReturn(remplacantEmploye);
// Mock the behavior of the employe mapper for RhDTO
        Employe rhEmploye = new Employe();
        when(employeMapper.fromRhDTO(rhDTO)).thenReturn(rhEmploye);

        // Mock the behavior of the mapper
        LeaveRequest leaveRequest = new LeaveRequest();
        leaveRequest.setStartDate(dto.getStartDate());
        leaveRequest.setEndDate(dto.getEndDate());
        leaveRequest.setComment(dto.getComment());
        // Set other properties as needed
        when(leaveRequestMapper.fromLeaveRequestCreationDTO(dto)).thenReturn(leaveRequest);

        // Mock the behavior of the repository
        when(leaveRequestRepository.save(any())).thenReturn(leaveRequest);

        // Test the service method
        LeaveRequestCreationDTO result = leaveRequestService.createLeaveRequest(dto);

        // Assertions
        assertEquals(dto.getStartDate(), result.getStartDate());
        assertEquals(dto.getEndDate(), result.getEndDate());
        assertEquals(dto.getComment(), result.getComment());
    }

    // Helper method to add days to a date
    private Date addDays(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, days);
        return calendar.getTime();
    }
}

