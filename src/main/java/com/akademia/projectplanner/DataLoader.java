package com.akademia.projectplanner;

import com.akademia.projectplanner.entity.UserEntity;
import com.akademia.projectplanner.enums.Role;
import com.akademia.projectplanner.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private UserRepository userRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        UserEntity admin = new UserEntity();
        admin.setEmail("aaa@com");
        admin.setName("aaa");
        admin.setPassword(new BCryptPasswordEncoder().encode("aaa"));
        admin.setRole(Role.ADMINISTRATOR);
        userRepository.save(admin);

        UserEntity dev = new UserEntity();
        dev.setEmail("ddd@com");
        dev.setName("ddd");
        dev.setPassword(new BCryptPasswordEncoder().encode("ddd"));
        dev.setRole(Role.DEVELOPER);
        userRepository.save(dev);
    }
}
