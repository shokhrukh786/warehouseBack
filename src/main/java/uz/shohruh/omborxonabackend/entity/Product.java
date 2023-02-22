package uz.shohruh.omborxonabackend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.shohruh.omborxonabackend.entity.template.AbstractEntity;


import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Product extends AbstractEntity {

    @Column(nullable = false)
    private String name;

    @JoinColumn(nullable = false)
    @ManyToOne
    private Category category;

    @OneToOne
    private Attachment photo;

    @Column(nullable = false)
    private String code;

    @JoinColumn(nullable = false)
    @ManyToOne
    private Measurement measurement;

    @Column(nullable = false)
    private boolean active;
}
