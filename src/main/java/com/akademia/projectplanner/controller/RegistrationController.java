package com.akademia.projectplanner.controller;

import com.akademia.projectplanner.service.RegistrationService;
import com.akademia.projectplanner.dto.UserDto;
import com.akademia.projectplanner.exception.AuthenticationException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * The RegistrationController handles requests related to user registration, such as displaying a
 * registration form or processing user registration requests.
 */
@AllArgsConstructor
@Controller
public class RegistrationController {

  private RegistrationService registrationService;

  /**
   * Handles GET request for the registration page of the application. Creates a new UserDto object
   * and adds it to the model used by the view to bind input values to the properties of UserDto
   * class.
   *
   * @param model the Spring Model object that contains attributes to be passed to the view
   * @return the name of the view (registration.html) to be rendered by the Spring MVC framework
   */
  @GetMapping("/registration")
  public String getRegistrationPage(Model model) {
    UserDto userDto = new UserDto();
    model.addAttribute("registrationDto", userDto);
    return "registration";
  }

  /**
   * Handles POST request for the registration of the user. If the registration is successful, the
   * user is redirected to the login page.
   *
   * @param userDto the UserDto object that contains the form data submitted by the user
   * @param model the Spring Model object that contains attributes to be passed to the view in case
   *     of errors
   * @return the name of the view (registration.html) to be rendered by the Spring MVC framework in
   *     case of errors, or a redirect to the login page in case of success
   */
  @PostMapping("/registration")
  public String register(@ModelAttribute("registrationDto") UserDto userDto, Model model) {
    try {
      registrationService.register(userDto);
    } catch (IllegalArgumentException i) {
      model.addAttribute("fieldsNotFilledMessage", i.getMessage());
      return "registration";
    } catch (AuthenticationException e) {
      model.addAttribute("registrationErrorMessage", e.getMessage());
      return "registration";
    }
    return "redirect:/login";
  }
}
