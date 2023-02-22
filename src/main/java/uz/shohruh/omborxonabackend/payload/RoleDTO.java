package uz.shohruh.omborxonabackend.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.shohruh.omborxonabackend.entity.enums.Permission;
import uz.shohruh.omborxonabackend.entity.template.AbstractEntity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleDTO extends AbstractEntity {

    @NotNull(message = "name bo'sh bo'lmasin")
    @NotBlank  //belgilarni ham hisobga oladi, probelni ham otkazib yubormasligi uchun
    private String name; //ADMIN, USER VA BOSHQALAR

    private String description;

    @NotNull(message = "permissionlar bo'sh bo'lmasin")
    @NotEmpty
    private List<Permission> permissionList;




}
