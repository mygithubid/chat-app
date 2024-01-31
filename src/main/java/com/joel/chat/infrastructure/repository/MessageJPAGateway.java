package com.joel.chat.infrastructure.repository;

import com.joel.chat.domain.model.Message;
import com.joel.chat.domain.model.User;
import com.joel.chat.domain.usecase.message.gateway.MessageGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MessageJPAGateway implements MessageGateway {

    private final UserJPARepository userJPARepository;
    private final MessageJPARepository messageJPARepository;

    @Override
    public Optional<Message> findById(Long id) {
        return messageJPARepository.findById(id).map(this::toMessage);
    }

    @Override
    public List<Message> findSenderMessages(Long senderId) {
        var messages = messageJPARepository.findBySenderId(senderId);
        return messages.stream()
                .map(this::toMessage)
                .toList();
    }

    @Override
    public List<Message> findReceiverMessages(Long receiverId) {
        var messages = messageJPARepository.findByReceiverId(receiverId);
        return messages.stream()
                .map(this::toMessage)
                .toList();
    }

    @Override
    public Message save(Message message) {
        return toMessage(messageJPARepository.save(toEntity(message)));
    }

    private com.joel.chat.infrastructure.entity.Message toEntity(final Message message) {
        var sender = userJPARepository.findById(message.sender().id()).orElseThrow();
        var receiver = userJPARepository.findById(message.recipient().id()).orElseThrow();
        return com.joel.chat.infrastructure.entity.Message.builder()
                .content(message.content())
                .read(message.read())
                .sender(sender)
                .receiver(receiver)
                .sentAt(message.createdAt())
                .build();
    }

    private Message toMessage(com.joel.chat.infrastructure.entity.Message message) {
        return new Message(
                message.getId(),
                message.getContent(),
                message.getRead(),
                toUser(message.getSender()),
                toUser(message.getReceiver()),
                message.getSentAt()
        );
    }

    private User toUser(final com.joel.chat.infrastructure.entity.User entity) {
        return new User(
                entity.getId(),
                entity.getUsername(),
                entity.getPassword(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getCreatedAt()
        );
    }


}
