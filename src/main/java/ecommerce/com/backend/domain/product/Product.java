package ecommerce.com.backend.domain.product;

import ecommerce.com.backend.domain.cart.CartItem;
import ecommerce.com.backend.domain.category.Category;
import ecommerce.com.backend.domain.inventory.Inventory;
import ecommerce.com.backend.domain.orderItem.OrderItem;
import ecommerce.com.backend.domain.pictures.ProductPictures;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
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

    @OneToOne
    @JoinColumn(name = "main_picture_id")
    private ProductPictures mainPicture;

    @OneToOne
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
    private Set<ProductPictures> productPictures;

    @OneToMany(mappedBy = "product")
    private Set<OrderItem> orderItems;

    @ManyToMany
    @JoinTable(name = "categories_by_products",
            joinColumns = @JoinColumn(name = "product_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "category_id", nullable = false))
    private Set<Category> categories;

    @OneToMany(mappedBy = "product")
    private Set<CartItem> cartItems;
}
