package com.joel.chat.adapter.rest.resource;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


class UserResourceTest extends AbstractChatAppResourceTest {

    @Test
    public void createUserSuccess() throws Exception {
        var userJson = """
                {
                  "username": "string",
                  "password": "string",
                  "firstName": "string",
                  "lastName": "string",
                  "createdAt": "2024-01-30T23:52:31.128361"
                }
                """;
        when(createUser.execute(any())).thenReturn(
                new com.joel.chat.domain.usecase.user.model.User(
                        1L,
                        "string",
                        "string",
                        "string",
                        "string",
                        LocalDateTime.now()
                )
        );
        var mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/user")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(userJson))
                .andReturn();
        var status = mvcResult.getResponse().getStatus();
        assertEquals(HttpStatus.OK.value(), status);
    }

    @Test
    void findByIdSuccess() throws Exception {
        when(findUserById.query(any())).thenReturn(
                Optional.of(new com.joel.chat.domain.usecase.user.model.User(
                        1L,
                        "string",
                        "string",
                        "string",
                        "string",
                        LocalDateTime.now()
                        )
                )
        );
        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/user/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn();

        int status = mvcResult
                .getResponse()
                .getStatus();
        assertEquals(HttpStatus.OK.value(), status);
        String content = mvcResult
                .getResponse()
                .getContentAsString();
        assertNotNull(content);
    }

    @Test
    void findByAllSuccess() throws Exception {
        when(findAllUsers.query()).thenReturn(
                List.of(new com.joel.chat.domain.usecase.user.model.User(
                                1L,
                                "string",
                                "string",
                                "string",
                                "string",
                                LocalDateTime.now()
                        )
                )
        );
        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/user/list")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn();

        int status = mvcResult
                .getResponse()
                .getStatus();
        assertEquals(HttpStatus.OK.value(), status);
        String content = mvcResult
                .getResponse()
                .getContentAsString();
        assertNotNull(content);
    }
}