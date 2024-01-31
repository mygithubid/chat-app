package com.joel.chat.domain.usecase.message.findbyreceiver;

import com.joel.chat.domain.usecase.message.model.Message;

import java.util.List;

public interface FindByReceiver {

    List<Message> query(Long receiverId);
}
