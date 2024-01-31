package com.joel.chat.domain.usecase.user.create;

import com.joel.chat.domain.usecase.user.gateway.UserGateway;
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
    UserGateway userGateway;
    CreateImpl createImpl;

    @BeforeEach
    void setUp() {
        mockedLocalDateTime = mockStatic(LocalDateTime.class);
        userGateway = mock(UserGateway.class);
        createImpl = new CreateImpl(userGateway);
    }

    @AfterEach
    void tearDown() {
        mockedLocalDateTime.close();
    }

    @Test
    void execute() {

        var now = LocalDateTime.now();

        mockedLocalDateTime.when(LocalDateTime::now).thenReturn(now);

        var domain = new com.joel.chat.domain.model.User(
                null,
                "username",
                "password",
                "firstName",
                "lastName",
                now
        );

        Mockito.when(userGateway.save(domain)).thenAnswer(invocationOnMock -> {
            var domainUser = invocationOnMock.getArgument(0, com.joel.chat.domain.model.User.class);
            return new com.joel.chat.domain.model.User(
                    1L,
                    "username",
                    "password",
                    "firstName",
                    "lastName",
                    now
            );
        });

        var user = new com.joel.chat.domain.usecase.user.model.User(
                1L,
                "username",
                "password",
                "firstName",
                "lastName",
                now
        );

        var savedUser = createImpl.execute(user);

        assertEquals(user.id(), savedUser.id());
        assertEquals(user.username(), savedUser.username());
        assertEquals(user.password(), savedUser.password());
        assertEquals(user.firstName(), savedUser.firstName());
        assertEquals(user.lastName(), savedUser.lastName());
        assertEquals(user.createdAt(), savedUser.createdAt());
    }

}