package com.joel.chat.domain.usecase.user.findall;

import com.joel.chat.domain.usecase.user.gateway.UserGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FindAllImplTest {

    UserGateway userGateway;
    FindAllImpl findAll;

    @BeforeEach
    void setUp() {
        userGateway = mock(UserGateway.class);
        findAll = new FindAllImpl(userGateway);
    }

    @Test
    void findNoUsers() {
        when(userGateway.findAll()).thenReturn(List.of());
        var users = findAll.query();
        assertTrue(users.isEmpty());
    }

    @Test
    void findUsers() {
        var user = new com.joel.chat.domain.model.User(
                1L,
                "username",
                "password",
                "firstName",
                "lastName",
                LocalDateTime.now()
        );
        when(userGateway.findAll()).thenReturn(List.of(user));

        var users = findAll.query();
        assertFalse(users.isEmpty());
        assertEquals(user.id(), users.get(0).id());
        assertEquals(user.username(), users.get(0).username());
        assertEquals(user.password(), users.get(0).password());
        assertEquals(user.firstName(), users.get(0).firstName());
        assertEquals(user.lastName(), users.get(0).lastName());
        assertEquals(user.createdAt(), users.get(0).createdAt());
    }

}