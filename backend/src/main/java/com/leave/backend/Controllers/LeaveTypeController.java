package com.leave.backend.Controllers;

import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.leave.backend.Dtos.LeaveTypeDTO;
import com.leave.backend.Services.LeaveTypeService;
import com.leave.backend.Services.LeaveTypeService;

import java.util.List;
@RestController @CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/leaveType")
public class LeaveTypeController {
     private final LeaveTypeService leaveTypeService;

    
    @Autowired
    public LeaveTypeController(LeaveTypeService leaveTypeService) {
        this.leaveTypeService = leaveTypeService;
    
    }
    @PostMapping("/add")
    public LeaveTypeDTO addLeaveType(@RequestBody LeaveTypeDTO leaveTypeDTO){
        return leaveTypeService.addLeaveType(leaveTypeDTO);
    }

    @PutMapping("/update")
    public LeaveTypeDTO updateOrganisation(@RequestBody LeaveTypeDTO leaveTypeDTO){
        return leaveTypeService.updateLeaveType(leaveTypeDTO);
    }

    @GetMapping("/all")
    public List<LeaveTypeDTO> getAllLeaveTypes() {
        return leaveTypeService.getAllLeaveTypes();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteLeaveTypeById(@PathVariable int id){
        leaveTypeService.deleteLeaveTypeById(id);
    }
    
}
