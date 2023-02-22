package uz.shohruh.omborxonabackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.shohruh.omborxonabackend.entity.Currency;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {
    boolean existsByName(String name);
}
