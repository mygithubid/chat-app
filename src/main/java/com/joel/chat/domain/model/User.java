package com.joel.chat.domain.model;

import java.time.LocalDateTime;

public record User(
        Long id,
        String username,
        String password,
        String firstName,
        String lastName,
        LocalDateTime createdAt
) {
}
