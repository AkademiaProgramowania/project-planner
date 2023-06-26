package com.akademia.projectplanner.dto;

import com.akademia.projectplanner.enums.Role;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/** A Data Transfer Object (DTO) for a User. */
@Getter
@Setter
public class UserDto {

  private Long id;

  @NotBlank(message = "Field required")
  private String name;
  @Email(message = "Please enter a valid email address")
  @NotBlank(message = "Field required")
  private String email;
  @NotBlank(message = "Field required")
  private String password;
  @NotBlank(message = "Field required")
  private String passwordRepeated;
  private Role role;
}
