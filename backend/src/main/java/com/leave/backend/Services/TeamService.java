package com.leave.backend.Services;

import java.util.List;

import com.leave.backend.Dtos.TeamDTO;

public interface TeamService {
    TeamDTO createTeam(TeamDTO teamDTO);
    List<TeamDTO> getAllTeams();
}
