package com.example.cinema.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.cinema.dto.UserDTO;
import com.example.cinema.model.User;
import com.example.cinema.service.UserService;

@RestController
@RequestMapping("/users")
@Tag(name = "Пользователи", description = "Контроллер для работы с пользователями сервиса")
public class UserRestController extends GenericRestController<User, UserDTO> {

    public UserRestController(UserService userService) {
        service = userService;
    }
}
