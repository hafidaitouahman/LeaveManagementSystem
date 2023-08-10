package com.leave.backend.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.leave.backend.Dtos.LeaveRequestCreationDTO;
import com.leave.backend.Dtos.LeaveRequestDTO;
import com.leave.backend.Entities.Employe;
import com.leave.backend.Entities.LeaveRequest;
import com.leave.backend.Repositories.LeaveRequestRepository;
import com.leave.backend.mappers.EmployeMapper;
import com.leave.backend.mappers.LeaveRequestMapper;

@Service
public class LeaveRequestServiceImpl implements LeaveRequestService {
    private final LeaveRequestMapper leaveRequestMapper;
    private final LeaveRequestRepository leaveRequestRepository;

    public LeaveRequestServiceImpl(LeaveRequestMapper leaveRequestMapper, LeaveRequestRepository leaveRequestRepository) {
        this.leaveRequestMapper = leaveRequestMapper;
        this.leaveRequestRepository = leaveRequestRepository;
    }

    @Override
    public LeaveRequestCreationDTO createLeaveRequest(LeaveRequestCreationDTO dto) {
        LeaveRequest leaveRequest = leaveRequestMapper.fromLeaveRequestCreationDTO(dto);
        leaveRequestRepository.save(leaveRequest);
        return dto;
    }
}

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

