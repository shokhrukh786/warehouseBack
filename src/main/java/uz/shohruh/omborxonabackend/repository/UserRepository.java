package uz.shohruh.omborxonabackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.shohruh.omborxonabackend.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByUsername(String username);

    Optional<User> findByUsername(String username);
}
