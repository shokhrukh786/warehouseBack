package uz.shohruh.omborxonabackend.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WarehouseDTO {

    @NotNull(message = "nom bo'sh bo'lmasligi kerak")
    private String name;
}
