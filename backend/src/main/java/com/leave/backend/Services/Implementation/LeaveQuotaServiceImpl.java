package com.leave.backend.Services.Implementation;

import com.leave.backend.Services.LeaveQuotaService;


import com.leave.backend.Dtos.LeaveQuotaDTO;
import com.leave.backend.Entities.LeaveQuota;
import com.leave.backend.Entities.User;
import com.leave.backend.Entities.UserQuota;
import com.leave.backend.Exceptions.LeaveQuotaNotFoundException;
import com.leave.backend.Exceptions.LeaveRequestNotFoundException;
import com.leave.backend.Repositories.LeaveQuotaRepository;
import com.leave.backend.Repositories.UserQuotaRepository;
import com.leave.backend.Repositories.UserRepository;
import com.leave.backend.Services.LeaveQuotaService;
import com.leave.backend.mappers.LeaveQuotaMapper;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.leave.backend.Entities.LeaveQuota;
import com.leave.backend.Repositories.LeaveQuotaRepository;

import java.util.List;
import java.util.Optional;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;

@Service
public class LeaveQuotaServiceImpl implements LeaveQuotaService{
    private final LeaveQuotaRepository leaveQuotaRepository;

    private final UserRepository userRepository;

    private final UserQuotaRepository userQuotaRepository;
 @Autowired
    public LeaveQuotaServiceImpl(LeaveQuotaRepository leaveQuotaRepository,UserRepository userRepository, UserQuotaRepository userQuotaRepository) {
        this.leaveQuotaRepository = leaveQuotaRepository;
        this.userRepository=userRepository;
        this.userQuotaRepository=userQuotaRepository;
    }


    @Scheduled(cron = "0 0 0 1 1 ?")
    public void createLeaveQuotaForNewYear() {
        // Récupérer tous les quotas existants
        List<LeaveQuota> allQuotas = leaveQuotaRepository.findAll();

        // Pour chaque quota existant, réaffectez-le aux utilisateurs ayant le même pays
        for (LeaveQuota quota : allQuotas) {
            String quotaPays = quota.getPays();

            // Récupérer tous les utilisateurs ayant le même pays que le quota
            List<User> usersWithSamePays = userRepository.findByPays(quotaPays);

            // Associez le quota aux utilisateurs pour la nouvelle année
            int currentYear = LocalDate.now().getYear();
            for (User user : usersWithSamePays) {
                UserQuota userQuota = new UserQuota();
                userQuota.setUser(user);
                userQuota.setLeaveQuota(quota);
                userQuota.setYear(currentYear);
                userQuota.setQuota(quota.getQuota());
                userQuota.setResiduel(quota.getQuota());

                userQuotaRepository.save(userQuota);
            }
        }
    }
    // public LeaveQuota createLeaveQuota(String name, double quota) {
    //     // Créer un nouveau quota
    //     LeaveQuota newQuota = new LeaveQuota();
    //     newQuota.setName(name);
    //     newQuota.setQuota(quota);

    //     // Enregistrer le quota
    //     newQuota = leaveQuotaRepository.save(newQuota);

    //     // Récupérer tous les utilisateurs
    //     List<User> users = userRepository.findAll();

    //     // Associer le quota à tous les utilisateurs pour l'année en cours
    //    int currentYear = LocalDate.now().getYear();
    //     for (User user : users) {
    //         UserQuota userQuota = new UserQuota();
    //         userQuota.setUser(user);
    //         userQuota.setLeaveQuota(newQuota);
    //         userQuota.setYear(currentYear);
    //         userQuota.setQuota(quota);
    //         userQuota.setResiduel(quota);

    //         userQuotaRepository.save(userQuota);
    //     }

