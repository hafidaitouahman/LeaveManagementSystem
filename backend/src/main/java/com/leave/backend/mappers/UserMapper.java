package com.leave.backend.mappers;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.leave.backend.Dtos.DepartementDTO;
import com.leave.backend.Dtos.SiteDTO;
import com.leave.backend.Dtos.TeamDTO;
import com.leave.backend.Dtos.UserDTO;
import com.leave.backend.Entities.Departement;
import com.leave.backend.Entities.Site;
import com.leave.backend.Entities.Team;
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


//       public SiteDTO fromSite(Site site){
//         SiteDTO siteDTO=new SiteDTO();
//         BeanUtils.copyProperties(site, siteDTO);
//         siteDTO.setName(site.getName());

//         return siteDTO;
//     }
//     public Site fromSiteDTO(SiteDTO siteDTO){
//     Site site=new Site();
//     BeanUtils.copyProperties(siteDTO, site);
//     site.setName(siteDTO.getName());

//     return site;
// }
//         public DepartementDTO fromDepartement(Departement departement){
//         DepartementDTO departementDTO=new DepartementDTO();
//         BeanUtils.copyProperties(departement, departementDTO);
//         return departementDTO;
//     }
//     public Departement fromDepartementDTO(DepartementDTO departementDTO){
//         Departement departement=new Departement();
//         BeanUtils.copyProperties(departementDTO, departement);
//         return departement;
//     }
//           public TeamDTO fromTeam(Team team){
//         TeamDTO teamDTO=new TeamDTO();
//         BeanUtils.copyProperties(team, teamDTO);
//         teamDTO.setName(team.getName());

//         return teamDTO;
//     }
//     public Team fromTeamDTO(TeamDTO teamDTO){
//     Team team=new Team();
//     BeanUtils.copyProperties(teamDTO, team);
//     team.setName(teamDTO.getName());

//     return team;
// }
//     public UserDTO convertToUserDTO(User user) {
//         UserDTO userDTO = new UserDTO();
//         BeanUtils.copyProperties(user, userDTO);
//         userDTO.setTeam(fromTeam(user.getTeam())); // Assurez-vous d'avoir un mapper pour Team
//         userDTO.setDepartement(fromDepartement(user.getDepartement())); // Assurez-vous d'avoir un mapper pour Departement
//         userDTO.setSite(fromSite(user.getSite())); // Assurez-vous d'avoir un mapper pour Site
//         // Ajoutez d'autres mappages ici si nécessaire
//         return userDTO;
//     }

//     public User convertToUser(UserDTO userDTO) {
//         User user = new User();
//         BeanUtils.copyProperties(userDTO, user);
//         user.setTeam(fromTeamDTO(userDTO.getTeam())); // Assurez-vous d'avoir un mapper pour Team
//         user.setDepartement(fromDepartementDTO(userDTO.getDepartement())); // Assurez-vous d'avoir un mapper pour Departement
//         user.setSite(fromSiteDTO(userDTO.getSite())); // Assurez-vous d'avoir un mapper pour Site
//         // Ajoutez d'autres mappages ici si nécessaire
//         return user;
//     }


}

