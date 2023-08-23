package com.akademia.projectplanner.dto;

import com.akademia.projectplanner.constants.ConstantClass;
import com.akademia.projectplanner.enums.Role;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.*;
import javax.validation.groups.Default;
import java.util.List;

/** A Data Transfer Object (DTO) for a User. */
@Getter
@Setter
public class UserDto {
  private Long id;

  @NotBlank(message = ConstantClass.UserMessages.FIELD_REQUIRED)
  private String name;

  @Email(message = ConstantClass.UserMessages.VALID_EMAIL)
  @NotBlank(message = ConstantClass.UserMessages.FIELD_REQUIRED)
  private String email;

  @NotEmpty(message = ConstantClass.UserMessages.FIELD_REQUIRED)
  @Pattern(
      regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
      message = ConstantClass.UserMessages.PASSWORD_RESTRICTIONS)
  private String password;

  @NotBlank(message = ConstantClass.UserMessages.FIELD_REQUIRED)
  private String passwordRepeated;

  private Role role;
  private List<TaskDto> tasks;
}
