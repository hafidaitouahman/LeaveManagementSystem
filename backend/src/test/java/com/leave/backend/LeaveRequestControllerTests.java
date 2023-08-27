// package com.leave.backend;
// import org.junit.jupiter.api.Test;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.springframework.boot.test.context.SpringBootTest;

// import com.leave.backend.Controllers.LeaveRequestController;
// import com.leave.backend.Dtos.EmployeDTO;
// import com.leave.backend.Dtos.LeaveRequestCreationDTO;
// import com.leave.backend.Dtos.LeaveRequestDTO;
// import com.leave.backend.Dtos.LeaveTypeDTO;
// import com.leave.backend.Dtos.RemplacantDTO;
// import com.leave.backend.Dtos.RhDTO;
// import com.leave.backend.Repositories.LeaveRequestRepository;
// import com.leave.backend.Services.LeaveRequestService;
// import com.leave.backend.Services.Implementation.LeaveRequestServiceImpl;

// import static org.mockito.ArgumentMatchers.any;
// import static org.mockito.Mockito.*;

// import java.time.LocalDate;
// import java.util.ArrayList;
// import java.util.Collections;
// import java.util.List;

// @SpringBootTest
// class LeaveRequestControllerTests {

//     @Mock
//     private LeaveRequestService leaveRequestService;

//     @InjectMocks
//     private LeaveRequestController leaveRequestController;

//     @Test
//     void testCreateLeaveRequest() {
//     //     LeaveRequestCreationDTO requestDTO = new LeaveRequestCreationDTO();
//     //     requestDTO.setType(new LeaveTypeDTO("Vacation"));
//     //     requestDTO.setStartDate(LocalDate.of(2023, 8, 10));
//     //     requestDTO.setEndDate(LocalDate.of(2023, 8, 15));

//     //     List<RhDTO> approvers = new ArrayList<>();
//     //     approvers.add(new RhDTO("John Doe"));
//     //     requestDTO.setApprovers(approvers);

//     //     List<RemplacantDTO> remplacants = new ArrayList<>();
//     //     remplacants.add(new RemplacantDTO("Jane Smith"));
//     //     requestDTO.setRemplacants(remplacants);

//     //     List<EmployeDTO> employes = new ArrayList<>();
//     //     employes.add(new EmployeDTO("Alice"));
//     //     requestDTO.setEmployes(employes);

//     //     requestDTO.setComment("Vacation request for some time off"); 
//     //     doNothing().when(leaveRequestService).createLeaveRequest(any());

//     //     leaveRequestController.createLeaveRequest(requestDTO);

//     //     verify(leaveRequestService, times(1)).createLeaveRequest(requestDTO);
//     // }

//     // @Test
//     // void testGetLeaveRequestDetails() {
//     //     int requestId = 1;
//     //     LeaveRequestDTO leaveRequestDTO = new LeaveRequestDTO();
//     //     Initialize leaveRequestDTO with necessary data

//     //     when(leaveRequestService.getLeaveRequestDetails(requestId)).thenReturn(leaveRequestDTO);

//     //     leaveRequestController.getLeaveRequestDetails(requestId);

//     //     verify(leaveRequestService, times(1)).getLeaveRequestDetails(requestId);
//     // }

//     }}