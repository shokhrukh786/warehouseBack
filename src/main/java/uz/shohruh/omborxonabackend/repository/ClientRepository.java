package uz.shohruh.omborxonabackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.shohruh.omborxonabackend.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
    boolean existsByName(String name);
}
