package uz.shohruh.omborxonabackend.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {

    @NotNull(message = "nom bo'sh bo'lmasligi kerak")
    private String name;

    private String phoneNumber;
}
