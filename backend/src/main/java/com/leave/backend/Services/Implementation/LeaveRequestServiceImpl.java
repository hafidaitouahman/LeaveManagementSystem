package com.leave.backend.Services.Implementation;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leave.backend.Dtos.EmployeDTO;
import com.leave.backend.Dtos.LeaveRequestCreationDTO;
import com.leave.backend.Dtos.LeaveRequestDTO;
import com.leave.backend.Dtos.LeaveTypeDTO;
import com.leave.backend.Dtos.RemplacantDTO;
import com.leave.backend.Entities.Employe;
import com.leave.backend.Entities.LeaveRequest;
import com.leave.backend.Entities.LeaveType;

import com.leave.backend.Exceptions.EmployeNotFoundException;
import com.leave.backend.Exceptions.RemplacantNotAvailableException;
import com.leave.backend.Repositories.EmployeRepository;
import com.leave.backend.Repositories.LeaveRequestRepository;
import com.leave.backend.Services.LeaveRequestService;
import com.leave.backend.mappers.EmployeMapper;
import com.leave.backend.mappers.LeaveRequestMapper;
import com.leave.backend.mappers.LeaveTypeMapper;




@Service
public class LeaveRequestServiceImpl implements LeaveRequestService {

    private final LeaveRequestMapper leaveRequestMapper;
    private final EmployeRepository employeRepository;
    private final LeaveRequestRepository leaveRequestRepository; // You need to inject this repository

    public LeaveRequestServiceImpl(LeaveRequestMapper leaveRequestMapper, EmployeRepository employeRepository,
                                   LeaveRequestRepository leaveRequestRepository) {
        this.leaveRequestMapper = leaveRequestMapper;
        this.employeRepository = employeRepository;
        this.leaveRequestRepository = leaveRequestRepository;
    }

    @Override
    public LeaveRequestCreationDTO createLeaveRequest(LeaveRequestCreationDTO dto)
            throws EmployeNotFoundException, RemplacantNotAvailableException {
        // Convert the DTO fields to entities and fetch employe entities
        // Employe rhEntity = employeRepository.findByRoleAndName(Role.RH, dto.getApprover());
        // Employe remplacantEntity = employeRepository.findByRoleAndName(Role.USER, dto.getRemplacant());

        // Map the DTO fields and employe entities to LeaveRequestCreationDTO
        // ...

        // Map LeaveRequestCreationDTO to LeaveRequest entity
        LeaveRequest leaveRequestEntity = leaveRequestMapper.fromLeaveRequestCreationDTO(dto);
        // leaveRequestEntity.setEmploye(rhEntity); // Set the approver (RH)
        // leaveRequestEntity.setRemplacant(remplacantEntity); // Set the remplacant

        // Save the leave request entity in the database using the repository
        leaveRequestRepository.save(leaveRequestEntity);

        return dto;
    }
}


// @Service
// public class LeaveRequestServiceImpl implements LeaveRequestService {
//     private final LeaveRequestMapper leaveRequestMapper;
//     private final LeaveRequestRepository leaveRequestRepository;
//     private final EmployeRepository employeRepository;
//     private final LeaveTypeMapper leaveTypeMapper;
//     private final EmployeMapper employeMapper;

//     public LeaveRequestServiceImpl(LeaveRequestMapper leaveRequestMapper, LeaveRequestRepository leaveRequestRepository,
//                                    EmployeRepository employeRepository,
//                                    LeaveTypeMapper leaveTypeMapper, EmployeMapper employeMapper) {
//         this.leaveRequestMapper = leaveRequestMapper;
//         this.leaveRequestRepository = leaveRequestRepository;
//         this.employeRepository = employeRepository;
//         this.leaveTypeMapper = leaveTypeMapper;
//         this.employeMapper=employeMapper;
//     }

