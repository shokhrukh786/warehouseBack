package uz.shohruh.omborxonabackend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.shohruh.omborxonabackend.entity.template.AbstractEntity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Output extends AbstractEntity {

    private Date date;

    @JoinColumn(nullable = false)
    @ManyToOne
    private Warehouse warehouse;

    @ManyToOne
    private Currency currency;

    @Column(nullable = false)
    private String factureNumber;

    private String code;

    @JoinColumn(nullable = false)
    @ManyToOne
    private Client client;
}
