package com.leave.backend.Security.services;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.leave.backend.Entities.Departement;
import com.leave.backend.Entities.Site;
import com.leave.backend.Entities.Team;
import com.leave.backend.Entities.User;

public class UserDetailsImpl implements UserDetails {
  private static final long serialVersionUID = 1L;

  private Long id;

  private String username;

  private String email;

  @JsonIgnore
  private String password;



  private Collection<? extends GrantedAuthority> authorities;
  private boolean active;

    private Departement departement;
    private Site site;
    private Team team;
    private String pays;
    private Date hirDate;

    public UserDetailsImpl(Long id, String username, String email, String password,
                           Collection<? extends GrantedAuthority> authorities, boolean active,
                           Departement departement, Site site, Team team, String pays, Date hireDate) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
        this.active = active;
        this.departement = departement;
        this.site = site;
        this.team = team;
        this.pays = pays;
        this.hirDate = hireDate;
    }
  public boolean isActive() {
      return active;
  }

  public void setActive(boolean active) {
      this.active = active;
  }

 

  public static UserDetailsImpl build(User user) {
    List<GrantedAuthority> authorities = user.getRoles().stream()
        .map(role -> new SimpleGrantedAuthority(role.getName().name()))
        .collect(Collectors.toList());

    return new UserDetailsImpl(
      user.getId(),
      user.getUsername(),
      user.getEmail(),
      user.getPassword(),
      authorities,
      user.isActive(),
      user.getDepartement(),  // Ajoutez le département
      user.getSite(),        // Ajoutez le site
      user.getTeam(),        // Ajoutez l'équipe
      user.getPays(),        // Ajoutez le pays
      user.getHirDate()      // Ajoutez la date d'embauche
); 
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }


  public String getPays() {
    return pays;
}

public Date getHirDate() {
    return hirDate;
}
  public Departement getDepartment() {
    return departement;
}

public Site getSite() {
    return site;
}

public Team getTeam() {
    return team;
}
  public Long getId() {
    return id;
  }

  public String getEmail() {
    return email;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    UserDetailsImpl user = (UserDetailsImpl) o;
    return Objects.equals(id, user.id);
  }
}
