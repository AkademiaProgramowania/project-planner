package com.akademia.projectplanner.configuration;

import com.akademia.projectplanner.enums.Role;
import com.akademia.projectplanner.handler.AccessDeniedHandler;
import com.akademia.projectplanner.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

  @Autowired private UserServiceImpl userService;

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public SecurityFilterChain configure(HttpSecurity http) throws Exception {
    return http.csrf()
        .ignoringAntMatchers("/console/**")
        .disable()
        .authorizeRequests(
            auth -> {
              auth.antMatchers("/css/**", "/images/**").permitAll();
              auth.antMatchers("/")
                  .hasAnyAuthority(
                      Role.ADMINISTRATOR.getRole(),
                      Role.ARCHITECT.getRole(),
                      Role.DEVELOPER.getRole());
              auth.antMatchers("/add-task", "/edit/**")
                  .hasAnyAuthority(Role.ADMINISTRATOR.getRole(), Role.ARCHITECT.getRole());
              auth.antMatchers("/registration").permitAll();
            })
        .headers()
        .frameOptions()
        .disable()
        .and()
        .formLogin(
            (formLogin) ->
                formLogin
                    .loginPage("/login")
                    .loginProcessingUrl("/login")
                    .defaultSuccessUrl("/")
                    .permitAll())
        .formLogin()
        .and()
        .exceptionHandling()
        .accessDeniedHandler(new AccessDeniedHandler())
        .and()
        .httpBasic(withDefaults())
        .logout()
        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
        .permitAll()
        .and()
        .build();
  }
}
