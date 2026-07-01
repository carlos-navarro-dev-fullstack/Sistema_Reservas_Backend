package com.company.roombook.domain.repository;

import com.company.roombook.domain.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    User save(User user);

    Optional<User> findById(Long id);

    List<User> findAll();

    void deleteById(Long id);

    Optional<User> findByEmail(String email);
}