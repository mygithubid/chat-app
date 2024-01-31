package com.joel.chat.domain.usecase.user.create;

import com.joel.chat.domain.usecase.user.gateway.UserGateway;
import com.joel.chat.domain.usecase.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component("com.joel.chat.domain.usecase.user.create.CreateImpl")
@RequiredArgsConstructor
public class CreateImpl implements Create {

    private final UserGateway userGateway;

    @Override
    public User execute(User user) {
        var domainUserRequest = new com.joel.chat.domain.model.User(
                null,
                user.username(),
                user.password(),
                user.firstName(),
                user.lastName(),
                LocalDateTime.now()
        );

        var savedDomain = userGateway.save(domainUserRequest);
        return new User(
                savedDomain.id(),
                savedDomain.username(),
                savedDomain.password(),
                savedDomain.firstName(),
                savedDomain.lastName(),
                savedDomain.createdAt()
        );
    }
}
