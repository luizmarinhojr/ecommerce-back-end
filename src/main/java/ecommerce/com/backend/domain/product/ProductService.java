package ecommerce.com.backend.domain.product;

import ecommerce.com.backend.domain.product.validation.register.RegisterValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private List<RegisterValidation> validators;

    public Page<ProductDtoOut> list(Pageable pageable) {
        Page<Product> products = productRepository.findAll(pageable);
        return products.map(ProductDtoOut::new);
    }

    public Product post(ProductDtoIn productIn) {
        validators.forEach(v -> v.valid(productIn));
        var productSaved = productRepository.save(new Product(productIn));
        productSaved.setMainPicture(productIn.mainPicture());
        productSaved.setProductPictures(productIn.productPictures());
        return productSaved;
    }
}
