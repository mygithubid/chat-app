package com.joel.chat.domain.usecase.message.findbyid;

import com.joel.chat.domain.usecase.message.gateway.MessageGateway;
import com.joel.chat.domain.usecase.message.model.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("com.joel.chat.domain.usecase.message.findbyid.FindByIdImpl")
@RequiredArgsConstructor
public class FindByIdImpl implements FindById {

    private final MessageGateway messageGateway;

    @Override
    public Optional<Message> query(Long id) {
        return messageGateway.findById(id).map(this::toMessage);
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
