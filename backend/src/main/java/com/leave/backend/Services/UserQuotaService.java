package com.leave.backend.Services;

import java.util.List;

import com.leave.backend.Dtos.UserQuotaInfoDTO;

public interface UserQuotaService {
    double getRemainingQuotaForUser(Long userId, int currentYear);
        List<UserQuotaInfoDTO> getQuotaAndResidualByUserId(Long userId);

}
