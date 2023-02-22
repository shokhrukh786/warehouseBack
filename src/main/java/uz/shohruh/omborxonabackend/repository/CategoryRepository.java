package uz.shohruh.omborxonabackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import uz.shohruh.omborxonabackend.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    boolean existsByName(String name);
    boolean existsByNameNot(String name);
}
