package com.akademia.projectplanner.controller;

import com.akademia.projectplanner.dto.UserDto;
import com.akademia.projectplanner.exception.AuthenticationException;
import com.akademia.projectplanner.service.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@AllArgsConstructor
@Controller
public class RegistrationController {

  private RegistrationService registrationService;

  @GetMapping("/registration")
  public String getRegistrationPage(Model model) {
    UserDto userDto = new UserDto();
    model.addAttribute("registrationDto", userDto);
    return "registration";
  }

  @PostMapping("/registered")
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
