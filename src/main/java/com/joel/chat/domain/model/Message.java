package com.joel.chat.domain.model;

import java.time.LocalDateTime;

public record Message(
        Long id,
        String content,
        Boolean read,
        User sender,
        User recipient,
        LocalDateTime createdAt
) {
}
