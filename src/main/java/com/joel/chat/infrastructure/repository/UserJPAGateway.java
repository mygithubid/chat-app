package com.joel.chat.infrastructure.repository;

import com.joel.chat.domain.model.User;
import com.joel.chat.domain.usecase.user.gateway.UserGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserJPAGateway implements UserGateway {

    private final UserJPARepository userJPARepository;

    @Override
    public Optional<User> findById(Long id) {
        var user = userJPARepository.findById(id);
        return user.map(this::toUser);
    }

    @Override
    public User save(User user) {
        var entity = com.joel.chat.infrastructure.entity.User.builder()
                .username(user.username())
                .firstName(user.firstName())
                .lastName(user.lastName())
                .password(user.password())
                .createdAt(user.createdAt())
                .build();
        var persisted = userJPARepository.save(entity);
        return toUser(persisted);
    }

    @Override
    public List<User> findAll() {
        var users = userJPARepository.findAll();
        return users.stream().map(this::toUser).toList();
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
