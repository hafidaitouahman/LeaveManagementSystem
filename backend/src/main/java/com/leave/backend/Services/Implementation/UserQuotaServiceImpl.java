package com.leave.backend.Services.Implementation;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leave.backend.Dtos.UserQuotaInfoDTO;
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
 @Override
    public List<UserQuotaInfoDTO> getQuotaAndResidualByUserId(Long userId) {
        List<UserQuotaInfoDTO> quotaInfoList = new ArrayList<>();
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int previousYear = currentYear - 1;

        UserQuota userQuotaCurrentYear = userQuotaRepository.findByUserIdAndYear(userId, currentYear);
        UserQuota userQuotaPreviousYear = userQuotaRepository.findByUserIdAndYear(userId, previousYear);

        if (userQuotaCurrentYear != null) {
            quotaInfoList.add(new UserQuotaInfoDTO(currentYear, userQuotaCurrentYear.getQuota(), userQuotaCurrentYear.getResiduel()));
        }

        if (userQuotaPreviousYear != null) {
            quotaInfoList.add(new UserQuotaInfoDTO(previousYear, userQuotaPreviousYear.getQuota(), userQuotaPreviousYear.getResiduel()));
        }

        return quotaInfoList;
    }
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
