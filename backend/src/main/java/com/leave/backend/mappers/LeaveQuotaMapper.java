package com.leave.backend.mappers;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.leave.backend.Dtos.LeaveQuotaDTO;
import com.leave.backend.Entities.LeaveQuota;

import java.util.List;
import java.util.stream.Collectors;import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class LeaveQuotaMapper {

    private final UserMapper userMapper; // Assurez-vous d'avoir un mapper pour UserDTO

    public LeaveQuotaMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public LeaveQuotaDTO fromLeaveQuota(LeaveQuota leaveQuota) {
        LeaveQuotaDTO leaveQuotaDTO = new LeaveQuotaDTO();
        BeanUtils.copyProperties(leaveQuota, leaveQuotaDTO);
        // Mapper la liste d'utilisateurs ici
        leaveQuotaDTO.setUsers(userMapper.fromUsers(leaveQuota.getUsers()));
        return leaveQuotaDTO;
    }

    public LeaveQuota fromLeaveQuotaDTO(LeaveQuotaDTO leaveQuotaDTO) {
        LeaveQuota leaveQuota = new LeaveQuota();
        BeanUtils.copyProperties(leaveQuotaDTO, leaveQuota);
        // Mapper la liste d'utilisateurs ici
        leaveQuota.setUsers(userMapper.toUsers(leaveQuotaDTO.getUsers()));
        return leaveQuota;
    }
}
