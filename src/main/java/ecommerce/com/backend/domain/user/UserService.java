package ecommerce.com.backend.domain.user;

import ecommerce.com.backend.domain.user.validation.RegisterValidationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private List<RegisterValidationUser> validators;

    public UserDtoOutput register(UserDtoInput userInput) {
        validators.forEach(v -> v.valid(userInput));
        var passwordEncrypted = encoder.encode(userInput.password());
        var user = repository.save(new User(userInput, passwordEncrypted));
        return new UserDtoOutput(user);
    }
}
