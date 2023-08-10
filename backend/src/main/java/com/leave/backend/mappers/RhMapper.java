package com.leave.backend.mappers;

import org.springframework.stereotype.Service;

import com.leave.backend.Dtos.RhDTO;
import com.leave.backend.Entities.Employe;

@Service
public class RhMapper {
    private final EmployeMapper employeMapper;

    public RhMapper(EmployeMapper employeMapper) {
        this.employeMapper = employeMapper;
    }

    public Employe fromRhDTO(RhDTO rhDTO) {
        Employe employe = new Employe();
        // Set other properties if needed
        return employe;
    }
    
    public RhDTO toRhDTO(Employe employe) {
        RhDTO rhDTO = new RhDTO();
        // Set other properties if needed
        return rhDTO;
    }
}

