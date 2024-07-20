package ecommerce.com.backend.infra.security;

import java.time.LocalDateTime;

public record TokenJwtDto(
        LocalDateTime timestamp,

        String token) {
}
