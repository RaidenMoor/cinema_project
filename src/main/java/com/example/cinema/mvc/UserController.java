package com.example.cinema.mvc;

import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.example.cinema.dto.UserDTO;
import com.example.cinema.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

  @Value("${spring.security.user.name}")
  private String adminUserName;
  private UserService userService;



  @GetMapping("")
  public String getAll(
      @RequestParam(value = "page", defaultValue = "1") int page,
      @RequestParam(value = "size", defaultValue = "5") int pageSize,
      Model model
  ) {
    PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Direction.ASC, "login"));
    model.addAttribute("users", userService.getAll(pageRequest));
    return "users/viewAllUsers";
  }

  @GetMapping("/profile/{id}")
  public String viewProfile(@PathVariable Long id, Model model) {
    model.addAttribute("user", userService.getById(id));
    return "users/viewProfile";
  }

  // Этот метод больше не нужен, так как форма редактирования находится в модальном окне

    @GetMapping("/profile/update/{id}")
    public String updateProfile(@PathVariable Long id, Model model) {
        model.addAttribute("user", userService.getById(id));
        return "users/updateProfile";
    }


  @PostMapping("/profile/update")
  public String updateProfile(@ModelAttribute("userForm") UserDTO userDTO) {
    userService.update(userDTO);
    return "redirect:/users/profile/" + userDTO.getId();
  }

  @GetMapping("/registration")
  public String registration(@ModelAttribute("userForm") UserDTO userDTO) {
    return "users/registration";
  }

  @PostMapping("/registration")
  public String registration(@ModelAttribute("userForm") UserDTO userDTO, BindingResult bindingResult) {
    if(adminUserName.equals(userDTO.getLogin()) || userService.getByLogin(userDTO.getLogin()) != null) {
      bindingResult.rejectValue("login", "error.login", "Такой логин уже существует");
      return "users/registration";
    }
    if(userService.getByEmail(userDTO.getEmail()) != null) {
      bindingResult.rejectValue("email", "error.email", "Такая почта уже существует");
      return "users/registration";
    }
    userService.create(userDTO);
    return "redirect:/login";
  }



  @Autowired
  public void setUserService(UserService userService) {
    this.userService = userService;
  }
}
