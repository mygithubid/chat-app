package com.joel.chat.adapter.rest.resource.model;

import com.joel.chat.domain.model.User;

import java.time.LocalDateTime;

public record Message(
        Long id,
        String content,
        Boolean read,
        User sender,
        User recipient,
        LocalDateTime createdAt
) {

    public record User(
            Long id,
            String username
    ) {
    }
}
