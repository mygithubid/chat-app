package com.joel.chat.domain.usecase.message.findbysender;

import com.joel.chat.domain.usecase.message.gateway.MessageGateway;
import com.joel.chat.domain.usecase.message.model.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FindBySenderImpl implements FindBySender {

    private final MessageGateway messageGateway;

    @Override
    public List<Message> query(Long senderId) {
        var messages = messageGateway.findSenderMessages(senderId);
        return messages.stream()
                .map(this::toMessage)
                .toList();
    }

    private Message toMessage(com.joel.chat.domain.model.Message message) {
        return new Message(
                message.id(),
                message.content(),
                message.read(),
                new Message.User(
                        message.sender().id(),
                        message.sender().username()
                ),
                new Message.User(
                        message.recipient().id(),
                        message.recipient().username()
                ),
                message.createdAt()
        );
    }
}
