package ecommerce.com.backend.domain.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByName(String name);

    Optional<Product> findByDescription(String description);

    @Query(value = "SELECT p FROM Product p WHERE p.mainPicture.url = :url")
    Optional<Product> findByMainPictureUrl(@Param("url") String url);
}
