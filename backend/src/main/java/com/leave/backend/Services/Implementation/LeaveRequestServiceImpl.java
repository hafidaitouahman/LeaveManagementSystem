package com.leave.backend.Services.Implementation;

import org.springframework.security.access.AccessDeniedException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leave.backend.Dtos.EmployeDTO;
import com.leave.backend.Dtos.LeaveRequestCreationDTO;
import com.leave.backend.Dtos.LeaveRequestDTO;
import com.leave.backend.Dtos.LeaveRequestDTOResponse;
import com.leave.backend.Dtos.LeaveTypeDTO;
import com.leave.backend.Dtos.RemplacantDTO;
import com.leave.backend.Entities.LeaveQuota;
import com.leave.backend.Entities.LeaveRequest;
import com.leave.backend.Entities.LeaveType;
import com.leave.backend.Entities.User;
import com.leave.backend.Entities.UserQuota;
import com.leave.backend.Enumeration.Status;
import com.leave.backend.Exceptions.EmployeNotFoundException;
import com.leave.backend.Exceptions.InsufficientLeaveQuotaException;
import com.leave.backend.Exceptions.LeaveRequestNotFoundException;
import com.leave.backend.Exceptions.LeaveTypeNotFoundException;
import com.leave.backend.Exceptions.RemplacantNotAvailableException;
import com.leave.backend.Exceptions.UserNotFoundException;
import com.leave.backend.Repositories.LeaveRequestRepository;
import com.leave.backend.Repositories.LeaveTypeRepository;
import com.leave.backend.Repositories.UserQuotaRepository;
import com.leave.backend.Repositories.UserRepository;
import com.leave.backend.Services.LeaveRequestService;
//import com.leave.backend.mappers.LeaveRequestMapper;
import com.leave.backend.mappers.LeaveRequestMapper;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class LeaveRequestServiceImpl implements LeaveRequestService {
    private final LeaveRequestRepository leaveRequestRepository;
    private final LeaveTypeRepository leaveTypeRepository;
    private final UserRepository userRepository;
    private final UserQuotaRepository userQuotaRepository;
    private final LeaveRequestMapper leaveRequestMapper;
    //  @Autowired
    // private LeaveRequestMapper leaveRequestMapper;
    // public LeaveRequestDTOResponse mapLeaveRequestToDTO(LeaveRequest leaveRequest) {
    //     return leaveRequestMapper.leaveRequestToDTO(leaveRequest);
    // }

    // public List<LeaveRequestDTOResponse> mapLeaveRequestsToDTOs(List<LeaveRequest> leaveRequests) {
    //     return leaveRequestMapper.leaveRequestsToDTOs(leaveRequests);
    // }
// @Override
//       public List<LeaveRequestDTOResponse> getAllLeaveRequests() {
//         List<LeaveRequest> leaveRequests = leaveRequestRepository.findAll();

//         List<LeaveRequestDTOResponse> responseList = new ArrayList<>();

//         for (LeaveRequest leaveRequest : leaveRequests) {
//             LeaveRequestDTOResponse response = mapLeaveRequestToDTO(leaveRequest);
//             responseList.add(response);
//         }

//         return responseList;
//     }

    // @Autowired
    // public LeaveRequestService(LeaveRequestRepository leaveRequestRepository, LeaveTypeRepository leaveTypeRepository, UserRepository userRepository) {
    //     this.leaveRequestRepository = leaveRequestRepository;
    //     this.leaveTypeRepository = leaveTypeRepository;
    //     this.userRepository = userRepository;
    // }


    //a revoir
// @Override
//     public LeaveRequest createLeaveRequest(LeaveRequestCreationDTO requestDTO, Long userId) throws LeaveTypeNotFoundException,UserNotFoundException {
//         LeaveType leaveType = leaveTypeRepository.findById(requestDTO.getLeaveTypeId())
//                 .orElseThrow(() -> new LeaveTypeNotFoundException("Leave type not found"));

//         User user = userRepository.findById(userId)
//                 .orElseThrow(() -> new UserNotFoundException("User not found"));

//         User approver = userRepository.findById(requestDTO.getApproverId())
//                 .orElseThrow(() -> new UserNotFoundException("Approver not found"));

// Set<User> replacements = new HashSet<>(userRepository.findAllById(requestDTO.getReplacementIds()));
//  // Calculate the duration of the leave
     

//         LeaveRequest leaveRequest = new LeaveRequest();
//         leaveRequest.setLeaveType(leaveType);
//         leaveRequest.setUser(user);
//         leaveRequest.setApprover(approver);
//         leaveRequest.setReplacements(replacements);
//         leaveRequest.setStartDate(requestDTO.getStartDate());
//         leaveRequest.setEndDate(requestDTO.getEndDate());
//         leaveRequest.setComment(requestDTO.getComment());

//         return leaveRequestRepository.save(leaveRequest);
//     }
@Override
public LeaveRequestDTOResponse getLeaveRequestById(Long leaveRequestId) throws LeaveRequestNotFoundException {
    LeaveRequest leaveRequest = leaveRequestRepository.findById(leaveRequestId)
            .orElseThrow(() -> new LeaveRequestNotFoundException("Leave request not found"));

    return leaveRequestMapper.convertToDTO(leaveRequest);
}

@Override
public List<LeaveRequestDTOResponse> getPendingLeaveRequestsByUserId(Long userId) {
    List<LeaveRequest> pendingLeaveRequests = leaveRequestRepository.findByUser_IdAndStatus(userId, Status.En_Attente);
    return pendingLeaveRequests.stream()
            .map(leaveRequestMapper::convertToDTO)
            .collect(Collectors.toList());
}

@Override
    public List<LeaveRequestDTOResponse> getLeaveRequestsByUserId(Long userId) {
        List<LeaveRequest> leaveRequests = leaveRequestRepository.findByUserId(userId);
        return leaveRequests.stream()
                .map(leaveRequestMapper::convertToDTO)
                .collect(Collectors.toList());
    }

@Override
public List<LeaveRequestDTOResponse> getPendingLeaveRequests() {
    List<LeaveRequest> pendingLeaveRequests = leaveRequestRepository.findByStatus(Status.En_Attente);
    return pendingLeaveRequests.stream()
            .map(leaveRequestMapper::convertToDTO) // Convert LeaveRequest entities to DTOs
            .collect(Collectors.toList());
}

@Override
public List<LeaveRequestDTOResponse> getAllLeaveRequestsForUser(Long userId) {
    // Use userId to fetch all leave requests for that user from the repository
    // You can then transform the entities to DTOs if needed
    List<LeaveRequest> leaveRequests = leaveRequestRepository.findByUserId(userId);
    return leaveRequests.stream()
        .map(leaveRequestMapper::convertToDTO)
        .collect(Collectors.toList());
}
@Override
public LeaveRequestDTOResponse updateLeaveRequest(Long userId, Long leaveRequestId, LeaveRequestCreationDTO updateDTO)
        throws LeaveRequestNotFoundException, UserNotFoundException, InsufficientLeaveQuotaException, AccessDeniedException, LeaveTypeNotFoundException {
    LeaveRequest leaveRequest = leaveRequestRepository.findById(leaveRequestId)
            .orElseThrow(() -> new LeaveRequestNotFoundException("Leave request not found"));

    if (!userId.equals(leaveRequest.getUser().getId())) {
        throw new AccessDeniedException("You do not have permission to update this leave request");
    }

    LeaveType leaveType = leaveTypeRepository.findById(updateDTO.getLeaveTypeId())
            .orElseThrow(() -> new LeaveTypeNotFoundException("Leave type not found"));

    User approver = userRepository.findById(updateDTO.getApproverId())
            .orElseThrow(() -> new UserNotFoundException("Approver not found"));

    Set<User> replacements = new HashSet<>(userRepository.findAllById(updateDTO.getReplacementIds()));

    // Calculate the duration of the updated leave
    LocalDate newStartDate = updateDTO.getStartDate();
    LocalDate newEndDate = updateDTO.getEndDate();
    long newDuration = ChronoUnit.DAYS.between(newStartDate, newEndDate) + 1;

    // Calculate the difference in duration
    long durationDifference = (long) (newDuration - leaveRequest.getDuration());

    // Get the user's quota for the current year (you may need to adjust this logic)
    int currentYear = Year.now().getValue();
    UserQuota userQuota = userQuotaRepository.findByUserIdAndYear(userId, currentYear);

    if (userQuota == null) {
        throw new InsufficientLeaveQuotaException("User quota not found for the current year");
    }

    // Check if the user has enough quota for the updated leave
    double remainingQuota = userQuota.getResiduel();

    if (remainingQuota < durationDifference) {
        throw new InsufficientLeaveQuotaException("Insufficient leave quota");
    }

    // Update the user's quota
    userQuota.setResiduel(userQuota.getResiduel() - durationDifference);

    // Update the leave request
    leaveRequest.setLeaveType(leaveType);
    leaveRequest.setApprover(approver);
    leaveRequest.setReplacements(replacements);
    leaveRequest.setStartDate(newStartDate);
    leaveRequest.setEndDate(newEndDate);
    leaveRequest.setComment(updateDTO.getComment());
    leaveRequest.setDuration((long)newDuration);

    LeaveRequest updatedLeaveRequest = leaveRequestRepository.save(leaveRequest);

    // Create a response DTO
    LeaveRequestDTOResponse responseDTO = new LeaveRequestDTOResponse();
    responseDTO.setFrom(newStartDate);
    responseDTO.setTo(newEndDate);
    responseDTO.setDuration(newDuration);
    responseDTO.setRequesterUsername(leaveRequest.getUser().getUsername());
    responseDTO.setApproverUsername(approver.getUsername());
    List<String> replacementUsernames = replacements.stream().map(User::getUsername).collect(Collectors.toList());
    responseDTO.setReplacementUsernames(replacementUsernames);
    responseDTO.setLeaveTypeName(leaveType.getName());
    responseDTO.setComment(updateDTO.getComment());

    return responseDTO;
}

@Override
public LeaveRequestDTOResponse createLeaveRequest(LeaveRequestCreationDTO requestDTO, Long userId)
        throws LeaveTypeNotFoundException, UserNotFoundException, InsufficientLeaveQuotaException {
    LeaveType leaveType = leaveTypeRepository.findById(requestDTO.getLeaveTypeId())
            .orElseThrow(() -> new LeaveTypeNotFoundException("Leave type not found"));

    User user = userRepository.findById(userId)
            .orElseThrow(() -> new UserNotFoundException("User not found"));

    User approver = userRepository.findById(requestDTO.getApproverId())
            .orElseThrow(() -> new UserNotFoundException("Approver not found"));

    Set<User> replacements = new HashSet<>(userRepository.findAllById(requestDTO.getReplacementIds()));

    // Calculate the duration of the leave
    LocalDate startDate = requestDTO.getStartDate();
    LocalDate endDate = requestDTO.getEndDate();
    long duration = ChronoUnit.DAYS.between(startDate, endDate) + 1;

    // Calculate planned days (you need to define the logic for this)
    //double plannedDays = calculatePlannedDays(startDate, endDate);

    // Get the user's quota for the current year (you may need to adjust this logic)
    int currentYear = Year.now().getValue();
    UserQuota userQuota = userQuotaRepository.findByUserIdAndYear(userId, currentYear);

    if (userQuota == null) {
        throw new InsufficientLeaveQuotaException("User quota not found for the current year");
    }

    // Check if the user has enough quota for the leave
    double remainingQuota = userQuota.getResiduel();

    if (remainingQuota < duration) {
        throw new InsufficientLeaveQuotaException("Insufficient leave quota");
    }
    LocalDateTime date=LocalDateTime.now();
    // Update the user's quota
    userQuota.setResiduel(userQuota.getResiduel() - duration);

    LeaveRequest leaveRequest = new LeaveRequest();
    leaveRequest.setLeaveType(leaveType);
    leaveRequest.setUser(user);
    leaveRequest.setApprover(approver);
    leaveRequest.setReplacements(replacements);
    leaveRequest.setStartDate(startDate);
    leaveRequest.setEndDate(endDate);
    leaveRequest.setComment(requestDTO.getComment());
    leaveRequest.setDuration(duration);
    leaveRequest.setStatus(requestDTO.getStatus());
    leaveRequest.setCreDate(LocalDateTime.now()); // Set creDate to the current date
 // Set the status from the DTO


    LeaveRequest savedLeaveRequest = leaveRequestRepository.save(leaveRequest);

    // Create a response DTO
    LeaveRequestDTOResponse responseDTO = new LeaveRequestDTOResponse();
    responseDTO.setFrom(startDate);
    responseDTO.setTo(endDate);
    responseDTO.setDuration(duration);
   // responseDTO.setPlannedDays(plannedDays);
    responseDTO.setRequesterUsername(user.getUsername());
    responseDTO.setApproverUsername(approver.getUsername());
    List<String> replacementUsernames = replacements.stream().map(User::getUsername).collect(Collectors.toList());
    responseDTO.setReplacementUsernames(replacementUsernames);
    responseDTO.setLeaveTypeName(leaveType.getName());
    responseDTO.setComment(requestDTO.getComment());
    responseDTO.setStatus(savedLeaveRequest.getStatus()); // Set the status in the response DTO
    responseDTO.setCreDate(LocalDateTime.now());

    return responseDTO;
}


private double calculatePlannedDays(LocalDate startDate, LocalDate endDate) {
    double plannedDays = 0.0;
    LocalDate currentDate = startDate;

    while (!currentDate.isAfter(endDate)) {
        // Vérifiez si la date actuelle est un samedi (DayOfWeek.SATURDAY) ou un dimanche (DayOfWeek.SUNDAY)
        DayOfWeek dayOfWeek = currentDate.getDayOfWeek();
        if (dayOfWeek != DayOfWeek.SATURDAY && dayOfWeek != DayOfWeek.SUNDAY) {
            // Si ce n'est pas un week-end, augmentez le nombre de jours planifiés
            plannedDays += 1.0;
        }
        
        // Passez à la date suivante
        currentDate = currentDate.plusDays(1);
    }

    return plannedDays;
}

// public LeaveRequest createLeaveRequest(LeaveRequestCreationDTO requestDTO, Long userId)
//         throws LeaveTypeNotFoundException, UserNotFoundException, InsufficientLeaveQuotaException {
//     LeaveType leaveType = leaveTypeRepository.findById(requestDTO.getLeaveTypeId())
//             .orElseThrow(() -> new LeaveTypeNotFoundException("Leave type not found"));

//     User user = userRepository.findById(userId)
//             .orElseThrow(() -> new UserNotFoundException("User not found"));

//     User approver = userRepository.findById(requestDTO.getApproverId())
//             .orElseThrow(() -> new UserNotFoundException("Approver not found"));

//     Set<User> replacements = new HashSet<>(userRepository.findAllById(requestDTO.getReplacementIds()));

//     // Calculate the duration of the leave
//     LocalDate startDate = requestDTO.getStartDate();
//     LocalDate endDate = requestDTO.getEndDate();
//     long duration = ChronoUnit.DAYS.between(startDate, endDate) + 1;

//     // Get the user's quota for the current year (you may need to adjust this logic)
//     int currentYear = Year.now().getValue();
//     UserQuota userQuota = userQuotaRepository.findByUserIdAndYear(userId, currentYear);

//     if (userQuota == null) {
//         throw new InsufficientLeaveQuotaException("User quota not found for the current year");
//     }

//     //LeaveQuota leaveQuota = userQuota.getLeaveQuota();

//     // Check if the user has enough quota for the leave
//     double remainingQuota = userQuota.getResiduel();

//     if (remainingQuota < duration) {
//         throw new InsufficientLeaveQuotaException("Insufficient leave quota");
//     }

//     // Update the user's quota
//     userQuota.setResiduel(userQuota.getResiduel() - duration);

//     LeaveRequest leaveRequest = new LeaveRequest();
//     leaveRequest.setLeaveType(leaveType);
//     leaveRequest.setUser(user);
//     leaveRequest.setApprover(approver);
//     leaveRequest.setReplacements(replacements);
//     leaveRequest.setStartDate(startDate);
//     leaveRequest.setEndDate(endDate);
//     leaveRequest.setComment(requestDTO.getComment());
//    // leaveRequest.setCreDate(LocalDate.now());

//     return leaveRequestRepository.save(leaveRequest);
// }

    
// @Override

//     public LeaveRequest getLeaveRequestById(Long leaveRequestId) throws LeaveRequestNotFoundException {
//         return leaveRequestRepository.findById(leaveRequestId)
//                 .orElseThrow(() -> new LeaveRequestNotFoundException("Leave request not found"));
//     }
@Override

    public List<LeaveRequest> getAllLeaveRequests() {
        return leaveRequestRepository.findAll();
    }
@Override

    public void deleteLeaveRequest(Long leaveRequestId) {
        leaveRequestRepository.deleteById(leaveRequestId);
    }
}




