package com.akademia.projectplanner.entity;

import com.akademia.projectplanner.enums.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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
}
