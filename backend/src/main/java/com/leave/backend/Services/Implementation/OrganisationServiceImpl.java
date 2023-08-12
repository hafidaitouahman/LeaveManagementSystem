package com.leave.backend.Services.Implementation;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.leave.backend.Dtos.OrganisationDTO;
import com.leave.backend.Entities.Organisation;
import com.leave.backend.Repositories.OrganisationRepository;
import com.leave.backend.Repositories.TeamRepository;
import com.leave.backend.Services.OrganisationService;
import com.leave.backend.mappers.OrganisationMapper;
import com.leave.backend.mappers.TeamMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

@Slf4j
public class OrganisationServiceImpl implements OrganisationService {
    private OrganisationRepository organisationRepository;
    private OrganisationMapper organisationMapper;

      public OrganisationServiceImpl(OrganisationMapper organisationMapper, OrganisationRepository organisationRepository) {
        this.organisationMapper = organisationMapper;
        this.organisationRepository = organisationRepository;
    }
    
    public OrganisationDTO addOrganisation(OrganisationDTO organisationDTO){
      
        Organisation organisation = organisationMapper.fromOrganisationDTO(organisationDTO);
        // Save new user in database
        Organisation savedOrganisation = organisationRepository.save(organisation);
        // Convert Organisation entity to OrganisationDTO using the organisationMapper
        return organisationMapper.fromOrganisation(savedOrganisation);
    }
    public OrganisationDTO updateOrganisation(OrganisationDTO organisationDTO){
        log.info("Updating the Organisation");
        
        // Convert OrganisationDTO to Organisation entity using the organisationMapper
        Organisation organisation = organisationMapper.fromOrganisationDTO(organisationDTO);
        // Save new user in database
        Organisation savedRoom = organisationRepository.save(organisation);
        // Convert Organisation entity to OrganisationDTO using the organisationMapper
        return organisationMapper.fromOrganisation(savedRoom);
    }



    
}