// @Service
// public class LeaveRequestServiceImpl implements LeaveRequestService {

//     private final LeaveRequestMapper leaveRequestMapper;
//     private final EmployeRepository employeRepository;
//     private final LeaveRequestRepository leaveRequestRepository; // You need to inject this repository

//     public LeaveRequestServiceImpl(LeaveRequestMapper leaveRequestMapper, EmployeRepository employeRepository,
//                                    LeaveRequestRepository leaveRequestRepository) {
//         this.leaveRequestMapper = leaveRequestMapper;
//         this.employeRepository = employeRepository;
//         this.leaveRequestRepository = leaveRequestRepository;
//     }

//     @Override
//     public LeaveRequestCreationDTO createLeaveRequest(LeaveRequestCreationDTO dto)
//             throws EmployeNotFoundException, RemplacantNotAvailableException {
//         // Convert the DTO fields to entities and fetch employe entities
//         // Employe rhEntity = employeRepository.findByRoleAndName(Role.RH, dto.getApprover());
//         // Employe remplacantEntity = employeRepository.findByRoleAndName(Role.USER, dto.getRemplacant());

//         // Map the DTO fields and employe entities to LeaveRequestCreationDTO
//         // ...

//         // Map LeaveRequestCreationDTO to LeaveRequest entity
//         LeaveRequest leaveRequestEntity = leaveRequestMapper.fromLeaveRequestCreationDTO(dto);
//         // leaveRequestEntity.setEmploye(rhEntity); // Set the approver (RH)
//         // leaveRequestEntity.setRemplacant(remplacantEntity); // Set the remplacant

