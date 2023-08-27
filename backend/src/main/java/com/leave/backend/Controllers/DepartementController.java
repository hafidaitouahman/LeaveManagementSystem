package com.leave.backend.Controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.leave.backend.Dtos.DepartementDTO;
import com.leave.backend.Exceptions.DepartementNotFoundException;
import com.leave.backend.Services.DepartementService;

import java.util.List;
 @CrossOrigin(origins = "http://localhost:4200" , maxAge = 3600, allowCredentials="true")
 @RestController
@RequestMapping("/api/departement")
public class DepartementController {
      private final DepartementService departementService;

    
    @Autowired
    public DepartementController(DepartementService departementService) {
        this.departementService = departementService;
    
    }
    @PostMapping("/add")
    @PreAuthorize("hasRole('RH')")
    public DepartementDTO addDepartement(@RequestBody DepartementDTO departementDTO){
        return departementService.addDepartement(departementDTO);
    }

    @PutMapping("/{id}")   
    @PreAuthorize("hasRole('RH')")
    public DepartementDTO updateDepartement(@PathVariable int id,@RequestBody DepartementDTO departementDTO)throws DepartementNotFoundException{
        return departementService.updateDepartement(id,departementDTO);
    }
    @GetMapping("/all")
    @PreAuthorize("hasRole('RH')")
    public List<DepartementDTO> getAllDepartements() {
        return departementService.getAllDepartements();
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('RH')")
    public void deleteDepartementById(@PathVariable int id){
        departementService.deleteDepartementById(id);
    }
    
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('RH')")
    public DepartementDTO getById(@PathVariable int id) throws DepartementNotFoundException {
        return departementService.findById(id);
    }
}
