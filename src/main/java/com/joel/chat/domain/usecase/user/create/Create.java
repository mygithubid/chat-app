package com.joel.chat.domain.usecase.user.create;

import com.joel.chat.domain.usecase.user.model.User;

public interface Create {

    User execute(User user);
}
