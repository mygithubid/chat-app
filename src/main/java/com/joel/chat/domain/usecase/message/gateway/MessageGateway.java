package com.joel.chat.domain.usecase.message.gateway;

import com.joel.chat.domain.model.Message;

import java.util.List;
import java.util.Optional;

public interface MessageGateway {

    Optional<Message> findById(Long id);

    List<Message> findSenderMessages(Long senderId);

    List<Message> findReceiverMessages(Long receiverId);

    Message save(Message message);
}
