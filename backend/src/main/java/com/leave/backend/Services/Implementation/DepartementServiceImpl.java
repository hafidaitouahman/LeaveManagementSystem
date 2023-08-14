package com.leave.backend.Services.Implementation;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.leave.backend.Dtos.DepartementDTO;
import com.leave.backend.Entities.Departement;
import com.leave.backend.Exceptions.DepartementNotFoundException;
import com.leave.backend.Repositories.DepartementRepository;
import com.leave.backend.Services.DepartementService;
import com.leave.backend.mappers.DepartementMapper;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Transactional
@AllArgsConstructor
@Service
public class DepartementServiceImpl implements DepartementService {
        private DepartementRepository departementRepository;
    private DepartementMapper departementMapper;



    @Override
    public DepartementDTO addDepartement(DepartementDTO departementDTO){
      
        Departement departement = departementMapper.fromDepartementDTO(departementDTO);
        // Save new user in database
        Departement savedLeaveType = departementRepository.save(departement);
        // Convert Departement entity to DepartementDTO using the departementMapper
        return departementMapper.fromDepartement(savedLeaveType);
    }

    @Override
    public DepartementDTO updateDepartement( int id,DepartementDTO departementDTO) throws DepartementNotFoundException{

          Departement departement = departementRepository.findById(id)
         .orElseThrow(() -> new DepartementNotFoundException("not found"));
   

            // Update the properties of the existing user
            departement.setName(departementDTO.getName());
           
            // ... update other properties ...

            Departement updatedUser = departementRepository.save(departement);
            return departementMapper.fromDepartement(updatedUser);




    
    }

    @Override
      public List<DepartementDTO> getAllDepartements() {
        List<Departement> allLeaveTypes = departementRepository.findAll();
        List<DepartementDTO> leaveTypeDTOList = allLeaveTypes.stream()
                .map(departementMapper::fromDepartement)
                .collect(Collectors.toList());
        return leaveTypeDTOList;
    }
    
    @Override
     public void deleteDepartementById(int id){
        Optional<Departement> OptionalLeaveType = departementRepository.findById(id);

        Departement departement = OptionalLeaveType.get();

        // Convert the Room entity to RoomDTO using the roomMapper
        DepartementDTO departementDTO = departementMapper.fromDepartement(departement);

        departementRepository.delete(departement);

     }

     @Override
     public DepartementDTO findById(int id) throws DepartementNotFoundException{
         Departement departement = departementRepository.findById(id)
         .orElseThrow(() -> new DepartementNotFoundException("not found"));
                 
         return departementMapper.fromDepartement(departement);
     }
}
