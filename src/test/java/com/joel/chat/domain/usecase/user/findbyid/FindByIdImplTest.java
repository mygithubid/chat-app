package com.joel.chat.domain.usecase.user.findbyid;

import com.joel.chat.domain.model.User;
import com.joel.chat.domain.usecase.user.gateway.UserGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FindByIdImplTest {

    UserGateway userGateway;
    FindById findById;

    @BeforeEach
    void setUp() {
        userGateway = mock(UserGateway.class);
        findById = new FindByIdImpl(userGateway);
    }

    @Test
    void findNoUser() {
        when(userGateway.findById(1L)).thenReturn(Optional.empty());
        var user = findById.query(1L);
        assertTrue(user.isEmpty());
    }

    @Test
    void findUser() {

        var domain = new User(
                1L,
                "username",
                "password",
                "firstName",
                "lastName",
                null
        );
        when(userGateway.findById(1L)).thenReturn(Optional.of(domain));

        var user = findById.query(1L);
        assertTrue(user.isPresent());
        assertEquals(domain.id(), user.get().id());
        assertEquals(domain.username(), user.get().username());
        assertEquals(domain.password(), user.get().password());
        assertEquals(domain.firstName(), user.get().firstName());
        assertEquals(domain.lastName(), user.get().lastName());
        assertEquals(domain.createdAt(), user.get().createdAt());
    }

}