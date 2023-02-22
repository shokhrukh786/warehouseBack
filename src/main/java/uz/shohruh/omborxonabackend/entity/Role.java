package uz.shohruh.omborxonabackend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.shohruh.omborxonabackend.entity.enums.Permission;
import uz.shohruh.omborxonabackend.entity.template.AbstractEntity;


import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Role extends AbstractEntity {

    /**
     * unique = true takrorlanmasligini oldini oladi
     * masalan 2ta admin admin bolib qolmasligi kerak.
     */
    @Column(unique = true, nullable = false)
    private String name;

    /**
     * @ManyToMany qo'ymadik chunki Permission table emas enumred toifasida
     */
    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.LAZY)
    private List<Permission> permissionList;

    @Column(length = 500)  //text uzunligi 500dan oshmasligi kerak.
    private String description;
}
