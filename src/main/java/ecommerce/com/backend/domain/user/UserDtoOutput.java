package ecommerce.com.backend.domain.user;

public record UserDtoOutput(
        String email
) {

    public UserDtoOutput (User user) {
        this(user.getEmail());
    }
}