//         // Save the leave request entity in the database using the repository
//         leaveRequestRepository.save(leaveRequestEntity);

//         return dto;
//     }
// }


// // @Service
// // public class LeaveRequestServiceImpl implements LeaveRequestService {
// //     private final LeaveRequestMapper leaveRequestMapper;
// //     private final LeaveRequestRepository leaveRequestRepository;
// //     private final EmployeRepository employeRepository;
// //     private final LeaveTypeMapper leaveTypeMapper;
// //     private final EmployeMapper employeMapper;

// //     public LeaveRequestServiceImpl(LeaveRequestMapper leaveRequestMapper, LeaveRequestRepository leaveRequestRepository,
// //                                    EmployeRepository employeRepository,
// //                                    LeaveTypeMapper leaveTypeMapper, EmployeMapper employeMapper) {
// //         this.leaveRequestMapper = leaveRequestMapper;
// //         this.leaveRequestRepository = leaveRequestRepository;
// //         this.employeRepository = employeRepository;
// //         this.leaveTypeMapper = leaveTypeMapper;
// //         this.employeMapper=employeMapper;
// //     }

// //     @Override
// //     public LeaveRequestCreationDTO createLeaveRequest(EmployeDTO employe, RemplacantDTO remplacant,
// //                                                       Date startDate, Date endDate, String comment,
// //                                                       LeaveTypeDTO leaveType, EmployeDTO rh)
// //             throws EmployeNotFoundException, RemplacantNotAvailableException {
// //         // Check if start date is before end date
// //         if (startDate.after(endDate)) {
// //             throw new IllegalArgumentException("Start date must be before end date.");
// //         }

