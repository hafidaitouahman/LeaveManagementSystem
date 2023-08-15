package com.leave.backend.Services;

import java.util.List;

import com.leave.backend.Dtos.TeamDTO;
import com.leave.backend.Exceptions.TeamNotFoundException;

public interface TeamService {
    TeamDTO findById(int id) throws TeamNotFoundException;
    void deleteTeamById(int id);
    TeamDTO updateTeam( int id,TeamDTO teamDTO) throws TeamNotFoundException;
    TeamDTO addTeam(TeamDTO teamDTO);
    List<TeamDTO> getAllTeams();
}
