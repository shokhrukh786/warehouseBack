package uz.shohruh.omborxonabackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.shohruh.omborxonabackend.entity.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    boolean existsByName(String name);
}
