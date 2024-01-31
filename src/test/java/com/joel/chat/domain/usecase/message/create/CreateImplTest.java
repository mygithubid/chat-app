package com.joel.chat.domain.usecase.message.create;

import com.joel.chat.domain.usecase.message.gateway.MessageGateway;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;

class CreateImplTest {

    MockedStatic<LocalDateTime> mockedLocalDateTime;
    MessageGateway messageGateway;
    CreateImpl createImpl;


    @BeforeEach
    void setUp() {
        mockedLocalDateTime = mockStatic(LocalDateTime.class);
        messageGateway = mock(MessageGateway.class);
        createImpl = new CreateImpl(messageGateway);
    }

    @AfterEach
    void tearDown() {
        mockedLocalDateTime.close();
    }

    @Test
    void execute() {

        var now = LocalDateTime.now();

        mockedLocalDateTime.when(LocalDateTime::now).thenReturn(now);

        var domain = new com.joel.chat.domain.model.Message(
                null,
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

        Mockito.when(messageGateway.save(domain)).thenAnswer(invocationOnMock -> {
            var domainModel = invocationOnMock.getArgument(0, com.joel.chat.domain.model.Message.class);
            return new com.joel.chat.domain.model.Message(
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
        });

        var message = new com.joel.chat.domain.usecase.message.model.Message(
                null,
                "content",
                false,
                new com.joel.chat.domain.usecase.message.model.Message.User(
                        1L,
                        "username"
                ),
                new com.joel.chat.domain.usecase.message.model.Message.User(
                        2L,
                        "username"
                ),
                now
        );

        var saved = createImpl.execute(message);

        assertEquals(1L, saved.id());
        assertEquals(domain.content(), saved.content());
        assertEquals(domain.read(), saved.read());
        assertEquals(domain.sender().id(), saved.sender().id());
        assertEquals(domain.sender().username(), saved.sender().username());
        assertEquals(domain.recipient().id(), saved.recipient().id());
        assertEquals(domain.recipient().username(), saved.recipient().username());
        assertEquals(domain.createdAt(), saved.createdAt());
    }
}