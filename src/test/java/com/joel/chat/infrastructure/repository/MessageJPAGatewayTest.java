package com.joel.chat.infrastructure.repository;

import com.joel.chat.infrastructure.entity.Message;
import com.joel.chat.infrastructure.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MessageJPAGatewayTest {

    UserJPARepository userJPARepository;
    MessageJPARepository repository;

    MessageJPAGateway gateway;

    @BeforeEach
    void setUp() {
        userJPARepository = mock(UserJPARepository.class);
        repository = mock(MessageJPARepository.class);
        gateway = new MessageJPAGateway(userJPARepository, repository);
    }

    @Test
    void findById() {
        var now = LocalDateTime.now();

        var entity = Message.builder()
                .id(1L)
                .content("content")
                .read(false)
                .sender(User.builder()
                        .id(1L)
                        .username("username")
                        .password("password")
                        .firstName("firstName")
                        .lastName("lastName")
                        .build())
                .receiver(User.builder()
                        .id(2L)
                        .username("username")
                        .password("password")
                        .firstName("firstName")
                        .lastName("lastName")
                        .build())
                .sentAt(now)
                .build();
        when(repository.findById(1L)).thenReturn(Optional.of(entity));

        var message = gateway.findById(1L);

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

    @Test
    void findAllSender() {
        var now = LocalDateTime.now();

        var entity = Message.builder()
                .id(1L)
                .content("content")
                .read(false)
                .sender(User.builder()
                        .id(1L)
                        .username("username")
                        .password("password")
                        .firstName("firstName")
                        .lastName("lastName")
                        .build())
                .receiver(User.builder()
                        .id(2L)
                        .username("username")
                        .password("password")
                        .firstName("firstName")
                        .lastName("lastName")
                        .build())
                .sentAt(now)
                .build();
        when(repository.findBySenderId(1L)).thenReturn(List.of(entity));

        var messages = gateway.findSenderMessages(1L);

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

    @Test
    void findAllReceiver() {
        var now = LocalDateTime.now();

        var entity = Message.builder()
                .id(1L)
                .content("content")
                .read(false)
                .sender(User.builder()
                        .id(1L)
                        .username("username")
                        .password("password")
                        .firstName("firstName")
                        .lastName("lastName")
                        .build())
                .receiver(User.builder()
                        .id(2L)
                        .username("username")
                        .password("password")
                        .firstName("firstName")
                        .lastName("lastName")
                        .build())
                .sentAt(now)
                .build();
        when(repository.findByReceiverId(1L)).thenReturn(List.of(entity));

        var messages = gateway.findReceiverMessages(1L);

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

    @Test
    void create() {
        var now = LocalDateTime.now();

        when(userJPARepository.findById(1L)).thenReturn(Optional.of(User.builder()
                .id(1L)
                .username("username")
                .password("password")
                .firstName("firstName")
                .lastName("lastName")
                .build()));
        when(userJPARepository.findById(2L)).thenReturn(Optional.of(User.builder()
                .id(2L)
                .username("username")
                .password("password")
                .firstName("firstName")
                .lastName("lastName")
                .build()));

        var entity = Message.builder()
                .id(1L)
                .content("content")
                .read(false)
                .sender(User.builder()
                        .id(1L)
                        .username("username")
                        .password("password")
                        .firstName("firstName")
                        .lastName("lastName")
                        .build())
                .receiver(User.builder()
                        .id(2L)
                        .username("username")
                        .password("password")
                        .firstName("firstName")
                        .lastName("lastName")
                        .build())
                .sentAt(now)
                .build();
        when(repository.save(any(Message.class))).thenReturn(entity);


        var saved = gateway.save(
                new com.joel.chat.domain.model.Message(
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
                )
        );

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

        assertEquals(domain.id(), saved.id());
        assertEquals(domain.content(), saved.content());
        assertEquals(domain.read(), saved.read());
        assertEquals(domain.sender().id(), saved.sender().id());
        assertEquals(domain.sender().username(), saved.sender().username());
        assertEquals(domain.recipient().id(), saved.recipient().id());
        assertEquals(domain.recipient().username(), saved.recipient().username());
        assertEquals(domain.createdAt(), saved.createdAt());

    }

}