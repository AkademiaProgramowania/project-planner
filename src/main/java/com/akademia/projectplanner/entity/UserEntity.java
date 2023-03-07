package com.akademia.projectplanner.entity;

import com.akademia.projectplanner.enums.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class UserEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  private String email;
  private String password;

  @Enumerated(EnumType.STRING)
  private Role role;

  @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
  private List<TaskEntity> tasks;

  public UserEntity(String name, String email, String password) {
    this.name = name;
    this.email = email;
    this.password = password;
  }

  //  @Override
  //  public Collection<? extends GrantedAuthority> getAuthorities() {
  //    return List.of(new SimpleGrantedAuthority(role.name()));
  //  }
  //
  //  @Override
  //  public String getUsername() {
  //    return name;
  //  }
  //
  //  @Override
  //  public boolean isAccountNonExpired() {
  //    return true;
  //  }
  //
  //  @Override
  //  public boolean isAccountNonLocked() {
  //    return true;
  //  }
  //
  //  @Override
  //  public boolean isCredentialsNonExpired() {
  //    return true;
  //  }
  //
  //  @Override
  //  public boolean isEnabled() {
  //    return true;
  //  }
  //
  //  @Override
  //  public String getPassword() {
  //    return password;
  //  }
}
