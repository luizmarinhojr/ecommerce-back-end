package ecommerce.com.backend.domain.product;

import ecommerce.com.backend.domain.cart.CartItem;
import ecommerce.com.backend.domain.category.Category;
import ecommerce.com.backend.domain.inventory.Inventory;
import ecommerce.com.backend.domain.orderItem.OrderItem;
import ecommerce.com.backend.domain.picture.ProductPicture;
import ecommerce.com.backend.domain.picture.ProductPictureDtoIn;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "products")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "main_picture_id")
    private ProductPicture mainPicture;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "inventory_id", nullable = false)
    private Inventory inventory;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "modified_at")
    @UpdateTimestamp
    private LocalDateTime modifiedAt;

    @Column(name = "external_id")
    @UuidGenerator
    private UUID externalId;

    @OneToMany(mappedBy = "product")
    private Set<ProductPicture> productPictures;

    @OneToMany(mappedBy = "product")
    private Set<OrderItem> orderItems;

    @ManyToMany
    @JoinTable(name = "categories_by_products",
            joinColumns = @JoinColumn(name = "product_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "category_id", nullable = false))
    private Set<Category> categories;

    @OneToMany(mappedBy = "product")
    private Set<CartItem> cartItems;


    public Product(ProductDtoIn productIn) {
        this.name = productIn.name();
        this.description = productIn.description();
        this.inventory = new Inventory(productIn.inventory());
    }

    public void setMainPicture(ProductPictureDtoIn mainPictureDto) {
        if (mainPictureDto != null) {
            this.mainPicture = new ProductPicture(Product.this, mainPictureDto.url());
        }
    }

    public void setProductPictures(Set<ProductPictureDtoIn> productPicturesDto) {
        if (productPicturesDto != null) {
            this.productPictures = new HashSet<>();
            for (ProductPictureDtoIn picture : productPicturesDto) {
                this.productPictures.add(new ProductPicture(Product.this, picture.url()));
            }
        }
    }
}
