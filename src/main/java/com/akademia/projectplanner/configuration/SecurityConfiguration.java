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

/**
 * This class configures Spring Security for the application. It specifies which endpoints are
 * secured and which aren't, defines authentication and authorization mechanisms, and sets up
 * various security-related options such as CSRF protection and password encoding. The configuration
 * is based on the Spring Security framework and is implemented using Java configuration.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

  @Autowired private UserServiceImpl userService;

  /**
   * Returns an instance of the BCryptPasswordEncoder class, which provides a secure way of storing
   * user credentials.
   *
   * @return a new instance of the BCryptPasswordEncoder class
   */
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  /**
   * Sets up the security configuration for the application, including defining which endpoints are
   * secured, which are not, and what authentication and authorization mechanisms to use.
   *
   * @param http the HttpSecurity instance to configure
   * @return a new instance of SecurityFilterChain that contains the configured security settings
   * @throws Exception if there is an error configuring the HttpSecurity instance
   */
  @Bean
  public SecurityFilterChain configure(HttpSecurity http) throws Exception {
    return http.csrf()
        .ignoringAntMatchers("/console/**")
        .disable()
        .authorizeRequests(
            auth -> {
              auth.antMatchers("/css/**", "/images/**").permitAll();
              auth.antMatchers("/registration", "/console/**").permitAll();
              auth.antMatchers("/")
                  .hasAnyAuthority(
                      Role.ADMINISTRATOR.getRole(),
                      Role.ARCHITECT.getRole(),
                      Role.DEVELOPER.getRole());
              auth.antMatchers("/add-task", "/edit/**")
                  .hasAnyAuthority(Role.ADMINISTRATOR.getRole(), Role.ARCHITECT.getRole());
              auth.anyRequest().authenticated();
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
