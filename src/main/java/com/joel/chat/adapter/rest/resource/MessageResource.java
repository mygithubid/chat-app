package com.joel.chat.adapter.rest.resource;

import com.joel.chat.adapter.rest.resource.model.Message;
import com.joel.chat.domain.usecase.message.create.Create;
import com.joel.chat.domain.usecase.message.findbyid.FindById;
import com.joel.chat.domain.usecase.message.findbyreceiver.FindByReceiver;
import com.joel.chat.domain.usecase.message.findbysender.FindBySender;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/message")
@RequiredArgsConstructor
public class MessageResource {

    private final FindById findMessageById;
    private final FindByReceiver findByReceiver;
    private final FindBySender findBySender;
    private final Create createMessage;

    @GetMapping("/{id}")
    public Message getMessage(@PathVariable("id") Long id) {
        var message = findMessageById.query(id);
        if (message.isEmpty()) {
            return emptyMessage();
        }

        return toMessage(message.get());
    }

    @GetMapping("/receive/list/{receiverId}")
    public List<Message> getReceiverMessages(@PathVariable("receiverId") Long receiverId) {
        var messages = findByReceiver.query(receiverId);

        if (messages.isEmpty()) {
            return List.of(emptyMessage());
        }

        return messages.stream()
                .map(this::toMessage)
                .toList();
    }

    @GetMapping("/send/list/{senderId}")
    public List<Message> getSenderMessages(@PathVariable("senderId") Long senderId) {
        var messages = findBySender.query(senderId);

        if (messages.isEmpty()) {
            return List.of(emptyMessage());
        }

        return messages.stream()
                .map(this::toMessage)
                .toList();
    }

    @PostMapping
    public Message createMessage(@RequestBody Message request) {
        var message = new com.joel.chat.domain.usecase.message.model.Message(
                null,
                request.content(),
                false,
                new com.joel.chat.domain.usecase.message.model.Message.User(
                        request.sender().id(),
                        null
                ),
                new com.joel.chat.domain.usecase.message.model.Message.User(
                        request.recipient().id(),
                        null
                ),
                null
        );

        var createdMessage = createMessage.execute(message);
        return toMessage(createdMessage);
    }

    private Message emptyMessage() {
        return new Message(
                null,
                null,
                null,
                null,
                null,
                null
        );
    }

    private Message toMessage(com.joel.chat.domain.usecase.message.model.Message message) {
        return new Message(
                message.id(),
                message.content(),
                message.read(),
                new Message.User(
                        message.sender().id(),
                        message.sender().username()
                ),
                new Message.User(
                        message.recipient().id(),
                        message.recipient().username()
                ),
                message.createdAt()
        );
    }
}
