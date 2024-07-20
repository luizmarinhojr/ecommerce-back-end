package ecommerce.com.backend.domain.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;

public record UserDtoInput(
        @Email
        @NotEmpty
        String email,

        @NotEmpty
        @Length(max = 35)
        String password
) {
}
