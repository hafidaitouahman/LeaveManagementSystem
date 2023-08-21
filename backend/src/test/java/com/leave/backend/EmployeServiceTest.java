package com.leave.backend;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.leave.backend.Dtos.EmployeDTO;
import com.leave.backend.Dtos.TeamDTO;
import com.leave.backend.Entities.Employe;
import com.leave.backend.Entities.Team;
import com.leave.backend.Repositories.EmployeRepository;
import com.leave.backend.Services.Implementation.EmployeServiceImpl;
import com.leave.backend.mappers.EmployeMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class EmployeServiceTest {
    @Mock
    private EmployeRepository employeRepository;

    @Mock
    private EmployeMapper employeMapper;

    @InjectMocks
    private EmployeServiceImpl employeService;

    @Test
    public void testCreateEmploye() {
        // Create a sample EmployeDTO
        EmployeDTO employeDTO = new EmployeDTO();
        employeDTO.setName("John Doe");
        
        // Mock the behavior of the mapper
        Employe employe = new Employe();
        employe.setName("John Doe");
        when(employeMapper.fromEmployeDTO(employeDTO)).thenReturn(employe);
        
        // Mock the behavior of the repository
        when(employeRepository.save(any())).thenReturn(employe);
        
        // Test the service method
        EmployeDTO result = employeService.createEmploye(employeDTO);
        
        // Assertions
        assertEquals(employeDTO.getName(), result.getName());
    }

    @Test
    public void testGetAllEmployes() {
        // Create some sample Employe instances
        Employe employe1 = new Employe();
        employe1.setName("John Doe");
        Employe employe2 = new Employe();
        employe2.setName("Jane Smith");
        
        // Mock the behavior of the repository to return the sample employes
        when(employeRepository.findAll()).thenReturn(Stream.of(employe1, employe2).collect(Collectors.toList()));
        
        // Mock the behavior of the mapper
        EmployeDTO employeDTO1 = new EmployeDTO();
        employeDTO1.setName("John Doe");
        EmployeDTO employeDTO2 = new EmployeDTO();
        employeDTO2.setName("Jane Smith");
        when(employeMapper.toEmployeDTO(employe1)).thenReturn(employeDTO1);
        when(employeMapper.toEmployeDTO(employe2)).thenReturn(employeDTO2);
        
        // Test the service method
        List<EmployeDTO> employeDTOs = employeService.getAllEmployes();
        
        // Assertions
        assertEquals(2, employeDTOs.size());
        assertEquals("John Doe", employeDTOs.get(0).getName());
        assertEquals("Jane Smith", employeDTOs.get(1).getName());
    }
      @Test
    public void testCreateEmployeAndAddToTeam() {
        // Create a sample TeamDTO
        TeamDTO teamDTO = new TeamDTO();
        teamDTO.setName("Development Team");

        // Create a sample EmployeDTO
        EmployeDTO employeDTO = new EmployeDTO();
        employeDTO.setName("John Doe");
        //employeDTO.setTeamDTO(teamDTO);

        // Mock the behavior of the mapper for both employe and team
        Team team = new Team();
        team.setName("Development Team");
        when(employeMapper.fromTeamDTO(teamDTO)).thenReturn(team);

        Employe employe = new Employe();
        employe.setName("John Doe");
        employe.setTeams(team);
        when(employeMapper.fromEmployeDTO(employeDTO)).thenReturn(employe);

        // Mock the behavior of the repository for both employe and team
        when(employeRepository.save(any())).thenReturn(employe);

        // Test the service method
        EmployeDTO result = employeService.createEmploye(employeDTO);

        // Assertions
        assertEquals(employeDTO.getName(), result.getName());
       //assertEquals(teamDTO.getName(), result.getTeamDTO().getName());
    }
}
