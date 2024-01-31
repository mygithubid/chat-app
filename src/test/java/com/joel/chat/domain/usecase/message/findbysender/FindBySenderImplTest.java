package com.joel.chat.domain.usecase.message.findbysender;

import com.joel.chat.domain.usecase.message.gateway.MessageGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FindBySenderImplTest {

    MessageGateway gateway;
    FindBySender findBySender;

    @BeforeEach
    void setUp() {
        gateway = mock(MessageGateway.class);
        findBySender = new FindBySenderImpl(gateway);
    }

    @Test
    void findNothing() {
        when(gateway.findById(1L)).thenReturn(Optional.empty());
        var messages = findBySender.query(1L);
        assertTrue(messages.isEmpty());
    }

    @Test
    void findList() {

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
        when(gateway.findSenderMessages(1L)).thenReturn(List.of(domain));

        var messages = findBySender.query(1L);
        assertFalse(messages.isEmpty());
        assertEquals(domain.id(), messages.get(0).id());
        assertEquals(domain.content(), messages.get(0).content());
        assertEquals(domain.read(), messages.get(0).read());
        assertEquals(domain.sender().id(), messages.get(0).sender().id());
        assertEquals(domain.sender().username(), messages.get(0).sender().username());
        assertEquals(domain.recipient().id(), messages.get(0).recipient().id());
        assertEquals(domain.recipient().username(), messages.get(0).recipient().username());
        assertEquals(domain.createdAt(), messages.get(0).createdAt());
    }

}