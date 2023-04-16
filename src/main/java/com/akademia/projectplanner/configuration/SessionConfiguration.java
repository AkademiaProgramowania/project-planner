package com.akademia.projectplanner.configuration;

import com.akademia.projectplanner.state.SessionState;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.SessionScope;

/** This class configures the session management for the application. */
@Configuration
public class SessionConfiguration {

  /**
   * Returns a new instance of the SessionState class and specifies that the scope of the bean is
   * session-scoped.
   *
   * @return a new instance of the SessionState class with session scope
   */
  @Bean
  @SessionScope
  public SessionState getSessionState() {
    return new SessionState();
  }
}
