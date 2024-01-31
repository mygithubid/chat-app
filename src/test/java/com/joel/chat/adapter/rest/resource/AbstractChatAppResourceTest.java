package com.joel.chat.adapter.rest.resource;

import com.joel.chat.domain.usecase.user.create.Create;
import com.joel.chat.domain.usecase.user.findall.FindAll;
import com.joel.chat.domain.usecase.user.findbyid.FindById;
import lombok.Getter;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest
@Getter
public abstract class AbstractChatAppResourceTest {

    @Autowired
    protected MockMvc mockMvc;

    @MockBean
    protected FindById findUserById;

    @MockBean
    protected FindAll findAllUsers;

    @MockBean
    protected Create createUser;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }


}
