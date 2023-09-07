package com.leave.backend.Services;

import java.util.List;
import java.util.Optional;

import com.leave.backend.Dtos.LeaveQuotaDTO;
import com.leave.backend.Entities.LeaveQuota;
import com.leave.backend.Entities.User;
import com.leave.backend.Exceptions.LeaveQuotaNotFoundException;
import com.leave.backend.Repositories.LeaveQuotaRepository;

public interface LeaveQuotaService {
    // LeaveQuotaDTO addLeaveQuota(LeaveQuotaDTO leaveQuotaDTO);
    // LeaveQuotaDTO updateLeaveQuota(int id, LeaveQuotaDTO leaveQuotaDTO) throws LeaveQuotaNotFoundException;
    //  List<LeaveQuotaDTO> getAllLeaveQuotas();
    //  void deleteLeaveQuotaById(int id);
    //  LeaveQuotaDTO findLeaveQuotaById(int id) throws LeaveQuotaNotFoundException;
    List<LeaveQuota> getAllLeaveQuotas();
    LeaveQuota getLeaveQuotaById(int id) throws LeaveQuotaNotFoundException;
    LeaveQuota createLeaveQuota(LeaveQuota leaveQuota);
    LeaveQuota updateLeaveQuota(int id, LeaveQuota leaveQuota);
    void deleteLeaveQuota(int id);
    List<LeaveQuota> getLeaveQuotaByUser(User user);

}