// //         // Get the employe entity from the repository
// //         Employe employeEntity = employeRepository.findById(employe.getId())
// //                 .orElseThrow(() -> new EmployeNotFoundException("Employe not found"));

// //         // Check if the selected remplacant is available
// //         if (!isRemplacantAvailable(remplacant, startDate, endDate)) {
// //             throw new RemplacantNotAvailableException("Selected remplacant is not available for the specified dates.");
// //         }

// //         // Get the RH (approver) employe entity from the repository
// //         Employe rhEntity = employeRepository.findById(rh.getId())
// //                 .orElseThrow(() -> new EmployeNotFoundException("RH not found"));

// //         // Convert LeaveTypeDTO to LeaveType entity using your mapping logic
// //         LeaveType leaveTypeEntity = leaveTypeMapper.fromLeaveTypeDTO(leaveType);
// //         Employe remplacantEntity = employeMapper.fromEmployeDTO(remplacantDTO);

// //         // Create the LeaveRequest entity
// //         LeaveRequest leaveRequestEntity = new LeaveRequest();
// //         leaveRequestEntity.setEmploye(employeEntity);
// //         leaveRequestEntity.setRemplacant(remplacant);
// //         leaveRequestEntity.setLeaveType(leaveTypeEntity);
// //         leaveRequestEntity.setStartDate(startDate);
// //         leaveRequestEntity.setEndDate(endDate);
// //         leaveRequestEntity.setComment(comment);
// //         // Set other attributes as needed
        
