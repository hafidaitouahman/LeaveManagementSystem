package com.leave.backend.Controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.leave.backend.Dtos.AddUserRequest;
import com.leave.backend.Dtos.SiteDTO;
import com.leave.backend.Dtos.UserDTO;
import com.leave.backend.Dtos.UserDetailsResponse;
import com.leave.backend.Entities.Departement;
import com.leave.backend.Entities.ERole;
import com.leave.backend.Entities.LeaveQuota;
import com.leave.backend.Entities.LeaveRequest;
import com.leave.backend.Entities.Role;
import com.leave.backend.Entities.Site;
import com.leave.backend.Entities.Team;
import com.leave.backend.Entities.User;
import com.leave.backend.Exceptions.SiteNotFoundException;
import com.leave.backend.Repositories.DepartementRepository;
import com.leave.backend.Repositories.RoleRepository;
import com.leave.backend.Repositories.SiteRepository;
import com.leave.backend.Repositories.TeamRepository;
import com.leave.backend.Repositories.UserRepository;
import com.leave.backend.Security.jwt.JwtUtils;
import com.leave.backend.Services.SiteService;
import com.leave.backend.Services.UserService;
import com.leave.backend.mappers.UserMapper;
import com.leave.backend.payload.response.MessageResponse;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.text.SimpleDateFormat;

@RestController
@CrossOrigin(origins = "http://localhost:4200" , maxAge = 3600, allowCredentials="true")

@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;
      @Autowired
    private UserMapper userMapper;
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepository userRepository;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  DepartementRepository departementRepository;
  
  @Autowired
  SiteRepository siteRepository;

  @Autowired
  TeamRepository teamRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;


    @PostMapping("/add")
public ResponseEntity<?> addUserByRH(@Valid @RequestBody AddUserRequest addUserRequest) {
    // Vérifiez si l'utilisateur actuel a le rôle "RH"
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    boolean isRH = authentication.getAuthorities().stream()
            .anyMatch(role -> role.getAuthority().equals("ROLE_RH"));

    if (!isRH) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new MessageResponse("Vous n'avez pas l'autorisation d'ajouter un utilisateur."));
    }

    if (userRepository.existsByUsername(addUserRequest.getUsername())) {
        return ResponseEntity.badRequest().body(new MessageResponse("Erreur : le nom d'utilisateur est déjà pris !"));
    }

    if (userRepository.existsByEmail(addUserRequest.getEmail())) {
        return ResponseEntity.badRequest().body(new MessageResponse("Erreur : l'adresse e-mail est déjà utilisée !"));
    }

    // Créez un nouvel utilisateur avec le nom d'utilisateur, l'e-mail et le mot de passe fournis
    User user = new User(addUserRequest.getUsername(),
                         addUserRequest.getEmail(),
                         encoder.encode(addUserRequest.getPassword()),
                         addUserRequest.getPays(),
                         addUserRequest.getHirDate());
    user.setActive(true);

    // Récupérez le département, l'équipe et le site à partir des IDs fournis
    Departement departement = departementRepository.findById(addUserRequest.getDepartementId())
            .orElseThrow(() -> new RuntimeException("Erreur : le département n'a pas été trouvé."));

    Team team = teamRepository.findById(addUserRequest.getTeamId())
            .orElseThrow(() -> new RuntimeException("Erreur : l'équipe n'a pas été trouvée."));

    Site site = siteRepository.findById(addUserRequest.getSiteId())
            .orElseThrow(() -> new RuntimeException("Erreur : le site n'a pas été trouvé."));

    // Associez le département, l'équipe et le site à l'utilisateur
    user.setDepartement(departement);
    user.setTeam(team);
    user.setSite(site);

    Set<Role> roles = new HashSet<>();
    Role userRole = roleRepository.findByName(ERole.ROLE_USER)
          .orElseThrow(() -> new RuntimeException("Erreur : le rôle n'a pas été trouvé."));
    roles.add(userRole);
    user.setRoles(roles);

    userRepository.save(user);

   //return ResponseEntity.ok(new MessageResponse("Utilisateur ajouté avec succès !"));
   
// Récupérez les informations détaillées de l'utilisateur
String departementName = departement.getName(); // Supposons que le département a un attribut "name"
String teamName = team.getName();               // Supposons que l'équipe a un attribut "name"
String siteName = site.getName();               // Supposons que le site a un attribut "name"

// Créez une instance de UserDetailsResponse avec les informations détaillées de l'utilisateur
UserDetailsResponse userDetailsResponse = new UserDetailsResponse();
userDetailsResponse.setId(user.getId());
userDetailsResponse.setUsername(user.getUsername());
userDetailsResponse.setEmail(user.getEmail());
userDetailsResponse.setPays(user.getPays());
userDetailsResponse.setDepartementName(departementName);
userDetailsResponse.setTeamName(teamName);
userDetailsResponse.setSiteName(siteName);
userDetailsResponse.setHirDate(user.getHirDate()); // Définissez hirDate


