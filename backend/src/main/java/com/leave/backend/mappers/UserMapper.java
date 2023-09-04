package com.leave.backend.mappers;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.leave.backend.Dtos.UserDTO;
import com.leave.backend.Entities.User;

@Component
public class UserMapper {

    public Set<User> toUsers(Set<UserDTO> set) {
        return set.stream()
                .map(this::toUser)
                .collect(Collectors.toSet());
    }
   public Set<UserDTO> fromUsers(Set<User> users) {
        return users.stream()
                .map(this::fromUser)
                .collect(Collectors.toSet());
    }
    public UserDTO fromUser(User user) {
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        return userDTO;
    }

    public User toUser(UserDTO userDTO) {
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        return user;
    }
}

