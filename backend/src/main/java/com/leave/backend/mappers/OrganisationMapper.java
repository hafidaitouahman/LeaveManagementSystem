package com.leave.backend.mappers;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.leave.backend.Dtos.OrganisationDTO;
import com.leave.backend.Entities.Organisation;
@Service
public class OrganisationMapper {
    public OrganisationDTO fromOrganisation(Organisation organisation){
        OrganisationDTO organisationDTO=new OrganisationDTO();
        BeanUtils.copyProperties(organisation, organisationDTO);
        return organisationDTO;
    }
    public Organisation fromOrganisationDTO(OrganisationDTO organisationDTO){
        Organisation organisation=new Organisation();
        BeanUtils.copyProperties(organisationDTO, organisation);
        return organisation;
    }
}
