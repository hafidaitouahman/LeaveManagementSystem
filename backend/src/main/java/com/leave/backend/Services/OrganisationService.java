package com.leave.backend.Services;

import com.leave.backend.Dtos.OrganisationDTO;

public interface OrganisationService {
    OrganisationDTO addOrganisation(OrganisationDTO organisationDTO);
    OrganisationDTO updateOrganisation(OrganisationDTO organisationDTO);
}