    //     return newQuota;
    // }
@Override
    public LeaveQuota getLeaveQuotaByPays(String pays) {
        return leaveQuotaRepository.findByPays(pays)
                .orElseThrow(() -> new RuntimeException("Erreur : le quota de congé n'a pas été trouvé pour le pays " + pays));
    }
    @Override
    public LeaveQuota createLeaveQuota(String name, double quota, String pays) {
        // Créer un nouveau quota
        LeaveQuota newQuota = new LeaveQuota();
        newQuota.setName(name);
        newQuota.setQuota(quota);
        newQuota.setPays(pays);
        // Enregistrez le quota dans la base de données
        newQuota = leaveQuotaRepository.save(newQuota);

        // Récupérer tous les utilisateurs ayant le même pays
        List<User> usersWithSamePays = userRepository.findByPays(pays);

        // Associez le quota aux utilisateurs pour l'année en cours
        int currentYear = LocalDate.now().getYear();
        for (User user : usersWithSamePays) {
            UserQuota userQuota = new UserQuota();
            userQuota.setUser(user);
            userQuota.setLeaveQuota(newQuota);
            userQuota.setYear(currentYear);
            userQuota.setQuota(quota);
            userQuota.setResiduel(quota);

            userQuotaRepository.save(userQuota);
        }

        return newQuota;
    }
  @Override
  public LeaveQuota updateLeaveQuota(Long quotaId, LeaveQuotaDTO request) {
    LeaveQuota existingQuota = leaveQuotaRepository.findById(quotaId)
            .orElseThrow(() -> new EntityNotFoundException("Quota not found with id: " + quotaId));

    // Mettez à jour les attributs du quota avec les valeurs de la demande
    existingQuota.setName(request.getName());
    existingQuota.setQuota(request.getQuota());
    existingQuota.setPays(request.getPays());

    // Enregistrez les modifications dans la base de données
    LeaveQuota updatedQuota = leaveQuotaRepository.save(existingQuota);
 
    return updatedQuota;
}
 @Override
public List<LeaveQuota> getAllLeaveQuotas() {
        return leaveQuotaRepository.findAll();
    }

 @Override
 @Transactional
    public void deleteLeaveQuota(Long quotaId) throws LeaveQuotaNotFoundException {
        Optional<LeaveQuota> leaveQuotaOptional = leaveQuotaRepository.findById(quotaId);

        if (leaveQuotaOptional.isPresent()) {
            LeaveQuota leaveQuota = leaveQuotaOptional.get();
            leaveQuotaRepository.delete(leaveQuota);

            // Supprimer toutes les lignes associées à ce quota dans la table quota_user
            userQuotaRepository.deleteByLeaveQuota(leaveQuota);
        } else {
            throw new LeaveQuotaNotFoundException("Leave Quota with ID " + quotaId + " not found.");
        }
    }
@Override
    public LeaveQuota getLeaveQuotaById(Long quotaId) throws LeaveQuotaNotFoundException {
        return leaveQuotaRepository.findById(quotaId)
                .orElseThrow(() -> new LeaveQuotaNotFoundException("Leave Quota not found with id: " + quotaId));
    }
}
// @Transactional
// @Service
// public class LeaveQuotaServiceImpll implements LeaveQuotaService {

//     private final LeaveQuotaRepository leaveQuotaRepository;
//     private final UserRepository userRepository;

//     @Autowired
//     public LeaveQuotaServiceImpll(LeaveQuotaRepository leaveQuotaRepository,UserRepository userRepository) {
//         this.leaveQuotaRepository = leaveQuotaRepository;
//         this.userRepository=userRepository;
//     }



// @Override

//     // Dans votre service LeaveQuotaService.java
// public LeaveQuota getQuotaWithUsersById(int quotaId) throws LeaveQuotaNotFoundException {
//     Optional<LeaveQuota> optionalQuota = leaveQuotaRepository.findById(quotaId);
    
//     if (optionalQuota.isPresent()) {
//         LeaveQuota quota = optionalQuota.get();
//         quota.getUsers().size(); // Assure le chargement de la liste des utilisateurs
//         return quota;
//     } else {
//         throw new LeaveQuotaNotFoundException("Quota not found with id: " + quotaId);
//     }
// }

// //// a revoir
// @Override
// public LeaveQuota getLeaveQuotaByYear(int year) {
//     return leaveQuotaRepository.findByYear(year);
// }
// // @Override
// //     public void copyQuotaToInitialQuota(int year) {
// //         // Récupérez la quota de l'année actuelle (par exemple, 2023)
// //         LeaveQuota leaveQuota = leaveQuotaRepository.findByYear(year);

// //         if (leaveQuota != null) {
// //             // Récupérez tous les utilisateurs associés à cette quota
// //             Set<User> users = leaveQuota.getUsers();

// //             // Copiez la valeur de la quota dans la valeur du quota initial de chaque utilisateur
// //             for (User user : users) {
// //                 user.setQuotaInitial(leaveQuota.getQuota());
// //                 userRepository.save(user);
// //             }
// //         }
// //     }
//  @Override
// public List<LeaveQuota> getAllLeaveQuotas() {
//         return leaveQuotaRepository.findAll();
//     }
//  @Override
//     public LeaveQuota getLeaveQuotaById(int id) {
//         return leaveQuotaRepository.findById(id).orElse(null);
//     }
//  @Override
//     public LeaveQuota createLeaveQuota(LeaveQuota leaveQuota, List<Long> userIds) {
//         Set<User> users = new HashSet<>();
//         for (Long userId : userIds) {
//             User user = userRepository.findById(userId).orElse(null);
//             if (user != null) {
//                 users.add(user);
//             }
//         }
//         leaveQuota.setUsers(users);
//         return leaveQuotaRepository.save(leaveQuota);
//     }


