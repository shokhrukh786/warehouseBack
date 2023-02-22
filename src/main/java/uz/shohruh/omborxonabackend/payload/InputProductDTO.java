package uz.shohruh.omborxonabackend.payload;

import lombok.Data;

import java.util.Date;

@Data
public class InputProductDTO {
    private Long inputId;
    private Long productId;
    private Double amount;
    private Double price;
    private Date expireDate;
}
