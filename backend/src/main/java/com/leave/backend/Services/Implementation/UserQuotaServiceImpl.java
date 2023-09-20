package com.leave.backend.Services.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leave.backend.Entities.UserQuota;
import com.leave.backend.Repositories.UserQuotaRepository;
import com.leave.backend.Services.UserQuotaService;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
@Service
@Transactional
@AllArgsConstructor
public class UserQuotaServiceImpl implements UserQuotaService {
     @Autowired
    private UserQuotaRepository userQuotaRepository;

    // ...
@Override
    public double getRemainingQuotaForUser(Long userId, int currentYear) {
        // Rechercher le quota de l'utilisateur pour l'année actuelle
        UserQuota userQuota = userQuotaRepository.findByUserIdAndYear(userId, currentYear);

        if (userQuota != null) {
            return userQuota.getResiduel();
        } else {
            return 0.0; // L'utilisateur n'a pas de quota pour l'année actuelle
        }
    }

}
