package com.joel.chat.domain.usecase.message.findbysender;

import com.joel.chat.domain.usecase.message.model.Message;

import java.util.List;

public interface FindBySender {

    List<Message> query(Long senderId);
}
