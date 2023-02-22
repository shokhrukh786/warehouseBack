package uz.shohruh.omborxonabackend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.shohruh.omborxonabackend.entity.template.AbstractEntity;


import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Input extends AbstractEntity {

    private Timestamp data;

    @JoinColumn(nullable = false)
    @ManyToOne
    private Warehouse warehouse;

    @JoinColumn(nullable = false)
    @ManyToOne
    private Supplier supplier;

    @JoinColumn(nullable = false)
    @ManyToOne
    private Currency currency;

    private String factureNumber;

    private String code;


    public Input(Timestamp data, Long warehouseId, Long supplierId, Long currencyId, String factureNumber, String codes) {
    }
}
