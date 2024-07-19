package ecommerce.com.backend.domain.order;

import ecommerce.com.backend.domain.address.Address;
import ecommerce.com.backend.domain.customer.Customer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
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


    // PAREI AQUI! PRECISA REVER SE PRECISA DE TABELA DE DELIVERY
    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @Column(name = "datetime_creation")
    private LocalDateTime datetimeCreation;

    private String annotation;

    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "external_id")
    private UUID externalId;
}
