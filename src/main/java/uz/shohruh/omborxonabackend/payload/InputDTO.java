package uz.shohruh.omborxonabackend.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InputDTO {

    private Timestamp data;

    private Long warehouseId;

    private Long supplierId;

    private Long currencyId;

    private String factureNumber;
}
