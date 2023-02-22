package uz.shohruh.omborxonabackend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.shohruh.omborxonabackend.entity.template.AbstractEntity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class OutputProduct extends AbstractEntity {

    @JoinColumn(nullable = false)
    @ManyToOne
    private Product product;

    @Column(nullable = false)
    private Double amount;

    private Double price;

    @JoinColumn(nullable = false)
    @ManyToOne
    private Output output;
}
