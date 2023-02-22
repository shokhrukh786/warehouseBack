package uz.shohruh.omborxonabackend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import uz.shohruh.omborxonabackend.entity.enums.Permission;
import uz.shohruh.omborxonabackend.entity.template.AbstractEntity;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "users")
public class User extends AbstractEntity implements UserDetails {

    @Column(nullable = false) //ismsiz user bo'lmasin
    private String fullName;

    @Column(nullable = false)
    private String username;  //email yoki phoneNumber bolishi mumkun

    @Column(nullable = false)
    private String password;  //kalit so'z

    @ManyToMany
    private Set<Warehouse> warehouse;

    /**
     * fetch = FetchType.LAZY bu qachon chaqirsakgina bazadan olib keladi.
     * bazida userni ozi kerak boladi role ishlatmasligimiz mumkun shu uchun
     * optional = false  - user role siz(lavozimsiz) bo'lmasin degani
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Role role;

    private boolean enabled;  //faqat enabled bilan ishlamoqchiman shu uchun qolganlari true qilingan. shu uchun 5 talik constructor ochib qoyaaman.
    private boolean accountNonExpired=true;
    private boolean accountNonLocked=true;
    private boolean credentialsNonExpired=true;

    /**
     * bu method tizimga kerib turgan userni permission larini(huquhlarini) Collection nini qaytaradi
     * @return
     */
    @Override //bu method foydalanuvchini huquqlarini qaytaradi
    public Collection<? extends GrantedAuthority> getAuthorities() {

        /**
         * 1-usul
         */
//        List<Permission> permissionList = this.role.getPermissionList();
//        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
//        for (Permission permission : permissionList){
//            grantedAuthorities.add(new GrantedAuthority() {
//                @Override
//                public String getAuthority() {
//                    return permission.name();
//                }
//            });
//        }
//        return grantedAuthorities;

        /**
         * 2-usul
         */
        List<Permission> permissionList = this.role.getPermissionList();
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (Permission permission:permissionList){
            grantedAuthorities.add(new SimpleGrantedAuthority(permission.name()));
        }
        return grantedAuthorities;


    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    public User(String fullName, String username, String password, Role role, boolean enabled) {
        this.fullName = fullName;
        this.username = username;
        this.password = password;
        this.role = role;
        this.enabled = enabled;
    }
}



//    @Column(nullable = false)
//    private String firstName;
//
//    @Column(nullable = false)
//    private String lastName;
//
//    @Column(nullable = false,unique = true)
//    private String phoneNumber;
//
//    @Column(nullable = false,unique = true)
//    private String code;
//
//    @Column(nullable = false)
//    private String password;
//
//    private boolean active = true;
//
//    @ManyToMany
//    private List<Warehouse> warehouses;

