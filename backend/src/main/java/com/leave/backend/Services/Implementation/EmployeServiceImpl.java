package com.leave.backend.Services.Implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import com.leave.backend.Dtos.EmployeDTO;
import com.leave.backend.Entities.Employe;
import com.leave.backend.mappers.EmployeMapper;
import com.leave.backend.Repositories.EmployeRepository;
import com.leave.backend.Services.EmployeService;

@Service
public class EmployeServiceImpl implements EmployeService {
    private final EmployeMapper employeMapper;
    private final EmployeRepository employeRepository;

    public EmployeServiceImpl(EmployeMapper employeMapper, EmployeRepository employeRepository) {
        this.employeMapper = employeMapper;
        this.employeRepository = employeRepository;
    }

    @Override
    public EmployeDTO createEmploye(EmployeDTO employeDTO) {
        Employe employe = employeMapper.fromEmployeDTO(employeDTO);
        employeRepository.save(employe);
        return employeDTO;
    }

    @Override
    public List<EmployeDTO> getAllEmployes() {
        List<Employe> employes = employeRepository.findAll();
        return employes.stream().map(employeMapper::toEmployeDTO).collect(Collectors.toList());
    }
}

// @Service
// public class EmployeServiceImpl implements EmployeService {

//     private final EmployeRepository employeRepository;
//     private final EmployeMapper employeMapper;

//     public EmployeServiceImpl(EmployeRepository employeRepository, EmployeMapper employeMapper) {
//         this.employeRepository = employeRepository;
//         this.employeMapper = employeMapper;
//     }

//     @Override
//     public void addEmploye(EmployeDTO employeDTO) {
//         Employe employe = employeMapper.fromEmployeDTO(employeDTO);
//         employeRepository.save(employe);
//     }

//     // Ajoutez ici d'autres méthodes spécifiques du service pour gérer les employés
// }

