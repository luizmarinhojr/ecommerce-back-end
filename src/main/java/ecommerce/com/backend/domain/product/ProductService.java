package ecommerce.com.backend.domain.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Page<ProductDtoOut> list(Pageable pageable) {
        Page<Product> products = productRepository.findAll(pageable);
        return products.map(ProductDtoOut::new);
    }

    public Product post(ProductDtoIn productIn) {
        var productSaved = productRepository.save(new Product(productIn));
        productSaved.setMainPicture(productIn.mainPicture());
        productSaved.setProductPictures(productIn.productPictures());
        return productSaved;
    }
}
