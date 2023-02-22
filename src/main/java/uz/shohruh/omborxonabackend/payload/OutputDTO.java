package uz.shohruh.omborxonabackend.payload;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class OutputDTO {

    private Timestamp data;

    private Long warehouseId;

    private Long currencyId;

    private String factureNumber;

    private Long clientId;
}
