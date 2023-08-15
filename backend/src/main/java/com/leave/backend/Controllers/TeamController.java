package com.leave.backend.Controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.leave.backend.Dtos.TeamDTO;
import com.leave.backend.Exceptions.TeamNotFoundException;
import com.leave.backend.Services.TeamService;

import java.util.List;
@RestController @CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/team")
public class TeamController {
    private final TeamService teamService;

    
    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    
    }
    @PostMapping("/add")
    public TeamDTO addTeam(@RequestBody TeamDTO teamDTO){
        return teamService.addTeam(teamDTO);
    }

    @PutMapping("/{id}")
    public TeamDTO updateTeam(@PathVariable int id,@RequestBody TeamDTO teamDTO)throws TeamNotFoundException{
        return teamService.updateTeam(id,teamDTO);
    }

    @GetMapping("/all")
    public List<TeamDTO> getAllTeams() {
        return teamService.getAllTeams();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTeamById(@PathVariable int id){
        teamService.deleteTeamById(id);
    }
    @GetMapping("/{id}")
    public TeamDTO getById(@PathVariable int id) throws TeamNotFoundException {
        return teamService.findById(id);
    }
    
}
