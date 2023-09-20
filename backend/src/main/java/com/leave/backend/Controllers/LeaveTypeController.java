package com.leave.backend.Controllers;

import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.leave.backend.Dtos.LeaveTypeDTO;
import com.leave.backend.Exceptions.LeaveTypeNotFoundException;
import com.leave.backend.Services.LeaveTypeService;
import com.leave.backend.Services.LeaveTypeService;

import java.util.List;
 @CrossOrigin(origins = "http://localhost:4200" , maxAge = 3600, allowCredentials="true")
 @RestController
@RequestMapping("/api/leaveType")
public class LeaveTypeController {
     private final LeaveTypeService leaveTypeService;

    
    @Autowired
    public LeaveTypeController(LeaveTypeService leaveTypeService) {
        this.leaveTypeService = leaveTypeService;
    
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('RH')")
    public LeaveTypeDTO addLeaveType(@RequestBody LeaveTypeDTO leaveTypeDTO){
        return leaveTypeService.addLeaveType(leaveTypeDTO);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('RH')")
    public LeaveTypeDTO updateLeaveType(@PathVariable Long id,@RequestBody LeaveTypeDTO leaveTypeDTO)throws LeaveTypeNotFoundException{
        return leaveTypeService.updateLeaveType(id,leaveTypeDTO);
    }
    @GetMapping("/all")
    @PreAuthorize("hasRole('RH')")
    public List<LeaveTypeDTO> getAllLeaveTypes() {
        return leaveTypeService.getAllLeaveTypes();
    }
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('RH')")
    public void deleteLeaveTypeById(@PathVariable Long id){
        leaveTypeService.deleteLeaveTypeById(id);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('RH')")
    public LeaveTypeDTO getById(@PathVariable Long id) throws LeaveTypeNotFoundException {
        return leaveTypeService.findById(id);
    }
    
}
