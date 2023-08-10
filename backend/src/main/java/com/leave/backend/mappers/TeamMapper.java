package com.leave.backend.mappers;

import org.springframework.stereotype.Service;

import com.leave.backend.Dtos.TeamDTO;
import com.leave.backend.Entities.Team;

import org.springframework.beans.BeanUtils;

@Service
public class TeamMapper {
      public TeamDTO fromTeam(Team team){
        TeamDTO teamDTO=new TeamDTO();
        BeanUtils.copyProperties(team, teamDTO);
        teamDTO.setName(team.getName());

        return teamDTO;
    }
    public Team fromTeamDTO(TeamDTO teamDTO){
    Team team=new Team();
    BeanUtils.copyProperties(teamDTO, team);
    team.setName(teamDTO.getName());

    return team;
}
}