// //         // Save the leave request entity in the database using the repository
// //         LeaveRequest savedLeaveRequest = leaveRequestRepository.save(leaveRequestEntity);

// //         // Convert the saved LeaveRequest entity to LeaveRequestCreationDTO using the mapper
// //         LeaveRequestCreationDTO savedLeaveRequestDTO = leaveRequestMapper.toLeaveRequestCreationDTO(savedLeaveRequest);

// //         // Return the saved leave request DTO
// //         return savedLeaveRequestDTO;
// //     }

// //     private boolean isRemplacantAvailable(RemplacantDTO remplacant, Date startDate, Date endDate) {
// //         // Implement your logic here to check if the remplacant is available
// //         // You can fetch the remplacant's leave requests and check for overlapping
// //     }
// // }


   
// // }

// // @Service
// // public class LeaveRequestServiceImpl implements LeaveRequestService {
// //     private final LeaveRequestMapper leaveRequestMapper;
// //     private final LeaveRequestRepository leaveRequestRepository;
// //     private final RemplacantRepository remplacantRepository; 

// //     public LeaveRequestServiceImpl(LeaveRequestMapper leaveRequestMapper, LeaveRequestRepository leaveRequestRepository,
// //                                    RemplacantRepository remplacantRepository) {
// //         this.leaveRequestMapper = leaveRequestMapper;
// //         this.leaveRequestRepository = leaveRequestRepository;
// //         this.remplacantRepository = remplacantRepository;
// //     }

