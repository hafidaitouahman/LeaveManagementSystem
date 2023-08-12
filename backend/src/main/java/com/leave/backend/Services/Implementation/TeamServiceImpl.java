package com.leave.backend.Services.Implementation;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.leave.backend.Dtos.TeamDTO;
import com.leave.backend.Entities.Team;
import com.leave.backend.Repositories.TeamRepository;
import com.leave.backend.Services.TeamService;
import com.leave.backend.mappers.TeamMapper;

@Service
public class TeamServiceImpl implements TeamService {
    private final TeamMapper teamMapper;
    private final TeamRepository teamRepository;

    public TeamServiceImpl(TeamMapper teamMapper, TeamRepository teamRepository) {
        this.teamMapper = teamMapper;
        this.teamRepository = teamRepository;
    }
    

    @Override
    public TeamDTO createTeam(TeamDTO teamDTO) {
        Team team = teamMapper.fromTeamDTO(teamDTO);
        teamRepository.save(team);
        return teamDTO;
    }

    @Override
    public List<TeamDTO> getAllTeams() {
        List<Team> teams = teamRepository.findAll();
        return teams.stream().map(teamMapper::fromTeam).collect(Collectors.toList());
    }
}
