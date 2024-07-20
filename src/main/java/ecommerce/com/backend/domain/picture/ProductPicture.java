package ecommerce.com.backend.domain.picture;

import ecommerce.com.backend.domain.product.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product_pictures")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductPicture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private String url;

    @OneToOne(mappedBy = "mainPicture")
    private Product mainProductPicture;

    public ProductPicture (Product product, String url) {
        this.product = product;
        this.url = url;
    }
}
