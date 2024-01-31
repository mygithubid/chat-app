package com.joel.chat.domain.usecase.message.findbyid;

import com.joel.chat.domain.usecase.message.model.Message;

import java.util.Optional;

public interface FindById {

    Optional<Message> query(Long id);
}
