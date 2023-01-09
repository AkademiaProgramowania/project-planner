package com.akademia.projectplanner.service;

import com.akademia.projectplanner.dto.RegistrationDto;
import com.akademia.projectplanner.entity.UserEntity;
import com.akademia.projectplanner.exception.RegistrationException;
import com.akademia.projectplanner.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class RegistrationService {

    private UserRepository userRepository;

    public void register(RegistrationDto registrationDto) {
        if (checkMandatoryFieldsInRegistration(registrationDto)) {
            throw new IllegalArgumentException("Mandatory fields are not filled in!");
        }
        if (userRepository.existsByEmail(registrationDto.getEmail()) ||
                !checkPasswordCorrectness(registrationDto)) {
            throw new RegistrationException("Email or password is not correct!");
        }
        UserEntity userEntity = new UserEntity(registrationDto.getName(), registrationDto.getEmail(),
                registrationDto.getPassword());
        userRepository.save(userEntity);
    }

    private boolean checkMandatoryFieldsInRegistration(RegistrationDto registrationDto) {
        return registrationDto.getName().isBlank() || registrationDto.getEmail().isBlank() ||
                registrationDto.getPassword().isBlank();

    }

    private boolean checkPasswordCorrectness(RegistrationDto registrationDto) {
        return registrationDto.getPassword().equals(registrationDto.getPasswordRepeated());
    }
}
