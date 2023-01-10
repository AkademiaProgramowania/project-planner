package com.akademia.projectplanner.repository;

import com.akademia.projectplanner.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    boolean existsByEmail(String email);
}
