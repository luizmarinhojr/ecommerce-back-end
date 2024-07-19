package ecommerce.com.backend.domain.customer;

import ecommerce.com.backend.domain.address.Address;
import ecommerce.com.backend.domain.order.Order;
import ecommerce.com.backend.domain.user.UserAuthentication;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    @JoinColumn(name = "user_id")
    private UserAuthentication user;

    private String name;

    @Column(name = "last_name")
    private String lastName;

    private String cellphone;

    @Column(name = "cellphone_2")
    private String cellphone2;

    @Column(name = "external_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID externalId;

    @OneToMany(mappedBy = "customer")
    private List<Address> addresses;

    @OneToMany(mappedBy = "customer")
    private List<Order> orders;
}