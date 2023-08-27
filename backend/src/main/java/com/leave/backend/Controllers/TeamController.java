package com.leave.backend.Controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.leave.backend.Dtos.TeamDTO;
import com.leave.backend.Exceptions.TeamNotFoundException;
import com.leave.backend.Services.TeamService;

import java.util.List;

 @CrossOrigin(origins = "http://localhost:4200" , maxAge = 3600, allowCredentials="true")
 @RestController
@RequestMapping("/api/team")
public class TeamController {
    private final TeamService teamService;

    
    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('RH')")
    public TeamDTO addTeam(@RequestBody TeamDTO teamDTO){
        return teamService.addTeam(teamDTO);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('RH')")
    public TeamDTO updateTeam(@PathVariable int id,@RequestBody TeamDTO teamDTO)throws TeamNotFoundException{
        return teamService.updateTeam(id,teamDTO);
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('RH')")
    public List<TeamDTO> getAllTeams() {
        return teamService.getAllTeams();
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('RH')")
    public void deleteTeamById(@PathVariable int id){
        teamService.deleteTeamById(id);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('RH')")
    public TeamDTO getById(@PathVariable int id) throws TeamNotFoundException {
        return teamService.findById(id);
    }
    
}
