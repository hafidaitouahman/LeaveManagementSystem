package com.leave.backend.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.leave.backend.Services.UserQuotaService;
import com.leave.backend.Dtos.UserQuotaInfoDTO;
import com.leave.backend.Entities.UserQuota;
import com.leave.backend.Security.services.UserDetailsImpl;
@RestController
@RequestMapping("/api/userquota")
public class UserQuotaController {

    private final UserQuotaService userQuotaService;

    @Autowired
    public UserQuotaController(UserQuotaService userQuotaService) {
        this.userQuotaService = userQuotaService;
    }

    @GetMapping("/{userId}")
    public List<UserQuotaInfoDTO> getUserQuotaInfo(@PathVariable Long userId) {
        return userQuotaService.getQuotaAndResidualByUserId(userId);
    }

    @GetMapping("/quota")
public ResponseEntity<List<UserQuotaInfoDTO>> getQuotaAndResidualForCurrentUser(
        @AuthenticationPrincipal UserDetailsImpl userDetails) {
    try {
        Long userId = userDetails.getId();
        List<UserQuotaInfoDTO> quotaInfoList = userQuotaService.getQuotaAndResidualByUserId(userId);
        return ResponseEntity.ok(quotaInfoList);
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
}