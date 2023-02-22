package uz.shohruh.omborxonabackend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.shohruh.omborxonabackend.entity.template.AbstractEntity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Category extends AbstractEntity {

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToOne
    private Category parentCategory;

    @Column(nullable = false)
    private boolean active;

    public Category(String name, Long categoryId, boolean active) {
    }
}
