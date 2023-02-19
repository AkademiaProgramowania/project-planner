package com.akademia.projectplanner.configuration;

import com.akademia.projectplanner.enums.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("administrator")
                .password(passwordEncoder().encode("adminPass"))
                .roles(Role.ADMINISTRATOR.getRole())
                .build());
        manager.createUser(User.withUsername("architect")
                .password(passwordEncoder().encode("architectPass"))
                .roles(Role.ARCHITECT.getRole())
                .build());
        manager.createUser(User.withUsername("developer")
                .password(passwordEncoder().encode("developerPass"))
                .roles(Role.DEVELOPER.getRole())
                .build());
        return manager;
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                .authorizeRequests(auth -> {
                    auth.antMatchers("/").permitAll();
                    auth.antMatchers("/add-task").hasAnyRole(Role.ADMINISTRATOR.getRole(), Role.ARCHITECT.getRole());
                    auth.antMatchers("/edit").hasAnyRole(Role.ADMINISTRATOR.getRole(), Role.ARCHITECT.getRole());
                })
                .formLogin()
                .and()
                .httpBasic(withDefaults())
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll()
                .and()
                .build();
    }
}
