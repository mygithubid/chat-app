package com.joel.chat.adapter.rest.resource;

import com.joel.chat.adapter.rest.resource.model.User;
import com.joel.chat.domain.usecase.user.create.Create;
import com.joel.chat.domain.usecase.user.findall.FindAll;
import com.joel.chat.domain.usecase.user.findbyid.FindById;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserResource {

    private final FindById findById;
    private final FindAll findAll;
    private final Create create;


    @GetMapping("/{id}")
    public User getUser(@PathVariable("id") Long id) {
        var user = findById.query(id);
        if (user.isEmpty()) {
            emptyUser();
        }

        return mapToUser(user.orElseThrow());
    }

    @PostMapping
    public User createUser(@RequestBody User request) {
        var userRequest = new com.joel.chat.domain.usecase.user.model.User(
                null,
                request.username(),
                request.password(),
                request.firstName(),
                request.lastName(),
                null
        );

        var user = create.execute(userRequest);
        return mapToUser(user);
    }

    @GetMapping("/list")
    public List<User> getUsers() {
        var users = findAll.query();
        if (users.isEmpty()) {
            return List.of(emptyUser());
        }

        return users.stream().map(this::mapToUser).toList();
    }

    private User mapToUser(com.joel.chat.domain.usecase.user.model.User user) {
        return new User(
                user.id(),
                user.username(),
                user.password(),
                user.firstName(),
                user.lastName(),
                user.createdAt()
        );
    }

    private User emptyUser() {
        return new User(
                0L,
                "",
                "",
                "",
                "",
                null
        );
    }
}
