package com.joel.chat.domain.usecase.message.model;

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
