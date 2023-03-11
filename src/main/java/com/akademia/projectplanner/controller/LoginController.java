package com.akademia.projectplanner.controller;

import com.akademia.projectplanner.dto.UserDto;
import com.akademia.projectplanner.exception.AuthorizationException;
import com.akademia.projectplanner.exception.UserDoesNotExistException;
import com.akademia.projectplanner.service.LoginService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@AllArgsConstructor
@Controller
public class LoginController {

  private LoginService loginService;

  @GetMapping("/login")
  public String getLoginPage(Model model) {
    UserDto userDto = new UserDto();
    model.addAttribute("loginDto", userDto);
    return "login";
  }

  @PostMapping("/login")
  public String login(@ModelAttribute("loginDto") UserDto userDto, Model model) {

    try {
      loginService.login(userDto);

    } catch (IllegalArgumentException i) {
      model.addAttribute("fieldsNotFilledMessage", i.getMessage());
      return "login";
    } catch (UserDoesNotExistException e) {
      model.addAttribute("userDoesNotExistMessage", e.getMessage());
      return "login";
    } catch (AuthorizationException a) {
      model.addAttribute("passwordIncorrectMessage", a.getMessage());
      return "login";
    }

    return "index";
  }
}
