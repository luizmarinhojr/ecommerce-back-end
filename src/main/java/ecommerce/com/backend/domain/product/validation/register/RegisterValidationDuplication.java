package ecommerce.com.backend.domain.product.validation.register;

import ecommerce.com.backend.domain.product.ProductDtoIn;
import ecommerce.com.backend.domain.product.ProductRepository;
import ecommerce.com.backend.infra.exception.ValidationExceptionFields;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegisterValidationDuplication implements RegisterValidation{

    @Autowired
    private ProductRepository repository;

    @Override
    public void valid(ProductDtoIn productIn) {
        var productName = repository.findByName(productIn.name());
        var productDescription = repository.findByDescription(productIn.description());
        var productMainPicture = repository.findByMainPictureUrl(productIn.mainPicture().url());

        if (productName.isPresent()) {
            throw new ValidationExceptionFields("There is already a product registered with that name");
        }
        if (productDescription.isPresent()) {
            throw new ValidationExceptionFields("There is already a product registered with that description");
        }
        if (productMainPicture.isPresent()) {
            throw new ValidationExceptionFields("There is already a product registered with that main picture url");
        }
    }
}
