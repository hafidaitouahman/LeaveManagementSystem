package com.leave.backend.Controllers;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

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
import com.leave.backend.Repositories.LeaveQuotaRepository;
import com.leave.backend.Repositories.UserRepository;
import com.leave.backend.Services.LeaveQuotaService;
import com.leave.backend.Services.UserService;
import com.leave.backend.mappers.UserMapper;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/leavequotas")
public class LeaveQuotaController {
    @Autowired
    private LeaveQuotaService leaveQuotaService;

    // @PostMapping("/create")
    // //@PreAuthorize("hasRole('RH')")
    // public ResponseEntity<LeaveQuota> createLeaveQuota(@RequestBody Map<String, Object> requestBody) {
    //     // Récupérez les valeurs du JSON
    //     String name = (String) requestBody.get("name");
    //     Double quota = (Double) requestBody.get("quota");
    //     String pays = (String) requestBody.get("pays");

    //     // Appelez le service pour créer le quota
    //     LeaveQuota newQuota = leaveQuotaService.createLeaveQuota(name, quota,pays);
        
    //     return ResponseEntity.ok(newQuota);
    // }
    @PostMapping("/create")
    public ResponseEntity<LeaveQuota> createLeaveQuota(@RequestBody LeaveQuotaDTO leaveQuotaDTO) {
        String name = leaveQuotaDTO.getName();
        Double quota = leaveQuotaDTO.getQuota();
        String pays = leaveQuotaDTO.getPays();
    
        // Appelez le service pour créer le quota
        LeaveQuota newQuota = leaveQuotaService.createLeaveQuota(name, quota, pays);
    
        return ResponseEntity.ok(newQuota);
    }
    

    @PutMapping("/update/{quotaId}")
    @PreAuthorize("hasRole('RH')")
    public ResponseEntity<LeaveQuota> updateLeaveQuota(
        @PathVariable Long quotaId,
        @RequestBody LeaveQuotaDTO request
    ) {
        LeaveQuota updatedQuota = leaveQuotaService.updateLeaveQuota(quotaId, request);
        return ResponseEntity.ok(updatedQuota);
    }