// // @Override
// //     public List<LeaveQuota> getAllLeaveQuotas() {
// //         return leaveQuotaRepository.findAll();
// //     }
// // @Override

// //     public LeaveQuota getLeaveQuotaById(int id) throws LeaveQuotaNotFoundException {
// //         return leaveQuotaRepository.findById(id)
// //         .orElseThrow(() -> new LeaveQuotaNotFoundException("Leave quota not found"));
// //     }
// // @Override

// //     public LeaveQuota createLeaveQuota(LeaveQuota leaveQuota) {
// //         return leaveQuotaRepository.save(leaveQuota);
// //    }
// @Override

//     public LeaveQuota updateLeaveQuota(int id, LeaveQuota leaveQuota) {
//         leaveQuota.setId(id); // Assurez-vous que l'ID de l'entité à mettre à jour correspond à l'ID dans le chemin
//         return leaveQuotaRepository.save(leaveQuota);
//     }
// @Override

//     public void deleteLeaveQuota(int id) {
//         leaveQuotaRepository.deleteById(id);
//     }

//     @Override
//     public List<LeaveQuota> getLeaveQuotaByUser(User user) {
//         return leaveQuotaRepository.findByUser(user);
//     }
// }

// // @Transactional
// // @Service
// // public class LeaveQuotaServiceImpl implements LeaveQuotaService{

// //     private final LeaveQuotaRepository leaveQuotaRepository;
// //     private final LeaveQuotaMapper leaveQuotaMapper;

// //     @Autowired
// //     public LeaveQuotaServiceImpl(LeaveQuotaRepository leaveQuotaRepository, LeaveQuotaMapper leaveQuotaMapper) {
// //         this.leaveQuotaRepository = leaveQuotaRepository;
// //         this.leaveQuotaMapper = leaveQuotaMapper;
// //     }

// //     @Override
// //     public LeaveQuotaDTO addLeaveQuota(LeaveQuotaDTO leaveQuotaDTO) {
// //         LeaveQuota leaveQuota = leaveQuotaMapper.fromLeaveQuotaDTO(leaveQuotaDTO);
// //         LeaveQuota savedLeaveQuota = leaveQuotaRepository.save(leaveQuota);
// //         return leaveQuotaMapper.fromLeaveQuota(savedLeaveQuota);
// //     }

// //     @Override
// //     public LeaveQuotaDTO updateLeaveQuota(int id, LeaveQuotaDTO leaveQuotaDTO) throws LeaveQuotaNotFoundException {
// //         LeaveQuota leaveQuota = leaveQuotaRepository.findById(id)
// //                 .orElseThrow(() -> new LeaveQuotaNotFoundException("LeaveQuota not found"));
        
// //         // Mise à jour des propriétés de l'entité LeaveQuota avec les valeurs du DTO
// //         leaveQuota.setYear(leaveQuotaDTO.getYear());
// //         leaveQuota.setQuota(leaveQuotaDTO.getQuota());
// //         // Vous pouvez également mettre à jour d'autres propriétés ici
        
// //         LeaveQuota updatedLeaveQuota = leaveQuotaRepository.save(leaveQuota);
// //         return leaveQuotaMapper.fromLeaveQuota(updatedLeaveQuota);
// //     }

// //     @Override
// //     public List<LeaveQuotaDTO> getAllLeaveQuotas() {
// //         List<LeaveQuota> allLeaveQuotas = leaveQuotaRepository.findAll();
// //         return allLeaveQuotas.stream()
// //                 .map(leaveQuotaMapper::fromLeaveQuota)
// //                 .collect(Collectors.toList());
// //     }

// //     @Override
// //     public void deleteLeaveQuotaById(int id) {
// //         leaveQuotaRepository.deleteById(id);
// //     }

// //     @Override
// //     public LeaveQuotaDTO findLeaveQuotaById(int id) throws LeaveQuotaNotFoundException {
// //         LeaveQuota leaveQuota = leaveQuotaRepository.findById(id)
// //                 .orElseThrow(() -> new LeaveQuotaNotFoundException("LeaveQuota not found"));
// //         return leaveQuotaMapper.fromLeaveQuota(leaveQuota);
// //     }
// // }
