package com.akademia.projectplanner.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationDto {

    private String name;
    private String email;
    private String password;
    private String passwordRepeated;

}