//     @Override
//     public LeaveRequestCreationDTO createLeaveRequest(EmployeDTO employe, RemplacantDTO remplacant,
//                                                       Date startDate, Date endDate, String comment,
//                                                       LeaveTypeDTO leaveType, EmployeDTO rh)
//             throws EmployeNotFoundException, RemplacantNotAvailableException {
//         // Check if start date is before end date
//         if (startDate.after(endDate)) {
//             throw new IllegalArgumentException("Start date must be before end date.");
//         }

//         // Get the employe entity from the repository
//         Employe employeEntity = employeRepository.findById(employe.getId())
//                 .orElseThrow(() -> new EmployeNotFoundException("Employe not found"));

//         // Check if the selected remplacant is available
//         if (!isRemplacantAvailable(remplacant, startDate, endDate)) {
//             throw new RemplacantNotAvailableException("Selected remplacant is not available for the specified dates.");
//         }

//         // Get the RH (approver) employe entity from the repository
//         Employe rhEntity = employeRepository.findById(rh.getId())
//                 .orElseThrow(() -> new EmployeNotFoundException("RH not found"));

//         // Convert LeaveTypeDTO to LeaveType entity using your mapping logic
//         LeaveType leaveTypeEntity = leaveTypeMapper.fromLeaveTypeDTO(leaveType);
//         Employe remplacantEntity = employeMapper.fromEmployeDTO(remplacantDTO);

//         // Create the LeaveRequest entity
//         LeaveRequest leaveRequestEntity = new LeaveRequest();
//         leaveRequestEntity.setEmploye(employeEntity);
//         leaveRequestEntity.setRemplacant(remplacant);
//         leaveRequestEntity.setLeaveType(leaveTypeEntity);
//         leaveRequestEntity.setStartDate(startDate);
//         leaveRequestEntity.setEndDate(endDate);
//         leaveRequestEntity.setComment(comment);
//         // Set other attributes as needed
        
//         // Save the leave request entity in the database using the repository
//         LeaveRequest savedLeaveRequest = leaveRequestRepository.save(leaveRequestEntity);

//         // Convert the saved LeaveRequest entity to LeaveRequestCreationDTO using the mapper
//         LeaveRequestCreationDTO savedLeaveRequestDTO = leaveRequestMapper.toLeaveRequestCreationDTO(savedLeaveRequest);

//         // Return the saved leave request DTO
//         return savedLeaveRequestDTO;
//     }

//     private boolean isRemplacantAvailable(RemplacantDTO remplacant, Date startDate, Date endDate) {
//         // Implement your logic here to check if the remplacant is available
//         // You can fetch the remplacant's leave requests and check for overlapping
//     }
// }


   
// }

// @Service
// public class LeaveRequestServiceImpl implements LeaveRequestService {
//     private final LeaveRequestMapper leaveRequestMapper;
//     private final LeaveRequestRepository leaveRequestRepository;
//     private final RemplacantRepository remplacantRepository; 

//     public LeaveRequestServiceImpl(LeaveRequestMapper leaveRequestMapper, LeaveRequestRepository leaveRequestRepository,
//                                    RemplacantRepository remplacantRepository) {
//         this.leaveRequestMapper = leaveRequestMapper;
//         this.leaveRequestRepository = leaveRequestRepository;
//         this.remplacantRepository = remplacantRepository;
//     }

//     @Override
//     public LeaveRequestCreationDTO createLeaveRequest(LeaveRequestCreationDTO dto) {
//         // Check if start date is before end date
//         if (dto.getStartDate().isAfter(dto.getEndDate())) {
//             throw new IllegalArgumentException("Start date must be before end date.");
//         }

//         // Check if any overlapping leave requests for the selected remplacant
//         boolean hasOverlappingRequests = checkOverlappingRequests(dto.getRemplacant(), dto.getStartDate(), dto.getEndDate());
//         if (hasOverlappingRequests) {
//             throw new IllegalStateException("Selected remplacant has overlapping leave requests.");
//         }

//         // Perform your mapping operations here
        
