package com.akademia.projectplanner.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller class that handles requests for the login page of the application. The rendered view
 * of this controller is the first page displayed to users.
 */
@Controller
public class LoginController {

  /**
   * Handles GET request for the login page of the application.
   *
   * @return the name of the view (login.html) to be rendered by the Spring MVC framework
   */
  @GetMapping("/login")
  public String getLoginPage() {
    return "login";
  }
}
