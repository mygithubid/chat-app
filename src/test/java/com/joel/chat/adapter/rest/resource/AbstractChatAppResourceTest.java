package com.joel.chat.adapter.rest.resource;

import com.joel.chat.app.Starter;
import com.joel.chat.domain.usecase.message.findbyreceiver.FindByReceiver;
import com.joel.chat.domain.usecase.message.findbysender.FindBySender;
import com.joel.chat.domain.usecase.user.create.Create;
import com.joel.chat.domain.usecase.user.findall.FindAll;
import com.joel.chat.domain.usecase.user.findbyid.FindById;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(classes = Starter.class)
@AutoConfigureMockMvc
public abstract class AbstractChatAppResourceTest {

    @Autowired
    protected MockMvc mockMvc;

    @MockBean
    protected FindById findUserById;

    @MockBean
    protected FindAll findAllUsers;

    @MockBean
    protected Create createUser;

    @MockBean
    protected com.joel.chat.domain.usecase.message.findbyid.FindById findMessageById;

    @MockBean
    protected FindByReceiver findByReceiver;

    @MockBean
    protected FindBySender findBySender;

    @MockBean
    protected com.joel.chat.domain.usecase.message.create.Create createMessage;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }


}
