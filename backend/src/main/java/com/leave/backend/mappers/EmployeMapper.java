// package com.leave.backend.mappers;
// // import org.mapstruct.Mapper;
// // import org.mapstruct.Mapping;
// // import org.mapstruct.factory.Mappers;
// // import org.springframework.stereotype.Component;

// // import com.leave.backend.Dtos.EmployeDTO;
// // import com.leave.backend.Dtos.RhDTO;
// // import com.leave.backend.Dtos.RemplacantDTO;
// // import com.leave.backend.Entities.Employe;

// // @Mapper
// // @Component
// // public interface EmployeMapper {
// //     EmployeMapper INSTANCE = Mappers.getMapper(EmployeMapper.class);

// //     @Mapping(target = "teams", ignore = true)
// //     EmployeDTO employeToEmployeDTO(Employe employe);

// //     Mapper RhDTO vers Employe avec le rôle "rh"
// //     @Mapping(target = "role", constant = "rh")
// //     Employe rhDTOToEmploye(RhDTO rhDTO);

// //     Mapper RemplacantDTO vers Employe avec le rôle "remplacant"
// //     @Mapping(target = "role", constant = "remplacant")
// //     Employe remplacantDTOToEmploye(RemplacantDTO remplacantDTO);


// // }
// import org.springframework.beans.BeanUtils;
// import org.springframework.stereotype.Service;
// import com.leave.backend.Dtos.EmployeDTO;
// import com.leave.backend.Dtos.RemplacantDTO;
// import com.leave.backend.Dtos.RhDTO;
// import com.leave.backend.Dtos.TeamDTO;
// import com.leave.backend.Entities.Employe;
// import com.leave.backend.Entities.Team;

// @Service
// public class EmployeMapper {
//     private final TeamMapper teamMapper;

//     public EmployeMapper(TeamMapper teamMapper){
//         this.teamMapper = teamMapper;
//     }

//     public Employe fromEmployeDTO(EmployeDTO employeDTO){
//         Employe employe = new Employe();
//         BeanUtils.copyProperties(employeDTO, employe);
//         //employe.setTeams(teamMapper.fromTeamDTO(employeDTO.getTeamDTO()));
//         return employe;
//     }
    
//     public EmployeDTO toEmployeDTO(Employe employe){
//         EmployeDTO employeDTO = new EmployeDTO();
//         BeanUtils.copyProperties(employe, employeDTO);
//         //employeDTO.setTeamDTO(teamMapper.fromTeam(employe.getTeams()));
//         return employeDTO;
//     }
//         public Team fromTeamDTO(TeamDTO teamDTO) {
//         Team team = new Team();
//         team.setName(teamDTO.getName());
//         // Set other properties as needed
//         return team;
//     }

//    public Employe fromRhDTO(RhDTO rhDTO) {
//         Employe employe = new Employe();
//         // Map properties from RhDTO to Employe
//         return employe;
//     }
//     // Add this method to map from RemplacantDTO to Employe
//     public Employe fromRemplacantDTO(RemplacantDTO remplacantDTO) {
//         Employe employe = new Employe();
//         // Set other properties if needed
//         return employe;
//     }
// }

