package com.leave.backend.Services;

public interface UserQuotaService {
    double getRemainingQuotaForUser(Long userId, int currentYear);
}
