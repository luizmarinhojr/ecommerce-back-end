package ecommerce.com.backend.domain.customer;

import ecommerce.com.backend.domain.address.Address;
import ecommerce.com.backend.domain.order.Order;
import ecommerce.com.backend.domain.shoppingSession.ShoppingSession;
import ecommerce.com.backend.domain.user.UserAuthentication;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "customers")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserAuthentication user;

    private String name;

    @Column(name = "last_name")
    private String lastName;

    private String cellphone;

    @Column(name = "cellphone_2")
    private String cellphone2;

    @Column(name = "external_id")
    @UuidGenerator
    private UUID externalId;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "modified_at")
    @UpdateTimestamp
    private LocalDateTime modifiedAt;

    @OneToMany(mappedBy = "customer")
    private List<Address> addresses;

    @OneToMany(mappedBy = "customer")
    private List<Order> orders;

    @OneToOne(mappedBy = "customer")
    private ShoppingSession shopperSession;
}