// //     @Override
// //     public LeaveRequestCreationDTO createLeaveRequest(LeaveRequestCreationDTO dto) {
// //         // Check if start date is before end date
// //         if (dto.getStartDate().isAfter(dto.getEndDate())) {
// //             throw new IllegalArgumentException("Start date must be before end date.");
// //         }

// //         // Check if any overlapping leave requests for the selected remplacant
// //         boolean hasOverlappingRequests = checkOverlappingRequests(dto.getRemplacant(), dto.getStartDate(), dto.getEndDate());
// //         if (hasOverlappingRequests) {
// //             throw new IllegalStateException("Selected remplacant has overlapping leave requests.");
// //         }

// //         // Perform your mapping operations here
        
// //         // Save the leave request entity in the database using the repository
// //         LeaveRequest savedLeaveRequest = leaveRequestRepository.save(leaveRequestEntity);

// //         // Perform your mapping operations for the result here
        
// //         // Return the saved leave request DTO
// //         return leaveRequestDTO;
// //     }

// //     private boolean checkOverlappingRequests(RemplacantDTO remplacantDTO, Date startDate, Date endDate) {
// //         // Get the remplacant entity using the repository
// //         Remplacant remplacant = remplacantRepository.findById(remplacantDTO.getId()).orElse(null);
        
