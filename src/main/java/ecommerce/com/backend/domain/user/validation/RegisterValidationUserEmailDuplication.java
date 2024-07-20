package ecommerce.com.backend.domain.user.validation;

import ecommerce.com.backend.domain.user.UserDtoInput;
import ecommerce.com.backend.domain.user.UserRepository;
import ecommerce.com.backend.infra.exception.ValidationExceptionFields;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegisterValidationUserEmailDuplication implements RegisterValidationUser{

    @Autowired
    private UserRepository repository;

    @Override
    public void valid(UserDtoInput userInput) {
        if(repository.existsByEmail(userInput.email())) {
            throw new ValidationExceptionFields("There is already a user registered with that name");
        }
    }
}
