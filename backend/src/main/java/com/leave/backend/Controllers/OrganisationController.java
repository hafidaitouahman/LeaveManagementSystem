package com.leave.backend.Controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leave.backend.Dtos.OrganisationDTO;
import com.leave.backend.Repositories.OrganisationRepository;
//import com.leave.backend.Services.LeaveRequestService;
import com.leave.backend.Services.OrganisationService;
import com.leave.backend.Services.Implementation.OrganisationServiceImpl;


@RestController @CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/organisation")
public class OrganisationController {
    private final OrganisationService organisationService;

    
    @Autowired
    public OrganisationController(OrganisationService organisationService) {
        this.organisationService = organisationService;
    
    }
    @PostMapping("/add")
    public OrganisationDTO addOrganisation(@RequestBody OrganisationDTO organisationDTO){
        return organisationService.addOrganisation(organisationDTO);
    }

    @PutMapping("/update")
    public OrganisationDTO updateOrganisation(@RequestBody OrganisationDTO organisationDTO){
        return organisationService.updateOrganisation(organisationDTO);
    }
}