//         // Save the leave request entity in the database using the repository
//         LeaveRequest savedLeaveRequest = leaveRequestRepository.save(leaveRequestEntity);

//         // Perform your mapping operations for the result here
        
//         // Return the saved leave request DTO
//         return leaveRequestDTO;
//     }

//     private boolean checkOverlappingRequests(RemplacantDTO remplacantDTO, Date startDate, Date endDate) {
//         // Get the remplacant entity using the repository
//         Remplacant remplacant = remplacantRepository.findById(remplacantDTO.getId()).orElse(null);
        
//         if (remplacant == null) {
//             throw new IllegalArgumentException("Selected remplacant not found.");
//         }

//         // Check if there are any overlapping leave requests for the selected remplacant
//         List<LeaveRequest> overlappingRequests = leaveRequestRepository.findOverlappingRequests(remplacant, startDate, endDate);
//         return !overlappingRequests.isEmpty();
//     }
// }

// @Service
// public class LeaveRequestServiceImpl implements LeaveRequestService {
//     private final LeaveRequestMapper leaveRequestMapper;
//     private final LeaveRequestRepository leaveRequestRepository;

//     public LeaveRequestServiceImpl(LeaveRequestMapper leaveRequestMapper, LeaveRequestRepository leaveRequestRepository) {
//         this.leaveRequestMapper = leaveRequestMapper;
//         this.leaveRequestRepository = leaveRequestRepository;
//     }

//     @Override
//     public LeaveRequestCreationDTO createLeaveRequest(LeaveRequestCreationDTO dto) {
//         LeaveRequest leaveRequest = leaveRequestMapper.fromLeaveRequestCreationDTO(dto);
//         leaveRequestRepository.save(leaveRequest);
//         return dto;
//     }
// }

// @Service
// public class LeaveRequestServiceImp implements LeaveRequestService {

//     @Override
//     public void createLeaveRequest(LeaveRequestCreationDTO requestDTO) {
//         // TODO Auto-generated method stub
//         throw new UnsupportedOperationException("Unimplemented method 'createLeaveRequest'");
//     }

//     @Override
//     public LeaveRequestDTO getLeaveRequestDetails(int requestId) {
//         // TODO Auto-generated method stub
//         throw new UnsupportedOperationException("Unimplemented method 'getLeaveRequestDetails'");
//     }
    // private final EmployeMapper employeMapper;
    // private final LeaveRequestMapper leaveRequestMapper;
    // private final LeaveRequestRepository leaveRequestRepository;

    // @Autowired
    // public LeaveRequestServiceImp(
    //         EmployeMapper employeMapper,
    //         LeaveRequestMapper leaveRequestMapper,
    //         LeaveRequestRepository leaveRequestRepository
    // ) {
    //     this.employeMapper = employeMapper;
    //     this.leaveRequestMapper = leaveRequestMapper;
    //     this.leaveRequestRepository = leaveRequestRepository;
    // }

    // @Override
    // public void createLeaveRequest(LeaveRequestCreationDTO requestDTO) {
    //     Employe rh = employeMapper.rhDTOToEmploye(requestDTO.getApprovers().get(0));
    //     Employe remplacant = employeMapper.remplacantDTOToEmploye(requestDTO.getRemplacants().get(0));

    //     LeaveRequest leaveRequest = leaveRequestMapper.leaveRequestCreationDTOToLeaveRequest(requestDTO);
    //     leaveRequest.setEmploye(rh);
    //     leaveRequest.setRemplacant(remplacant);

    //     leaveRequestRepository.save(leaveRequest);
    // }

    // @Override
    // public LeaveRequestDTO getLeaveRequestDetails(int requestId) {
    //     LeaveRequest leaveRequest = leaveRequestRepository.findById(requestId)
    //             .orElseThrow(() -> new RuntimeException("Leave request not found"));

    //     return leaveRequestMapper.leaveRequestToLeaveRequestDTO(leaveRequest);
    // }

