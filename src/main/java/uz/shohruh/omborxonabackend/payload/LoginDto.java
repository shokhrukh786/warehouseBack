package uz.shohruh.omborxonabackend.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class LoginDto {

    @NotNull(message = "username bo'sh bo'lmasligi kerak")
    private String username;  //email yoki phoneNumber bolishi mumkun
    @NotNull(message = "password bo'sh bo'lmasligi kerak")
    private String password;  //kalit so'z
}
