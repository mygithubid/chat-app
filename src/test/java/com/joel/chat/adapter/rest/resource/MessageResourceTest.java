package com.joel.chat.adapter.rest.resource;

import com.joel.chat.domain.usecase.message.model.Message;
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
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class MessageResourceTest extends AbstractChatAppResourceTest {

    @Test
    public void createMessageSuccess() throws Exception {
        var messageJson = """
                {
                    "content": "string",
                    "isRead": false,
                    "sender": {
                        "id": 1,
                        "username": "string"
                    },
                    "recipient": {
                        "id": 2,
                        "username": "string"
                    },
                    "createdAt": "2024-01-30T23:52:31.128361"
                }
                """;
        when(createMessage.execute(any())).thenReturn(
                new com.joel.chat.domain.usecase.message.model.Message(
                        1L,
                        "string",
                        false,
                        new Message.User(
                                1L,
                                "string"
                        ),
                        new Message.User(
                                2L,
                                "string"
                                ),
                        LocalDateTime.now()
                )
        );
        var mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/message")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(messageJson))
                .andReturn();
        var status = mvcResult.getResponse().getStatus();
        assertEquals(HttpStatus.OK.value(), status);
    }

    @Test
    void findByIdSuccess() throws Exception {
        when(findMessageById.query(any())).thenReturn(
                Optional.of(new com.joel.chat.domain.usecase.message.model.Message(
                                1L,
                                "string",
                                false,
                                new Message.User(
                                        1L,
                                        "string"
                                ),
                                new Message.User(
                                        2L,
                                        "string"
                                ),
                                LocalDateTime.now()
                        )
                )
        );
        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/message/1")
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
    void findByReceiverSuccess() throws Exception {
        when(findByReceiver.query(anyLong())).thenReturn(
                List.of(new com.joel.chat.domain.usecase.message.model.Message(
                                1L,
                                "string",
                                false,
                                new Message.User(
                                        1L,
                                        "string"
                                ),
                                new Message.User(
                                        2L,
                                        "string"
                                ),
                                LocalDateTime.now()
                        )
                )
        );
        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/message/receive/list/1")
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
    void findBySenderSuccess() throws Exception {
        when(findBySender.query(anyLong())).thenReturn(
                List.of(new com.joel.chat.domain.usecase.message.model.Message(
                                1L,
                                "string",
                                false,
                                new Message.User(
                                        1L,
                                        "string"
                                ),
                                new Message.User(
                                        2L,
                                        "string"
                                ),
                                LocalDateTime.now()
                        )
                )
        );
        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/message/send/list/1")
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