// //         if (remplacant == null) {
// //             throw new IllegalArgumentException("Selected remplacant not found.");
// //         }

// //         // Check if there are any overlapping leave requests for the selected remplacant
// //         List<LeaveRequest> overlappingRequests = leaveRequestRepository.findOverlappingRequests(remplacant, startDate, endDate);
// //         return !overlappingRequests.isEmpty();
// //     }
// // }

// // @Service
// // public class LeaveRequestServiceImpl implements LeaveRequestService {
// //     private final LeaveRequestMapper leaveRequestMapper;
// //     private final LeaveRequestRepository leaveRequestRepository;

// //     public LeaveRequestServiceImpl(LeaveRequestMapper leaveRequestMapper, LeaveRequestRepository leaveRequestRepository) {
// //         this.leaveRequestMapper = leaveRequestMapper;
// //         this.leaveRequestRepository = leaveRequestRepository;
// //     }

// //     @Override
// //     public LeaveRequestCreationDTO createLeaveRequest(LeaveRequestCreationDTO dto) {
// //         LeaveRequest leaveRequest = leaveRequestMapper.fromLeaveRequestCreationDTO(dto);
// //         leaveRequestRepository.save(leaveRequest);
// //         return dto;
// //     }
// // }

// // @Service
// // public class LeaveRequestServiceImp implements LeaveRequestService {

// //     @Override
// //     public void createLeaveRequest(LeaveRequestCreationDTO requestDTO) {
// //         // TODO Auto-generated method stub
// //         throw new UnsupportedOperationException("Unimplemented method 'createLeaveRequest'");
// //     }

// //     @Override
// //     public LeaveRequestDTO getLeaveRequestDetails(int requestId) {
// //         // TODO Auto-generated method stub
// //         throw new UnsupportedOperationException("Unimplemented method 'getLeaveRequestDetails'");
// //     }
//     // private final EmployeMapper employeMapper;
//     // private final LeaveRequestMapper leaveRequestMapper;
//     // private final LeaveRequestRepository leaveRequestRepository;

//     // @Autowired
//     // public LeaveRequestServiceImp(
//     //         EmployeMapper employeMapper,
//     //         LeaveRequestMapper leaveRequestMapper,
//     //         LeaveRequestRepository leaveRequestRepository
//     // ) {
//     //     this.employeMapper = employeMapper;
//     //     this.leaveRequestMapper = leaveRequestMapper;
//     //     this.leaveRequestRepository = leaveRequestRepository;
//     // }

//     // @Override
//     // public void createLeaveRequest(LeaveRequestCreationDTO requestDTO) {
//     //     Employe rh = employeMapper.rhDTOToEmploye(requestDTO.getApprovers().get(0));
//     //     Employe remplacant = employeMapper.remplacantDTOToEmploye(requestDTO.getRemplacants().get(0));

//     //     LeaveRequest leaveRequest = leaveRequestMapper.leaveRequestCreationDTOToLeaveRequest(requestDTO);
//     //     leaveRequest.setEmploye(rh);
//     //     leaveRequest.setRemplacant(remplacant);

//     //     leaveRequestRepository.save(leaveRequest);
//     // }

//     // @Override
//     // public LeaveRequestDTO getLeaveRequestDetails(int requestId) {
//     //     LeaveRequest leaveRequest = leaveRequestRepository.findById(requestId)
//     //             .orElseThrow(() -> new RuntimeException("Leave request not found"));

//     //     return leaveRequestMapper.leaveRequestToLeaveRequestDTO(leaveRequest);
//     // }

