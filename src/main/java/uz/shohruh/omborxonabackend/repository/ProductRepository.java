package uz.shohruh.omborxonabackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.shohruh.omborxonabackend.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsByName(String name);
}