        @GetMapping("/all")
        @PreAuthorize("hasRole('RH')")
    public List<LeaveQuota> getAllLeaveQuotas() {
        return leaveQuotaService.getAllLeaveQuotas();
    }
    @DeleteMapping("/delete/{quotaId}")
    @PreAuthorize("hasRole('RH')")
public ResponseEntity<String> deleteLeaveQuota(@PathVariable Long quotaId) {
    try {
        leaveQuotaService.deleteLeaveQuota(quotaId);
        return ResponseEntity.ok("Leave Quota with ID " + quotaId + " has been deleted successfully.");
    } catch (LeaveQuotaNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Leave Quota with ID " + quotaId + " not found.");
    }
}

@GetMapping("/{quotaId}")
public ResponseEntity<LeaveQuota> getLeaveQuotaById(@PathVariable Long quotaId) {
    try {
        LeaveQuota leaveQuota = leaveQuotaService.getLeaveQuotaById(quotaId);
        return ResponseEntity.ok(leaveQuota);
    } catch (LeaveQuotaNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
}


}
// @Transactional
// public class LeaveQuotaController {

//     private final LeaveQuotaService leaveQuotaService;
//     private final UserService userService;
//     private final UserMapper userMapper;
//  private final UserRepository userRepository;
//     @Autowired
//     public LeaveQuotaController(LeaveQuotaService leaveQuotaService,UserService userService,UserMapper userMapper,UserRepository userRepository) {
//         this.leaveQuotaService = leaveQuotaService;
//         this.userService= userService;
//         this.userMapper=userMapper;
//         this.userRepository=userRepository;
//         // this.leaveQuotaRepository= leaveQuotaRepository;
//     }

//     // @GetMapping
//     // public ResponseEntity<List<LeaveQuota>> getAllLeaveQuotas() {
//     //     List<LeaveQuota> leaveQuotas = leaveQuotaService.getAllLeaveQuotas();
//     //     return ResponseEntity.ok(leaveQuotas);
//     // }
//     @GetMapping
//     public List<LeaveQuota> getAllLeaveQuotas() {
//         return leaveQuotaService.getAllLeaveQuotas();
//     }

//     // @GetMapping("/{id}")
//     // public LeaveQuota getLeaveQuotaById(@PathVariable int id) {
//     //     return leaveQuotaService.getQuotaWithUsersById(id);

//     // }

//     @PostMapping
//     public LeaveQuota createLeaveQuota(@RequestBody LeaveQuota leaveQuota, @RequestParam("userIds") List<Long> userIds) {
//         return leaveQuotaService.createLeaveQuota(leaveQuota, userIds);
//     }

//     // @PostMapping("/{year}")
//     // public ResponseEntity<String> copyQuotaToInitialQuota(@PathVariable int year) {
//     //     try {
//     //         // Recherchez la quota par année
//     //         LeaveQuota leaveQuota = leaveQuotaService.getLeaveQuotaByYear(year);
    
//     //         if (leaveQuota != null) {
//     //             // Si la quota existe, copiez la valeur de la quota dans la valeur du quota initial de chaque utilisateur
             
//     //                 // Récupérez tous les utilisateurs associés à cette quota
//     //         List<User> users = userService.getUsersByLeaveQuota(leaveQuota);
        
//     //                 // Copiez la valeur de la quota dans la valeur du quota initial de chaque utilisateur
//     //                 for (User user : users) {
//     //                     user.setQuotaInitial(leaveQuota.getQuota());
//     //                     userRepository.save(user);
//     //                 }
                
//     //             return ResponseEntity.ok("Quota copied to initial quota successfully.");
//     //         } else {
//     //             // Si la quota n'existe pas, renvoyez un message d'erreur
//     //             return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Quota for the specified year not found.");
//     //         }
//     //     } catch (Exception e) {
//     //         // Gérez les exceptions ici, si nécessaire
//     //         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to copy quota to initial quota.");
//     //     }
//     // }

  
//     @GetMapping("/{quotaId}/testUsers")
//     public ResponseEntity<Set<User>> testGetUsersByLeaveQuota(@PathVariable int quotaId) {
//         try {
//             LeaveQuota leaveQuota = leaveQuotaService.getLeaveQuotaById(quotaId);

//             if (leaveQuota != null) {
//                 Set<User> users = leaveQuota.getUsers();
//                 return new ResponseEntity<>(users, HttpStatus.OK);
//             } else {
//                 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//             }
//         } catch (Exception e) {
//             return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//         }
//     }
 

// //last
//     // @GetMapping("/all")
//     // public ResponseEntity<List<LeaveQuota>> getAllLeaveQuotas() {
//     //     List<LeaveQuota> leaveQuotas = leaveQuotaService.getAllLeaveQuotas();
//     //     return ResponseEntity.ok(leaveQuotas);
//     // }

//     @GetMapping("/year/{year}")
//     public ResponseEntity<LeaveQuota> getLeaveQuotaByYear(@PathVariable int year) {
//         LeaveQuota leaveQuota = leaveQuotaService.getLeaveQuotaByYear(year);
//         if (leaveQuota != null) {
//             return ResponseEntity.ok(leaveQuota);
//         } else {
//             return ResponseEntity.notFound().build();
//         }
//     }
    

//     // @GetMapping("/{id}")
//     // public ResponseEntity<LeaveQuota> getLeaveQuotaById(@PathVariable int id) {
//     //     LeaveQuota leaveQuota = leaveQuotaService.getQuotaWithUsersById(id);
//     //     return leaveQuota.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
//     //             .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
//     // }

//     @GetMapping("/{quotaId}")
// public ResponseEntity<?> getQuotaWithUsers(@PathVariable int quotaId) {
//     try {
//         LeaveQuota quota = leaveQuotaService.getQuotaWithUsersById(quotaId);
//         return ResponseEntity.ok(quota);
//     } catch (LeaveQuotaNotFoundException e) {
//         return ResponseEntity.notFound().build();
//     }
// }


//     //last
//     // @GetMapping("/{id}")
//     // public ResponseEntity<LeaveQuota> getLeaveQuotaById(@PathVariable int id) throws LeaveQuotaNotFoundException {
//     //     LeaveQuota leaveQuota = leaveQuotaService.getLeaveQuotaById(id);
//     //     if (leaveQuota != null) {
//     //         return new ResponseEntity<>(leaveQuota, HttpStatus.OK);
//     //     } else {
//     //         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//     //     }
//     // }


    
    
//     // @PostMapping("/add")
//     // public ResponseEntity<LeaveQuota> createLeaveQuota(@RequestBody LeaveQuota leaveQuota) {
//     //     LeaveQuota createdLeaveQuota = leaveQuotaService.createLeaveQuota(leaveQuota);
//     //     return new ResponseEntity<>(createdLeaveQuota, HttpStatus.CREATED);
//     // }


//     //last
//     // @PostMapping
//     // public ResponseEntity<LeaveQuota> createLeaveQuota(@RequestBody LeaveQuota leaveQuota, @RequestParam List<Long> userIds) {
//     //     // Assign users to the LeaveQuota using their IDs
//     //     List<User> users = userService.findUsersByIds(userIds);
    
//     //     // Explicitly specify the type parameter (User) for HashSet
//     //     leaveQuota.setUsers(new HashSet<User>(users));
    
//     //     LeaveQuota createdLeaveQuota = leaveQuotaService.createLeaveQuota(leaveQuota);
//     //     return new ResponseEntity<>(createdLeaveQuota, HttpStatus.CREATED);
//     // }
    

    
//     @PutMapping("/{id}")
//     public ResponseEntity<LeaveQuota> updateLeaveQuota(@PathVariable int id, @RequestBody LeaveQuota leaveQuota) {
//         LeaveQuota updatedLeaveQuota = leaveQuotaService.updateLeaveQuota(id, leaveQuota);
//         return new ResponseEntity<>(updatedLeaveQuota, HttpStatus.OK);
//     }

//     @DeleteMapping("/{id}")
//     public ResponseEntity<Void> deleteLeaveQuota(@PathVariable int id) {
//         leaveQuotaService.deleteLeaveQuota(id);
//         return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//     }

//     @GetMapping("/user/{userId}")
// public ResponseEntity<List<LeaveQuota>> getLeaveQuotaByUser(@PathVariable Long userId) {
//     try {
//         User user = userService.findUserById(userId);
//         List<LeaveQuota> leaveQuotas = leaveQuotaService.getLeaveQuotaByUser(user);
//         return new ResponseEntity<>(leaveQuotas, HttpStatus.OK);
//     } catch (UserNotFoundException e) {
//         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//     }
// }
// // @GetMapping("/{quotaId}/users")
// //     public ResponseEntity<List<String>> getUsersByLeaveQuota(@PathVariable int quotaId) {
// //         try {
// //            LeaveQuota leaveQuota = leaveQuotaService.getLeaveQuotaById(quotaId);

// //             if (leaveQuota != null) {
// //                 List<User> users = userService.getUsersByLeaveQuota(leaveQuota);
// //                 List<String> userNames = users.stream()
// //                         .map(User::getUsername) // Assuming "getUsername" returns the user's name.
// //                         .collect(Collectors.toList());

// //                 return new ResponseEntity<>(userNames, HttpStatus.OK);
// //             } else {
// //                 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
// //             }
// //         } catch (Exception e) {
// //             return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
// //         }
// //     }


// // @GetMapping("/{quotaId}/users")
// // public ResponseEntity<List<User>> getUsersByLeaveQuota(@PathVariable int quotaId) {
// //     try {
// //         LeaveQuota leaveQuota = leaveQuotaService.getLeaveQuotaById(quotaId);

// //         if (leaveQuota != null) {
// //             List<User> users = userService.getUsersByLeaveQuota(leaveQuota);
// //             return new ResponseEntity<>(users, HttpStatus.OK);
// //         } else {
// //             return new ResponseEntity<>(HttpStatus.NOT_FOUND);
// //         }
// //     } catch (Exception e) {
// //         return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
// //     }
// // }

// // }




// @GetMapping("/{quotaId}/users")
// public ResponseEntity<List<UserDTO>> getUsersByLeaveQuota(@PathVariable int quotaId) {
//     try {
//         LeaveQuota leaveQuota = leaveQuotaService.getLeaveQuotaById(quotaId);

//         if (leaveQuota != null) {
//             List<User> users = userService.getUsersByLeaveQuota(leaveQuota);
//             List<UserDTO> userDTOs = users.stream()
//                     .map(userMapper::fromUser) // You may need to define a UserMapper.
//                     .collect(Collectors.toList());

//             return new ResponseEntity<>(userDTOs, HttpStatus.OK);
//         } else {
//             return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//         }
//     } catch (Exception e) {
//         return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//     }
// }
// }

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

