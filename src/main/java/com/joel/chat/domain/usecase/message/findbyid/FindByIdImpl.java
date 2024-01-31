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
        return Optional.empty();
    }
}
