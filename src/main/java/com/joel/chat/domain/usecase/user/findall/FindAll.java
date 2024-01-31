package com.joel.chat.domain.usecase.user.findall;

import com.joel.chat.domain.usecase.user.model.User;

import java.util.List;

public interface FindAll {

    List<User> query();
}
