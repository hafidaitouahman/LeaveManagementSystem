package com.leave.backend;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import com.leave.backend.Dtos.LeaveTypeDTO;
import com.leave.backend.Dtos.RhDTO;
import com.leave.backend.Dtos.EmployeDTO;
import com.leave.backend.Entities.Employe;
import com.leave.backend.Entities.LeaveType;
//import com.leave.backend.Enumeration.Role;
import com.leave.backend.Repositories.EmployeRepository;
import com.leave.backend.Repositories.LeaveTypeRepository;
import com.leave.backend.Services.EmployeService;
import com.leave.backend.mappers.EmployeMapper;
import com.leave.backend.mappers.LeaveTypeMapper;

import static org.mockito.Mockito.*;

@SpringBootTest
class EmployeAndLeaveTypeTests {

    // @Mock
    // private EmployeRepository employeRepository;

    // @Mock
    // private LeaveTypeRepository leaveTypeRepository;

    // @InjectMocks
    // private EmployeService employeService;

    // @InjectMocks
    // private LeaveTypeService leaveTypeService;

    // @BeforeEach
    // void setUp() {
    //     // Initialiser les mappers si nécessaire
    //     // EmployeMapper employeMapper = new EmployeMapperImpl(); // Par exemple
    //     // LeaveTypeMapper leaveTypeMapper = new LeaveTypeMapperImpl(); // Par exemple

    //     employeService = new EmployeServiceImpl(employeRepository, employeMapper);
    //     leaveTypeService = new LeaveTypeServiceImpl(leaveTypeRepository, leaveTypeMapper);
    // }

    // @Test
    // void testAddUserAndRhEmploye() {
    //     EmployeDTO userDto = new EmployeDTO("Alice");
    //     EmployeDTO rhDto = new EmployeDTO("John");
    //     rhDto.setRole(Role.RH);

    //     employeService.addEmploye(userDto);
    //     employeService.addEmploye(rhDto);

    //     // Vous pouvez vérifier les appels aux méthodes du repository ici
    //     verify(employeRepository, times(1)).save(any(Employe.class)); // Vérification de l'ajout de l'employé user
    //     verify(employeRepository, times(1)).save(any(Employe.class)); // Vérification de l'ajout de l'employé RH
    // }

    // @Test
    // void testAddLeaveType() {
    //     LeaveTypeDTO leaveTypeDto = new LeaveTypeDTO();
    //     leaveTypeDto.setName("Vacation");

    //     leaveTypeService.addLeaveType(leaveTypeDto);

    //     // Vous pouvez vérifier les appels aux méthodes du repository ici
    //     verify(leaveTypeRepository, times(1)).save(any(LeaveType.class)); // Vérification de l'ajout du type de congé
    // }
}
