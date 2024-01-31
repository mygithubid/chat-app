package com.joel.chat.infrastructure.repository;

import com.joel.chat.infrastructure.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserJPAGatewayTest {

    UserJPARepository repository;

    UserJPAGateway gateway;

    @BeforeEach
    void setUp() {
        repository = mock(UserJPARepository.class);
        gateway = new UserJPAGateway(repository);
    }

    @Test
    void findById() {
        var entity = User.builder()
                .id(1L)
                .username("username")
                .password("password")
                .firstName("firstName")
                .lastName("lastName")
                .build();
        when(repository.findById(1L)).thenReturn(Optional.of(entity));

        var user = gateway.findById(1L);

        var domain = new com.joel.chat.domain.model.User(
                1L,
                "username",
                "password",
                "firstName",
                "lastName",
                null
        );
        assertTrue(user.isPresent());
        assertEquals(domain.id(), user.get().id());
        assertEquals(domain.username(), user.get().username());
        assertEquals(domain.password(), user.get().password());
        assertEquals(domain.firstName(), user.get().firstName());
        assertEquals(domain.lastName(), user.get().lastName());
        assertEquals(domain.createdAt(), user.get().createdAt());
    }

    @Test
    void findAll() {
        var entity = User.builder()
                .id(1L)
                .username("username")
                .password("password")
                .firstName("firstName")
                .lastName("lastName")
                .build();
        when(repository.findAll()).thenReturn(List.of(entity));

        var user = gateway.findAll();

        var domain = new com.joel.chat.domain.model.User(
                1L,
                "username",
                "password",
                "firstName",
                "lastName",
                null
        );
        assertFalse(user.isEmpty());
        assertEquals(domain.id(), user.get(0).id());
        assertEquals(domain.username(), user.get(0).username());
        assertEquals(domain.password(), user.get(0).password());
        assertEquals(domain.firstName(), user.get(0).firstName());
        assertEquals(domain.lastName(), user.get(0).lastName());
        assertEquals(domain.createdAt(), user.get(0).createdAt());
    }

    @Test
    void create() {
        var entity = User.builder()
                .id(1L)
                .username("username")
                .password("password")
                .firstName("firstName")
                .lastName("lastName")
                .build();
        when(repository.save(any(User.class))).thenReturn(entity);

        var user = gateway.save(new com.joel.chat.domain.model.User(
                null,
                "username",
                "password",
                "firstName",
                "lastName",
                null
        ));

        var domain = new com.joel.chat.domain.model.User(
                1L,
                "username",
                "password",
                "firstName",
                "lastName",
                null
        );

        assertEquals(domain.id(), user.id());
        assertEquals(domain.username(), user.username());
        assertEquals(domain.password(), user.password());
        assertEquals(domain.firstName(), user.firstName());
        assertEquals(domain.lastName(), user.lastName());
        assertEquals(domain.createdAt(), user.createdAt());
    }

}