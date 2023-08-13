package com.leave.backend.Services.Implementation;

import com.leave.backend.Dtos.LeaveTypeDTO;
import com.leave.backend.Entities.LeaveType;
import com.leave.backend.Exceptions.LeaveTypeNotFoundException;
import com.leave.backend.Repositories.LeaveTypeRepository;
import com.leave.backend.Services.LeaveTypeService;
import com.leave.backend.mappers.LeaveTypeMapper;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class LeaveTypeServiceImpl implements LeaveTypeService {
    private LeaveTypeRepository leaveTypeRepository;
    private LeaveTypeMapper leaveTypeMapper;



    @Override
    public LeaveTypeDTO addLeaveType(LeaveTypeDTO leaveTypeDTO){
      
        LeaveType leaveType = leaveTypeMapper.fromLeaveTypeDTO(leaveTypeDTO);
        // Save new user in database
        LeaveType savedLeaveType = leaveTypeRepository.save(leaveType);
        // Convert LeaveType entity to LeaveTypeDTO using the leaveTypeMapper
        return leaveTypeMapper.fromLeaveType(savedLeaveType);
    }

    @Override
    public LeaveTypeDTO updateLeaveType( int id,LeaveTypeDTO leaveTypeDTO) throws LeaveTypeNotFoundException{

          LeaveType leaveType = leaveTypeRepository.findById(id)
         .orElseThrow(() -> new LeaveTypeNotFoundException("not found"));
   

            // Update the properties of the existing user
            leaveType.setName(leaveTypeDTO.getName());
            leaveType.setApprobation(leaveType.isApprobation());
            leaveType.setSoustraction(leaveType.getSoustraction());
            // ... update other properties ...

            LeaveType updatedUser = leaveTypeRepository.save(leaveType);
            return leaveTypeMapper.fromLeaveType(updatedUser);




    
    }

    @Override
      public List<LeaveTypeDTO> getAllLeaveTypes() {
        List<LeaveType> allLeaveTypes = leaveTypeRepository.findAll();
        List<LeaveTypeDTO> leaveTypeDTOList = allLeaveTypes.stream()
                .map(leaveTypeMapper::fromLeaveType)
                .collect(Collectors.toList());
        return leaveTypeDTOList;
    }
    
    @Override
     public void deleteLeaveTypeById(int id){
        Optional<LeaveType> OptionalLeaveType = leaveTypeRepository.findById(id);

        LeaveType leaveType = OptionalLeaveType.get();

        // Convert the Room entity to RoomDTO using the roomMapper
        LeaveTypeDTO leaveTypeDTO = leaveTypeMapper.fromLeaveType(leaveType);

        leaveTypeRepository.delete(leaveType);

     }

     @Override
     public LeaveTypeDTO findById(int id) throws LeaveTypeNotFoundException{
         LeaveType leaveType = leaveTypeRepository.findById(id)
         .orElseThrow(() -> new LeaveTypeNotFoundException("not found"));
                 
         return leaveTypeMapper.fromLeaveType(leaveType);
     }

}
