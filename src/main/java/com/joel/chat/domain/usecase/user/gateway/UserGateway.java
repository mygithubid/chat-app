package com.joel.chat.domain.usecase.user.gateway;

import com.joel.chat.domain.model.User;

import java.util.List;
import java.util.Optional;

public interface UserGateway {

    Optional<User> findById(Long id);

    User save(User user);

    List<User> findAll();
}
