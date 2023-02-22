package uz.shohruh.omborxonabackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.shohruh.omborxonabackend.entity.Warehouse;

public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {

    boolean existsByName(String name);
}
