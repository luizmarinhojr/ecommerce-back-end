package ecommerce.com.backend.controller;

import ecommerce.com.backend.domain.product.ProductDtoIn;
import ecommerce.com.backend.domain.product.ProductDtoOut;
import ecommerce.com.backend.domain.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping
    public ResponseEntity<Page<ProductDtoOut>> list(Pageable pageable) {
        var product = service.list(pageable);
        return ResponseEntity.ok(product);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ProductDtoOut> post(@RequestBody ProductDtoIn productIn, UriComponentsBuilder uriBuilder) {
        var product = service.post(productIn);
        var uri = uriBuilder.path("/products/{id}").buildAndExpand(product.getId()).toUri();
        return ResponseEntity.created(uri).body(new ProductDtoOut(product));
    }
}
