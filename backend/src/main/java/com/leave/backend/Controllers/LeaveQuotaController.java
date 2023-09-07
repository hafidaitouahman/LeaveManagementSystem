package com.leave.backend.Controllers;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.leave.backend.Dtos.LeaveQuotaDTO;
import com.leave.backend.Dtos.UserDTO;
import com.leave.backend.Entities.LeaveQuota;
import com.leave.backend.Entities.User;
import com.leave.backend.Exceptions.LeaveQuotaNotFoundException;
import com.leave.backend.Exceptions.UserNotFoundException;
import com.leave.backend.Services.LeaveQuotaService;
import com.leave.backend.Services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/leavequotas")
public class LeaveQuotaController {

    private final LeaveQuotaService leaveQuotaService;
    private final UserService userService;
    @Autowired
    public LeaveQuotaController(LeaveQuotaService leaveQuotaService,UserService userService) {
        this.leaveQuotaService = leaveQuotaService;
        this.userService= userService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<LeaveQuota>> getAllLeaveQuotas() {
        List<LeaveQuota> leaveQuotas = leaveQuotaService.getAllLeaveQuotas();
        return new ResponseEntity<>(leaveQuotas, HttpStatus.OK);
    }

    // @GetMapping("/{id}")
    // public ResponseEntity<LeaveQuota> getLeaveQuotaById(@PathVariable int id) {
    //     Optional<LeaveQuota> leaveQuota = leaveQuotaService.getLeaveQuotaById(id);
    //     return leaveQuota.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
    //             .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    // }
    @GetMapping("/{id}")
    public ResponseEntity<LeaveQuota> getLeaveQuotaById(@PathVariable int id) throws LeaveQuotaNotFoundException {
        LeaveQuota leaveQuota = leaveQuotaService.getLeaveQuotaById(id);
        if (leaveQuota != null) {
            return new ResponseEntity<>(leaveQuota, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    // @PostMapping("/add")
    // public ResponseEntity<LeaveQuota> createLeaveQuota(@RequestBody LeaveQuota leaveQuota) {
    //     LeaveQuota createdLeaveQuota = leaveQuotaService.createLeaveQuota(leaveQuota);
    //     return new ResponseEntity<>(createdLeaveQuota, HttpStatus.CREATED);
    // }

    @PostMapping
    public ResponseEntity<LeaveQuota> createLeaveQuota(@RequestBody LeaveQuota leaveQuota, @RequestParam List<Long> userIds) {
        // Assign users to the LeaveQuota using their IDs
        List<User> users = userService.findUsersByIds(userIds);
    
        // Explicitly specify the type parameter (User) for HashSet
        leaveQuota.setUsers(new HashSet<User>(users));
    
        LeaveQuota createdLeaveQuota = leaveQuotaService.createLeaveQuota(leaveQuota);
        return new ResponseEntity<>(createdLeaveQuota, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LeaveQuota> updateLeaveQuota(@PathVariable int id, @RequestBody LeaveQuota leaveQuota) {
        LeaveQuota updatedLeaveQuota = leaveQuotaService.updateLeaveQuota(id, leaveQuota);
        return new ResponseEntity<>(updatedLeaveQuota, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLeaveQuota(@PathVariable int id) {
        leaveQuotaService.deleteLeaveQuota(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/user/{userId}")
public ResponseEntity<List<LeaveQuota>> getLeaveQuotaByUser(@PathVariable Long userId) {
    try {
        User user = userService.findUserById(userId);
        List<LeaveQuota> leaveQuotas = leaveQuotaService.getLeaveQuotaByUser(user);
        return new ResponseEntity<>(leaveQuotas, HttpStatus.OK);
    } catch (UserNotFoundException e) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
@GetMapping("/{quotaId}/users")
    public ResponseEntity<List<String>> getUsersByLeaveQuota(@PathVariable int quotaId) {
        try {
           LeaveQuota leaveQuota = leaveQuotaService.getLeaveQuotaById(quotaId);

            if (leaveQuota != null) {
                List<User> users = userService.getUsersByLeaveQuota(leaveQuota);
                List<String> userNames = users.stream()
                        .map(User::getUsername) // Assuming "getUsername" returns the user's name.
                        .collect(Collectors.toList());

                return new ResponseEntity<>(userNames, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

// @RestController
// @RequestMapping("/api/leavequotas")
// public class LeaveQuotaController {

//     private final LeaveQuotaService leaveQuotaService;

//     @Autowired
//     public LeaveQuotaController(LeaveQuotaService leaveQuotaService) {
//         this.leaveQuotaService = leaveQuotaService;
//     }

//     @GetMapping("/all")
//     public ResponseEntity<List<LeaveQuotaDTO>> getAllLeaveQuotas() {
//         List<LeaveQuotaDTO> leaveQuotas = leaveQuotaService.getAllLeaveQuotas();
//         return new ResponseEntity<>(leaveQuotas, HttpStatus.OK);
//     }

//     @GetMapping("/{id}")
//     public ResponseEntity<LeaveQuotaDTO> getLeaveQuotaById(@PathVariable int id) {
//         try {
//             LeaveQuotaDTO leaveQuota = leaveQuotaService.findLeaveQuotaById(id);
//             return new ResponseEntity<>(leaveQuota, HttpStatus.OK);
//         } catch (LeaveQuotaNotFoundException e) {
//             return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//         }
//     }

//     @PostMapping("/add")
//     public ResponseEntity<LeaveQuotaDTO> createLeaveQuota(@RequestBody LeaveQuotaDTO leaveQuotaDTO) {
//         LeaveQuotaDTO createdLeaveQuota = leaveQuotaService.addLeaveQuota(leaveQuotaDTO);
//         return new ResponseEntity<>(createdLeaveQuota, HttpStatus.CREATED);
//     }

//     @PutMapping("/{id}")
//     public ResponseEntity<LeaveQuotaDTO> updateLeaveQuota(@PathVariable int id, @RequestBody LeaveQuotaDTO leaveQuotaDTO) {
//         try {
//             LeaveQuotaDTO updatedLeaveQuota = leaveQuotaService.updateLeaveQuota(id, leaveQuotaDTO);
//             return new ResponseEntity<>(updatedLeaveQuota, HttpStatus.OK);
//         } catch (LeaveQuotaNotFoundException e) {
//             return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//         }
//     }

//     @DeleteMapping("/{id}")
//     public ResponseEntity<Void> deleteLeaveQuota(@PathVariable int id) {
//         leaveQuotaService.deleteLeaveQuotaById(id);
//         return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//     }
// }

