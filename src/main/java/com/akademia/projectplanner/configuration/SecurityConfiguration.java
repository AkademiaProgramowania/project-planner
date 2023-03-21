package com.akademia.projectplanner.configuration;

import com.akademia.projectplanner.enums.Role;
import com.akademia.projectplanner.service.impl.UserServiceImpl;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
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

    @Autowired
    private UserServiceImpl userService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//  @Bean
//  public UserDetailsService userDetailsService() {
//    InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//    manager.createUser(
//        User.withUsername("administrator")
//            .password(passwordEncoder().encode("adminPass"))
//            .roles(Role.ADMINISTRATOR.getRole())
//            .build());
//    manager.createUser(
//        User.withUsername("architect")
//            .password(passwordEncoder().encode("architectPass"))
//            .roles(Role.ARCHITECT.getRole())
//            .build());
//    manager.createUser(
//        User.withUsername("developer")
//            .password(passwordEncoder().encode("developerPass"))
//            .roles(Role.DEVELOPER.getRole())
//            .build());
//    return manager;
//  }

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
                            auth.antMatchers("/add-task", "/edit")
                                    .hasAnyAuthority(Role.ADMINISTRATOR.getRole(), Role.ARCHITECT.getRole());
                            auth.antMatchers("registration").permitAll();
                            auth.anyRequest().authenticated();
                        })
                .headers()
                .frameOptions()
                .disable()
                .and()
                .formLogin((formLogin) -> formLogin.loginPage("/login").loginProcessingUrl("/login").defaultSuccessUrl("/").permitAll())
                .formLogin()
                .failureUrl("/login")
                .and()
                .httpBasic(withDefaults())
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .permitAll()
                .and()
                .build();
    }


}
