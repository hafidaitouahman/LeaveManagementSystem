// package com.leave.backend.mappers;
// import org.springframework.beans.BeanUtils;
// import org.springframework.stereotype.Service;
// import com.leave.backend.Dtos.LeaveRequestCreationDTO;
// import com.leave.backend.Entities.LeaveRequest;

// import com.leave.backend.mappers.EmployeMapper;
// import com.leave.backend.mappers.LeaveTypeMapper;
// import com.leave.backend.Entities.Employe;
// //import com.leave.backend.Enumeration.Role;
// import com.leave.backend.Repositories.EmployeRepository;

// @Service
// public class LeaveRequestMapper {

//     private final EmployeMapper employeMapper;
//     private final LeaveTypeMapper leaveTypeMapper;
//     private final EmployeRepository employeRepository;

//     public LeaveRequestMapper(EmployeMapper employeMapper, LeaveTypeMapper leaveTypeMapper,
//                               EmployeRepository employeRepository) {
//         this.employeMapper = employeMapper;
//         this.leaveTypeMapper = leaveTypeMapper;
//         this.employeRepository = employeRepository;
//     }

//     public LeaveRequest fromLeaveRequestCreationDTO(LeaveRequestCreationDTO dto) {
//         LeaveRequest entity = new LeaveRequest();
//         BeanUtils.copyProperties(dto, entity);

//         // entity.setStartDate(dto.getStartDate());
//         // entity.setEndDate(dto.getEndDate());
//         // entity.setComment(dto.getComment());
//         // entity.setLeaveType(dto.getLeaveType());
//         // Set other attributes as needed
        
       

//         return entity;
//     }

//     //public Employe mapApprover(String approverName) {
//     //     // Fetch the employe entity with role=rh
//     //     //return employeRepository.findByRoleAndName(Role.RH, approverName);
//     // }

//     // public Employe mapRemplacant(String remplacantName) {
//     //     // Fetch the employe entity with role=user
//     //    // return employeRepository.findByRoleAndName(Role.USER, remplacantName);
//     // }
// }



// // import org.springframework.stereotype.Service;

// // @Service
// // public class LeaveRequestMapper {
// //     private final EmployeMapper employeMapper;
// //     private final LeaveTypeMapper leaveTypeMapper;
// //     private final RhMapper rhMapper;
// //     private final RemplacantMapper remplacantMapper;

// //     public LeaveRequestMapper(EmployeMapper employeMapper, LeaveTypeMapper leaveTypeMapper,
// //                               RhMapper rhMapper, RemplacantMapper remplacantMapper) {
// //         this.employeMapper = employeMapper;
// //         this.leaveTypeMapper = leaveTypeMapper;
// //         this.rhMapper = rhMapper;
// //         this.remplacantMapper = remplacantMapper;
// //     }

// //     public LeaveRequest fromLeaveRequestCreationDTO(LeaveRequestCreationDTO dto) {
// //         LeaveRequest leaveRequest = new LeaveRequest();
// //         leaveRequest.setStartDate(dto.getStartDate());
// //         leaveRequest.setEndDate(dto.getEndDate());
// //         leaveRequest.setComment(dto.getComment());
// //         leaveRequest.setRemplacant(remplacantMapper.fromRemplacantDTO(dto.getRemplacant()));
// //         leaveRequest.setEmploye(employeMapper.fromEmployeDTO(dto.getEmploye()));
// //         leaveRequest.setLeaveType(leaveTypeMapper.fromLeaveTypeDTO(dto.getLeaveType()));
// //         // Set other properties as needed
// //         return leaveRequest;
// //     }
    
// //     // Rest of your mapping methods...
// // }


// // @Service
// // public class LeaveRequestMapper {

// //     public LeaveRequest fromLeaveRequestCreationDTO(LeaveRequestCreationDTO requestDTO){
// //         LeaveRequest leaveRequest = new LeaveRequest();
// //         BeanUtils.copyProperties(requestDTO, leaveRequest);
// //         return leaveRequest;
// //     }

// //     public LeaveRequestCreationDTO toLeaveRequestCreationDTO(LeaveRequest leaveRequest){
// //         LeaveRequestCreationDTO requestDTO = new LeaveRequestCreationDTO();
// //         BeanUtils.copyProperties(leaveRequest, requestDTO);
// //         return requestDTO;
// //     }
// // }

// // import org.mapstruct.Mapper;
// // import org.mapstruct.Mapping;
// // import org.mapstruct.factory.Mappers;
// // import com.leave.backend.Dtos.LeaveRequestCreationDTO;
// // import com.leave.backend.Dtos.LeaveRequestDTO;
// // import com.leave.backend.Dtos.RemplacantDTO;
// // import com.leave.backend.Dtos.RhDTO;
// // import com.leave.backend.Entities.Employe;
// // import com.leave.backend.Entities.LeaveRequest;

// // @Mapper
// // public interface LeaveRequestMapper {
// //     LeaveRequestMapper INSTANCE = Mappers.getMapper(LeaveRequestMapper.class);

// //     // Mapper LeaveRequestCreationDTO vers LeaveRequest
// //     @Mapping(target = "employe", ignore = true)
// //     @Mapping(target = "leaveType", ignore = true)
// //     @Mapping(target = "remplacant", ignore = true)
// //     LeaveRequest leaveRequestCreationDTOToLeaveRequest(LeaveRequestCreationDTO requestDTO);

// //     // Mapper RhDTO vers Employe pour le champ "remplacant"
// //     @Mapping(target = "teams", ignore = true)
// //     @Mapping(target = "role", constant = "remplacant")
// //     @Mapping(target = "leaveRequests", ignore = true)
// //     Employe remplacantDTOToEmploye(RemplacantDTO remplacantDTO);

// //     // Mapper RhDTO vers Employe pour le champ "employe"
// //     @Mapping(target = "teams", ignore = true)
// //     @Mapping(target = "role", constant = "rh")
// //     @Mapping(target = "leaveRequests", ignore = true)
// //     Employe rhDTOToEmploye(RhDTO rhDTO);

// //     // Mapper LeaveRequest vers LeaveRequestDTO
// //     @Mapping(source = "employe.name", target = "employes")
// //     @Mapping(target = "type", source = "leaveType.name")
// //     @Mapping(target = "remplacant", expression = "java(request.getRemplacant().getName())")
// //     LeaveRequestDTO leaveRequestToLeaveRequestDTO(LeaveRequest request);
// // }
