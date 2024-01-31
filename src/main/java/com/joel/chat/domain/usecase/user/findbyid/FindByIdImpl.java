package com.joel.chat.domain.usecase.user.findbyid;

import com.joel.chat.domain.usecase.user.gateway.UserGateway;
import com.joel.chat.domain.usecase.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("com.joel.chat.domain.usecase.user.findbyid.FindByIdImpl")
@RequiredArgsConstructor
public class FindByIdImpl implements FindById {

    private final UserGateway userGateway;

    @Override
    public Optional<User> query(Long id) {
        var domain = userGateway.findById(id);
        if (domain.isEmpty()) {
            return Optional.empty();
        }

        var user = domain.get();
        return Optional.of(new User(
                user.id(),
                user.username(),
                user.password(),
                user.firstName(),
                user.lastName(),
                user.createdAt()
        ));
    }
}
