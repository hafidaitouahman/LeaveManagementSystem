// package com.leave.backend;
// import static org.junit.Assert.assertEquals;
// import static org.mockito.Mockito.*;

// import org.junit.Test;
// import org.junit.runner.RunWith;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.junit.MockitoJUnitRunner;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;

// import com.leave.backend.Controllers.LeaveRequestController;
// import com.leave.backend.Dtos.LeaveRequestCreationDTO;
// import com.leave.backend.Entities.Employe;
// import com.leave.backend.Entities.LeaveRequest;
// //import com.leave.backend.Enumeration.Role;
// import com.leave.backend.Exceptions.EmployeNotFoundException;
// import com.leave.backend.Exceptions.RemplacantNotAvailableException;
// import com.leave.backend.Repositories.EmployeRepository;
// import com.leave.backend.Repositories.LeaveRequestRepository;
// import com.leave.backend.Services.LeaveRequestService;
// import com.leave.backend.Services.Implementation.LeaveRequestServiceImpl;
// import com.leave.backend.mappers.LeaveRequestMapper;

// @RunWith(MockitoJUnitRunner.class)
// public class LeaveRequestControllerTest {

//     @Mock
//     private LeaveRequestService leaveRequestService;

//     @InjectMocks
//     private LeaveRequestController leaveRequestController;

//     @Test
//     public void testCreateLeaveRequest() throws EmployeNotFoundException, RemplacantNotAvailableException {
//         LeaveRequestCreationDTO dto = new LeaveRequestCreationDTO();
//         // Set DTO fields as needed

//         LeaveRequestCreationDTO createdDto = new LeaveRequestCreationDTO();
//         // Set created DTO fields as needed

//         when(leaveRequestService.createLeaveRequest(dto)).thenReturn(createdDto);

//         ResponseEntity<?> response = leaveRequestController.createLeaveRequest(dto);

//         assertEquals(HttpStatus.OK, response.getStatusCode());
//         assertEquals(createdDto, response.getBody());
//     }

//     @Test
//     public void testCreateLeaveRequestWithError() throws EmployeNotFoundException, RemplacantNotAvailableException {
//         LeaveRequestCreationDTO dto = new LeaveRequestCreationDTO();
//         // Set DTO fields as needed

//         String errorMessage = "Error message";
//         when(leaveRequestService.createLeaveRequest(dto)).thenThrow(new EmployeNotFoundException(errorMessage));

//         ResponseEntity<?> response = leaveRequestController.createLeaveRequest(dto);

//         assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
//         assertEquals(errorMessage, response.getBody());
//     }
// }
