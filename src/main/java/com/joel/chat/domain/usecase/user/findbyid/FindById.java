package com.joel.chat.domain.usecase.user.findbyid;

import com.joel.chat.domain.usecase.user.model.User;

import java.util.Optional;

public interface FindById {

    Optional<User> query(Long id);
}
