package com.joel.chat.adapter.rest.resource;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;


class UserResourceTest extends AbstractChatAppResourceTest {

    @Disabled
    @Test
    public void testGetEndpoint() throws Exception {
        var userJson = """
                {
                  "id": 1,
                  "username": "string",
                  "password": "string",
                  "firstName": "string",
                  "lastName": "string",
                  "createdAt": "2024-01-30T23:52:31.128361"
                }
                """;

        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/user")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(userJson))
                .andReturn();
        var status = mvcResult.getResponse().getStatus();
        assertEquals(HttpStatus.OK.value(), status);
    }

    @Disabled
    @Test
    void findById_success() throws Exception {
        var userJson = """
                {
                  "id": 1,
                  "username": "string",
                  "password": "string",
                  "firstName": "string",
                  "lastName": "string",
                  "createdAt": "2024-01-30T23:52:31.128361"
                }
                """;
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


}