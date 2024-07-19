package ecommerce.com.backend.domain.pictures;

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
public class ProductPictures {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private String url;

    @OneToOne(mappedBy = "mainPicture")
    private Product mainProductPicture;
}
