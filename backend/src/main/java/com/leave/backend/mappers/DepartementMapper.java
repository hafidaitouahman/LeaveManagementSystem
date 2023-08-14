package com.leave.backend.mappers;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.leave.backend.Dtos.DepartementDTO;
import com.leave.backend.Entities.Departement;

@Service
public class DepartementMapper {
        public DepartementDTO fromDepartement(Departement departement){
        DepartementDTO departementDTO=new DepartementDTO();
        BeanUtils.copyProperties(departement, departementDTO);
        return departementDTO;
    }
    public Departement fromDepartementDTO(DepartementDTO departementDTO){
        Departement departement=new Departement();
        BeanUtils.copyProperties(departementDTO, departement);
        return departement;
    }
}
