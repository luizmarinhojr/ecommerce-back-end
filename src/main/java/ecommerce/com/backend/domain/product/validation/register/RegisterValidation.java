package ecommerce.com.backend.domain.product.validation.register;

import ecommerce.com.backend.domain.product.ProductDtoIn;

public interface RegisterValidation {

    void valid(ProductDtoIn productIn);
}
