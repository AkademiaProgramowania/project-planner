package com.akademia.projectplanner.dto;

import com.akademia.projectplanner.enums.Role;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.*;
import javax.validation.groups.Default;

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

  @NotEmpty(message = "Field required")
  @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
          message = "Password must be at least 8 characters long, include at least " +
                  "one uppercase letter, one lowercase letter, one digit, and one special character")
  private String password;
  @NotBlank(message = "Field required")
  private String passwordRepeated;
  private Role role;
}
