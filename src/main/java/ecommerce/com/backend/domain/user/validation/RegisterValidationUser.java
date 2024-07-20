package ecommerce.com.backend.domain.user.validation;

import ecommerce.com.backend.domain.user.UserDtoInput;

public interface RegisterValidationUser {

    void valid(UserDtoInput userInput);
}
