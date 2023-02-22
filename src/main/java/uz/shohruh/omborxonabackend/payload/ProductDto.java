package uz.shohruh.omborxonabackend.payload;

import lombok.Data;

@Data
public class ProductDto {

    private String name;
    private  Long categoryId;
    private Long photoId;
    private Long measurementId;
    private String code;
}
