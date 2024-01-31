package com.joel.chat.domain.usecase.message.create;

import com.joel.chat.domain.usecase.message.model.Message;

public interface Create {

    Message execute(Message message);
}
