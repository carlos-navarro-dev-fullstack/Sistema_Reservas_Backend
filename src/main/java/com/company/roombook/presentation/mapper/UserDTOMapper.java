package com.company.roombook.presentation.mapper;

import com.company.roombook.domain.model.User;
import com.company.roombook.presentation.dto.request.UserRequest;
import com.company.roombook.presentation.dto.response.UserResponse;

public class UserDTOMapper {

    // Request DTO -> Domain
    public static User toDomain(UserRequest dto) {
        return new User(
                null,
                dto.getName(),
                dto.getEmail(),
                dto.getPassword()
        );
    }

    // Domain -> Response DTO
    public static UserResponse toResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }
}