package com.leave.backend.Services;

import java.util.List;

import com.leave.backend.Dtos.DepartementDTO;
import com.leave.backend.Exceptions.DepartementNotFoundException;

public interface DepartementService {
    DepartementDTO addDepartement(DepartementDTO departementDTO);
    DepartementDTO updateDepartement( int id,DepartementDTO departementDTO) throws DepartementNotFoundException;
    List<DepartementDTO> getAllDepartements();
    void deleteDepartementById(int id);
    DepartementDTO findById(int id) throws DepartementNotFoundException;
}
