package com.joel.chat.domain.usecase.message.create;

import com.joel.chat.domain.model.User;
import com.joel.chat.domain.usecase.message.gateway.MessageGateway;
import com.joel.chat.domain.usecase.message.model.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Component("com.joel.chat.domain.usecase.message.create.CreateImpl")
@RequiredArgsConstructor
public class CreateImpl implements Create {

    private final MessageGateway messageGateway;

    @Override
    public Message execute(Message message) {
        var domainMessage = new com.joel.chat.domain.model.Message(
                null,
                message.content(),
                false,
                new User(
                        message.sender().id(),
                        message.sender().username(),
                        null,
                        null,
                        null,
                        null
                ),
                new User(
                        message.recipient().id(),
                        message.recipient().username(),
                        null,
                        null,
                        null,
                        null
                ),
                LocalDateTime.now()
        );
        var savedMessage = messageGateway.save(domainMessage);
        return new Message(
                savedMessage.id(),
                savedMessage.content(),
                savedMessage.read(),
                new Message.User(
                        savedMessage.sender().id(),
                        savedMessage.sender().username()
                ),
                new Message.User(
                        savedMessage.recipient().id(),
                        savedMessage.recipient().username()
                ),
                savedMessage.createdAt()
        );
    }
}
