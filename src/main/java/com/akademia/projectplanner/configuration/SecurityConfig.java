package com.akademia.projectplanner.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Bean
  public PasswordEncoder getPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public InMemoryUserDetailsManager getUserDetails() {
    UserDetails user =
        User.withUsername("user")
            .password(getPasswordEncoder().encode("user1"))
            .roles("USER")
            .build();
    UserDetails admin =
        User.withUsername("admin")
            .password(getPasswordEncoder().encode("admin1"))
            .roles("ADMIN")
            .build();
    return new InMemoryUserDetailsManager(Arrays.asList(user, admin));
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    return http.authorizeRequests(
            (auth) -> {
              auth.antMatchers("/").permitAll();
              auth.antMatchers("/add-task").hasAnyRole("USER", "ADMIN");
              auth.antMatchers("/edit").hasAnyRole("USER", "ADMIN");
            })
        .formLogin((formLogin) -> formLogin.permitAll())
        .logout()
        .and()
        .build();
  }
}
