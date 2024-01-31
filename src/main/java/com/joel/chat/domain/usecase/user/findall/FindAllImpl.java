package com.joel.chat.domain.usecase.user.findall;

import com.joel.chat.domain.usecase.user.gateway.UserGateway;
import com.joel.chat.domain.usecase.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FindAllImpl implements FindAll {

    private final UserGateway userGateway;


    @Override
    public List<User> query() {
        var domainUsers = userGateway.findAll();
        return domainUsers.stream()
                    .map(this::toUser)
                    .toList();
    }

    private User toUser(final com.joel.chat.domain.model.User domainUser) {
        return new User(
                domainUser.id(),
                domainUser.username(),
                domainUser.password(),
                domainUser.firstName(),
                domainUser.lastName(),
                domainUser.createdAt()
        );

    }
}
