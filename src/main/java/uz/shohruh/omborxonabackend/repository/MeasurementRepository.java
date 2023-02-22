package uz.shohruh.omborxonabackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.shohruh.omborxonabackend.entity.Measurement;

public interface MeasurementRepository extends JpaRepository<Measurement, Long> {
    boolean existsByName(String name);
}
