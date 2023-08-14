package com.leave.backend.Controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.leave.backend.Dtos.DepartementDTO;
import com.leave.backend.Exceptions.DepartementNotFoundException;
import com.leave.backend.Services.DepartementService;

import java.util.List;
@RestController @CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/departement")
public class DepartementController {
      private final DepartementService departementService;

    
    @Autowired
    public DepartementController(DepartementService departementService) {
        this.departementService = departementService;
    
    }
    @PostMapping("/add")
    public DepartementDTO addDepartement(@RequestBody DepartementDTO departementDTO){
        return departementService.addDepartement(departementDTO);
    }

    @PutMapping("/{id}")
    public DepartementDTO updateDepartement(@PathVariable int id,@RequestBody DepartementDTO departementDTO)throws DepartementNotFoundException{
        return departementService.updateDepartement(id,departementDTO);
    }

    @GetMapping("/all")
    public List<DepartementDTO> getAllDepartements() {
        return departementService.getAllDepartements();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteDepartementById(@PathVariable int id){
        departementService.deleteDepartementById(id);
    }
    @GetMapping("/{id}")
    public DepartementDTO getById(@PathVariable int id) throws DepartementNotFoundException {
        return departementService.findById(id);
    }
}