return ResponseEntity.ok(userDetailsResponse);
}
@GetMapping("/{userId}")
public ResponseEntity<?> getUserById(@PathVariable Long userId) {
    // Recherchez l'utilisateur par son ID dans la base de données
    Optional<User> userOptional = userRepository.findById(userId);

    if (!userOptional.isPresent()) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("Utilisateur non trouvé avec l'ID : " + userId));
    }

    User user = userOptional.get();

    // Récupérez les informations détaillées de l'utilisateur (y compris le nom du département, de l'équipe et du site)
    String departementName = user.getDepartement() != null ? user.getDepartement().getName() : "";
    String teamName = user.getTeam() != null ? user.getTeam().getName() : "";
    String siteName = user.getSite() != null ? user.getSite().getName() : "";

    // Créez une instance de UserDetailsResponse avec les informations détaillées de l'utilisateur
    UserDetailsResponse userDetailsResponse = new UserDetailsResponse();
    userDetailsResponse.setId(user.getId());
    userDetailsResponse.setUsername(user.getUsername());
    userDetailsResponse.setEmail(user.getEmail());
    userDetailsResponse.setPays(user.getPays());
    userDetailsResponse.setDepartementName(departementName);
    userDetailsResponse.setTeamName(teamName);
    userDetailsResponse.setSiteName(siteName);
    userDetailsResponse.setHirDate(user.getHirDate()); // Définissez hirDate


    return ResponseEntity.ok(userDetailsResponse);
}

//  @GetMapping("/all")
//     public ResponseEntity<List<UserDTO>> getAllUsers() {
//         List<User> users = userService.getAllUsers(); // Assurez-vous d'avoir une méthode pour récupérer tous les utilisateurs
//         List<UserDTO> userDTOs = users.stream()
//                 .map(userMapper::convertToUserDTO)
//                 .collect(Collectors.toList());
//         return ResponseEntity.ok(userDTOs);
//     }
    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
    // @GetMapping("/{userId}")
    //     public ResponseEntity<User> getUserById(@PathVariable Long userId) {
    //         try {
    //             User user = userService.getUserById(userId);
    //             return ResponseEntity.ok(user);
    //         } catch (EntityNotFoundException e) {
    //             // Handle the case where the user is not found
    //             return ResponseEntity.notFound().build();
    //         }
    //     }
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok("User deleted successfully");
    }
    // @PutMapping("/{userId}")
    // @PreAuthorize("hasRole('RH')")
    // public ResponseEntity<User> updateUserProfile(
    //         @PathVariable Long userId,

    //         @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date hireDate) {
    //     User updatedUser = userService.updateUserProfile(userId, hireDate);
    //     return ResponseEntity.ok(updatedUser);
    // }

    // @PutMapping("/{userId}")
    // public ResponseEntity<User> updateUserProfile(@PathVariable Long userId, @RequestBody User updatedUser) {
    //     User user = userService.updateUserProfile(userId, updatedUser);
    //     return ResponseEntity.ok(user);
    // }


//     @PutMapping("/{userId}")
// public ResponseEntity<User> updateUserProfile(
//         @PathVariable Long userId,
//         @RequestParam int departementId,
//         @RequestParam int teamId,
//         @RequestParam int siteId,
//         @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date hireDate) {
//     try {
//         User updatedUser = userService.updateUserProfile(userId, departementId, teamId, siteId, hireDate);
//         return ResponseEntity.ok(updatedUser);
//     } catch (EntityNotFoundException e) {
//         // Handle the case where the user is not found
//         return ResponseEntity.notFound().build();
//     }
// }
@PutMapping("/{userId}")
public ResponseEntity<User> updateUserProfile(
        @PathVariable Long userId,
        @RequestBody Map<String, Object> requestParams) {
    try {
        int departementId = (int) requestParams.get("departementId");
        int teamId = (int) requestParams.get("teamId");
        int siteId = (int) requestParams.get("siteId");
        
        String hireDateStr = String.valueOf(requestParams.get("hireDate"));
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date hireDate = dateFormat.parse(hireDateStr);

        User updatedUser = userService.updateUserProfile(userId, departementId, teamId, siteId, hireDate);
        return ResponseEntity.ok(updatedUser);
    } catch (EntityNotFoundException e) {
        // Handle the case where the user is not found
        return ResponseEntity.notFound().build();
    } catch (Exception e) {
        // Handle errors, including date format errors
        return ResponseEntity.badRequest().build();
    }
}


    @PutMapping("/{userId}/deactivate")
    public ResponseEntity<User> deactivateUser(@PathVariable Long userId) {
        User user = userService.deactivateUser(userId);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{userId}/activate")
    public ResponseEntity<User> activateUser(@PathVariable Long userId) {
        User user = userService.activateUser(userId);
        return ResponseEntity.ok(user);
    }


}
