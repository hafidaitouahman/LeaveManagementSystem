package com.leave.backend.mappers;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.leave.backend.Dtos.LeaveTypeDTO;
import com.leave.backend.Entities.LeaveType;

@Service
public class LeaveTypeMapper {
    public LeaveTypeDTO fromLeaveType(LeaveType leaveType){
        LeaveTypeDTO leaveTypeDTO=new LeaveTypeDTO();
        BeanUtils.copyProperties(leaveType, leaveTypeDTO);
        return leaveTypeDTO;
    }
    public LeaveType fromLeaveTypeDTO(LeaveTypeDTO leaveTypeDTO){
    LeaveType leaveType=new LeaveType();
    BeanUtils.copyProperties(leaveTypeDTO, leaveType);
    return leaveType;
}
}
