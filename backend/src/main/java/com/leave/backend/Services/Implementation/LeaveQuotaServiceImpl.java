package com.leave.backend.Services.Implementation;

import com.leave.backend.Services.LeaveQuotaService;


import com.leave.backend.Dtos.LeaveQuotaDTO;
import com.leave.backend.Entities.LeaveQuota;
import com.leave.backend.Entities.User;
import com.leave.backend.Exceptions.LeaveQuotaNotFoundException;
import com.leave.backend.Repositories.LeaveQuotaRepository;
import com.leave.backend.Services.LeaveQuotaService;
import com.leave.backend.mappers.LeaveQuotaMapper;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.leave.backend.Entities.LeaveQuota;
import com.leave.backend.Repositories.LeaveQuotaRepository;

import java.util.List;
import java.util.Optional;
@Transactional
@Service
public class LeaveQuotaServiceImpl implements LeaveQuotaService {

    private final LeaveQuotaRepository leaveQuotaRepository;

    @Autowired
    public LeaveQuotaServiceImpl(LeaveQuotaRepository leaveQuotaRepository) {
        this.leaveQuotaRepository = leaveQuotaRepository;
    }
@Override
    public List<LeaveQuota> getAllLeaveQuotas() {
        return leaveQuotaRepository.findAll();
    }
@Override

    public Optional<LeaveQuota> getLeaveQuotaById(int id) {
        return leaveQuotaRepository.findById(id);
    }
@Override

    public LeaveQuota createLeaveQuota(LeaveQuota leaveQuota) {
        return leaveQuotaRepository.save(leaveQuota);
    }
@Override

    public LeaveQuota updateLeaveQuota(int id, LeaveQuota leaveQuota) {
        leaveQuota.setId(id); // Assurez-vous que l'ID de l'entité à mettre à jour correspond à l'ID dans le chemin
        return leaveQuotaRepository.save(leaveQuota);
    }
@Override

    public void deleteLeaveQuota(int id) {
        leaveQuotaRepository.deleteById(id);
    }

    @Override
    public List<LeaveQuota> getLeaveQuotaByUser(User user) {
        return leaveQuotaRepository.findByUser(user);
    }
}

// @Transactional
// @Service
// public class LeaveQuotaServiceImpl implements LeaveQuotaService{

//     private final LeaveQuotaRepository leaveQuotaRepository;
//     private final LeaveQuotaMapper leaveQuotaMapper;

//     @Autowired
//     public LeaveQuotaServiceImpl(LeaveQuotaRepository leaveQuotaRepository, LeaveQuotaMapper leaveQuotaMapper) {
//         this.leaveQuotaRepository = leaveQuotaRepository;
//         this.leaveQuotaMapper = leaveQuotaMapper;
//     }

//     @Override
//     public LeaveQuotaDTO addLeaveQuota(LeaveQuotaDTO leaveQuotaDTO) {
//         LeaveQuota leaveQuota = leaveQuotaMapper.fromLeaveQuotaDTO(leaveQuotaDTO);
//         LeaveQuota savedLeaveQuota = leaveQuotaRepository.save(leaveQuota);
//         return leaveQuotaMapper.fromLeaveQuota(savedLeaveQuota);
//     }

//     @Override
//     public LeaveQuotaDTO updateLeaveQuota(int id, LeaveQuotaDTO leaveQuotaDTO) throws LeaveQuotaNotFoundException {
//         LeaveQuota leaveQuota = leaveQuotaRepository.findById(id)
//                 .orElseThrow(() -> new LeaveQuotaNotFoundException("LeaveQuota not found"));
        
//         // Mise à jour des propriétés de l'entité LeaveQuota avec les valeurs du DTO
//         leaveQuota.setYear(leaveQuotaDTO.getYear());
//         leaveQuota.setQuota(leaveQuotaDTO.getQuota());
//         // Vous pouvez également mettre à jour d'autres propriétés ici
        
//         LeaveQuota updatedLeaveQuota = leaveQuotaRepository.save(leaveQuota);
//         return leaveQuotaMapper.fromLeaveQuota(updatedLeaveQuota);
//     }

//     @Override
//     public List<LeaveQuotaDTO> getAllLeaveQuotas() {
//         List<LeaveQuota> allLeaveQuotas = leaveQuotaRepository.findAll();
//         return allLeaveQuotas.stream()
//                 .map(leaveQuotaMapper::fromLeaveQuota)
//                 .collect(Collectors.toList());
//     }

//     @Override
//     public void deleteLeaveQuotaById(int id) {
//         leaveQuotaRepository.deleteById(id);
//     }

//     @Override
//     public LeaveQuotaDTO findLeaveQuotaById(int id) throws LeaveQuotaNotFoundException {
//         LeaveQuota leaveQuota = leaveQuotaRepository.findById(id)
//                 .orElseThrow(() -> new LeaveQuotaNotFoundException("LeaveQuota not found"));
//         return leaveQuotaMapper.fromLeaveQuota(leaveQuota);
//     }
// }
