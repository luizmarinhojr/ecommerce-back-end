package ecommerce.com.backend.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

//    @Query("SELECT COUNT(u) > 0 FROM User u WHERE u.email = :email")
    Boolean existsByEmail(String email);
}
