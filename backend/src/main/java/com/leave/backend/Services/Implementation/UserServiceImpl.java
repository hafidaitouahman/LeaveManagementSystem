package com.leave.backend.Services.Implementation;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leave.backend.Entities.Departement;
import com.leave.backend.Entities.LeaveQuota;
import com.leave.backend.Entities.LeaveRequest;
import com.leave.backend.Entities.Site;
import com.leave.backend.Entities.Team;
import com.leave.backend.Entities.User;
import com.leave.backend.Exceptions.TeamNotFoundException;
import com.leave.backend.Exceptions.UserNotFoundException;
import com.leave.backend.Repositories.DepartementRepository;
import com.leave.backend.Repositories.SiteRepository;
import com.leave.backend.Repositories.TeamRepository;
import com.leave.backend.Repositories.UserRepository;
import com.leave.backend.Services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Transactional
@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {
       private final UserRepository userRepository;
         @Autowired
    private DepartementRepository departementRepository; // Assurez-vous d'avoir le repository approprié pour Departement

    @Autowired
    private TeamRepository teamRepository; // Assurez-vous d'avoir le repository approprié pour Team

    @Autowired
    private SiteRepository siteRepository;
        @Override
        public List<User> getAllUsers() {
            return userRepository.findAll();
        }
        @Override
        public User getUserById(Long userId) {
            return userRepository.findById(userId)
                    .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));
        }
        @Override
        public void deleteUser(Long userId) {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));

            userRepository.delete(user);
        }

        // @Override
        // public User updateUserProfile(Long userId, Date hireDate) {
        //     User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("Utilisateur non trouvé"));
    
        //     // Departement departement = departementRepository.findById(departementId).orElseThrow(() -> new EntityNotFoundException("Département non trouvé"));
        //     // Team team = teamRepository.findById(teamId).orElseThrow(() -> new EntityNotFoundException("Équipe non trouvée"));
        //     // Site site = siteRepository.findById(siteId).orElseThrow(() -> new EntityNotFoundException("Site non trouvé"));
    
        //     // user.setDepartement(departement);
        //     // user.setTeam(team);
        //     // user.setSite(site);
        //     user.setHirDate(hireDate);
    
        //     return userRepository.save(user);
        // }
    
        // @Override
        // public User updateUserProfile(Long userId, User updatedUser) {
        //     User user = userRepository.findById(userId)
        //             .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));

        //     // Update user profile fields based on updatedUser
        //     user.setHirDate(updatedUser.getHirDate());
        //     user.setTeam(updatedUser.getTeam());
        //     user.setDepartement(updatedUser.getDepartement());
        //     user.setSite(updatedUser.getSite());
        //     user.setLeaveQuotas(updatedUser.getLeaveQuotas());
        //     // Update other fields as needed

        //     return userRepository.save(user);
        // }
        @Transactional
        public User updateUserProfile(Long userId, int departementId, int teamId, int siteId, Date hireDate) {
            // Rechercher l'utilisateur existant par son ID
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));
    
            // Rechercher le département par son ID
            Departement departement = departementRepository.findById(departementId)
                    .orElseThrow(() -> new EntityNotFoundException("Department not found with id: " + departementId));
    
            // Rechercher l'équipe par son ID
            Team team = teamRepository.findById(teamId)
                    .orElseThrow(() -> new EntityNotFoundException("Team not found with id: " + teamId));
    
            // Rechercher le site par son ID
            Site site = siteRepository.findById(siteId)
                    .orElseThrow(() -> new EntityNotFoundException("Site not found with id: " + siteId));
    
            // Mettre à jour les champs de l'utilisateur
            user.setHirDate(hireDate);
            user.setDepartement(departement);
            user.setTeam(team);
            user.setSite(site);
    
            // Enregistrer les modifications dans la base de données
            return userRepository.save(user);
        }
        @Override
        public User deactivateUser(Long userId) {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));

            user.setActive(false);

            return userRepository.save(user);
        }

        @Override
        public User activateUser(Long userId) {
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));

            user.setActive(true);

            return userRepository.save(user);
        }

        @Override
        public  List<LeaveQuota> getQuotaByEmployeeId(Long employeeId) {
            User user = userRepository.findById(employeeId)
                    .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + employeeId));

            return user.getLeaveQuotas();
        }

        @Override
        public List<LeaveRequest> getLeaveRequestsByEmployeeId(Long employeeId) {
            User user = userRepository.findById(employeeId)
                    .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + employeeId));

            return user.getLeaveRequests();
        }
        @Override
        public List<User> findUsersByIds(List<Long> userIds) {
            // Use the UserRepository to fetch users by their IDs
            return userRepository.findAllById(userIds);
        }
        @Override
        public User findUserById(Long userId) throws UserNotFoundException {
            return userRepository.findById(userId)  
            .orElseThrow(() -> new UserNotFoundException("not found"));
        }

        }
