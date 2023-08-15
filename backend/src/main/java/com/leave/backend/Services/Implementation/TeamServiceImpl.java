package com.leave.backend.Services.Implementation;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.leave.backend.Dtos.TeamDTO;
import com.leave.backend.Dtos.TeamDTO;
import com.leave.backend.Entities.Team;
import com.leave.backend.Entities.Team;
import com.leave.backend.Exceptions.TeamNotFoundException;
import com.leave.backend.Repositories.TeamRepository;
import com.leave.backend.Services.TeamService;
import com.leave.backend.mappers.TeamMapper;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Transactional
@AllArgsConstructor
@Service
public class TeamServiceImpl implements TeamService {
    private final TeamMapper teamMapper;
    private final TeamRepository teamRepository;

 
    @Override
    public TeamDTO addTeam(TeamDTO teamDTO){
      
        Team team = teamMapper.fromTeamDTO(teamDTO);
        // Save new user in database
        Team savedLeaveType = teamRepository.save(team);
        // Convert Team entity to TeamDTO using the teamMapper
        return teamMapper.fromTeam(savedLeaveType);
    }

    @Override
    public TeamDTO updateTeam( int id,TeamDTO teamDTO) throws TeamNotFoundException{

          Team team = teamRepository.findById(id)
         .orElseThrow(() -> new TeamNotFoundException("not found"));
   

            // Update the properties of the existing user
            team.setName(teamDTO.getName());
           
            // ... update other properties ...

            Team updatedUser = teamRepository.save(team);
            return teamMapper.fromTeam(updatedUser);




    
    }

    @Override
      public List<TeamDTO> getAllTeams() {
        List<Team> allLeaveTypes = teamRepository.findAll();
        List<TeamDTO> leaveTypeDTOList = allLeaveTypes.stream()
                .map(teamMapper::fromTeam)
                .collect(Collectors.toList());
        return leaveTypeDTOList;
    }
    
    @Override
     public void deleteTeamById(int id){
        Optional<Team> OptionalLeaveType = teamRepository.findById(id);

        Team team = OptionalLeaveType.get();

        // Convert the Room entity to RoomDTO using the roomMapper
        TeamDTO teamDTO = teamMapper.fromTeam(team);

        teamRepository.delete(team);

     }

     @Override
     public TeamDTO findById(int id) throws TeamNotFoundException{
         Team team = teamRepository.findById(id)
         .orElseThrow(() -> new TeamNotFoundException("not found"));
                 
         return teamMapper.fromTeam(team);
     }
}
