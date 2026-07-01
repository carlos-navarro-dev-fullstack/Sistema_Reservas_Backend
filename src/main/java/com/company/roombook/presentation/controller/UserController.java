package com.company.roombook.presentation.controller;

import com.company.roombook.application.usecase.UserUseCase;
import com.company.roombook.domain.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.company.roombook.presentation.dto.response.UserResponse;
import com.company.roombook.presentation.mapper.UserDTOMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    private final UserUseCase userUseCase;

    public UserController(UserUseCase userUseCase) {
        this.userUseCase = userUseCase;
    }

    @GetMapping("/{id}")
    public UserResponse getById(@PathVariable Long id) {

        log.info("Consultando usuario por id={}", id);

        User user = userUseCase.getById(id);

        if (user == null) {
            log.warn("Usuario no encontrado id={}", id);
        } else {
            log.info("Usuario encontrado id={}, username={}", id, user.getName());
        }

        return UserDTOMapper.toResponse(user);
    }

    @GetMapping
    public List<UserResponse> getAll() {

        log.info("Listando todos los usuarios");

        return userUseCase.getAll()
                .stream()
                .map(UserDTOMapper::toResponse)
                .toList();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {

        log.warn("Eliminando usuario id={}", id);

        userUseCase.delete(id);

        log.info("Usuario eliminado correctamente id={}", id);
    }
}