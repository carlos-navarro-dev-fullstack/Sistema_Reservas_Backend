package com.company.roombook.application.usecase;

import com.company.roombook.domain.model.User;

import java.util.List;

public interface UserUseCase {

    User create(User user);

    User getById(Long id);

    List<User> getAll();

    void delete(Long id);
}