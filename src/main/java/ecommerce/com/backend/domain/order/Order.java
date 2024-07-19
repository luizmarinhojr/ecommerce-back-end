package ecommerce.com.backend.domain.order;

import ecommerce.com.backend.domain.address.Address;
import ecommerce.com.backend.domain.customer.Customer;
import ecommerce.com.backend.domain.orderItem.OrderItem;
import ecommerce.com.backend.domain.payment.Payment;
import ecommerce.com.backend.domain.product.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "orders")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;

    private BigDecimal total;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "modified_at")
    @UpdateTimestamp
    private LocalDateTime modifiedAt;

    private String annotation;

    @UuidGenerator
    @Column(name = "external_id")
    private UUID externalId;

    @OneToOne(mappedBy = "order")
    private Payment payment;

    @OneToMany(mappedBy = "order")
    private Set<OrderItem> orderItems;
}
