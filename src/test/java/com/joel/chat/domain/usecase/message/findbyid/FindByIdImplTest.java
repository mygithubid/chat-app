package com.joel.chat.domain.usecase.message.findbyid;

import com.joel.chat.domain.usecase.message.gateway.MessageGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FindByIdImplTest {

    MessageGateway gateway;
    FindById findById;

    @BeforeEach
    void setUp() {
        gateway = mock(MessageGateway.class);
        findById = new FindByIdImpl(gateway);
    }

    @Test
    void findNothing() {
        when(gateway.findById(1L)).thenReturn(Optional.empty());
        var message = findById.query(1L);
        assertTrue(message.isEmpty());
    }

    @Test
    void findOptional() {

        var now = LocalDateTime.now();
        var domain = new com.joel.chat.domain.model.Message(
                1L,
                "content",
                false,
                new com.joel.chat.domain.model.User(
                        1L,
                        "username",
                        null,
                        null,
                        null,
                        null
                ),
                new com.joel.chat.domain.model.User(
                        2L,
                        "username",
                        null,
                        null,
                        null,
                        null
                ),
                now
        );
        when(gateway.findById(1L)).thenReturn(Optional.of(domain));

        var message = findById.query(1L);
        assertTrue(message.isPresent());
        assertEquals(domain.id(), message.get().id());
        assertEquals(domain.content(), message.get().content());
        assertEquals(domain.read(), message.get().read());
        assertEquals(domain.sender().id(), message.get().sender().id());
        assertEquals(domain.sender().username(), message.get().sender().username());
        assertEquals(domain.recipient().id(), message.get().recipient().id());
        assertEquals(domain.recipient().username(), message.get().recipient().username());
        assertEquals(domain.createdAt(), message.get().createdAt());
    }

}