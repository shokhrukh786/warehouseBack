package uz.shohruh.omborxonabackend.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.shohruh.omborxonabackend.entity.Role;
import uz.shohruh.omborxonabackend.entity.User;
import uz.shohruh.omborxonabackend.entity.enums.Permission;
import uz.shohruh.omborxonabackend.repository.RoleRepository;
import uz.shohruh.omborxonabackend.repository.UserRepository;
import uz.shohruh.omborxonabackend.utils.AppConstants;


import java.util.Arrays;

/**
 * tizim birinchi marta ishga tushganda role larni yaratadigan class
 */
@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Value("${spring.datasource.initialization-mode}") //shunday nomli keyni valuesini olib beradi
    private String initialMode;

    @Override
    public void run(String... args) throws Exception {

        if (initialMode.equals("always")){

            //barcha huquqlarni olib olamiz, array tipda
            Permission[] permissions = Permission.values();

            /**
             * admin va user role larini yarataman
             * Role lar yarataman permissionlarini berib va bazaga saqlayman
             */
            Role admin = roleRepository.save(new Role(
                    AppConstants.ADMIN,
                    Arrays.asList(permissions),
                    "hamma ishni qiloladi"
            ));
//            Role user = roleRepository.save(new Role(
//                    AppConstants.USER,
//                    Arrays.asList(Permission.ADD_COMMENT, Permission.EDIT_COMMENT, Permission.DELETE_MY_COMMENT),
//                    "berilgan permissionlaridan foydalana oladi"
//            ));

            /**
             * Admin va User userlarini yarataman.
             * role va permission yaratib endi user yarataman.
             */
            userRepository.save(new User(
                    "Admin",
                    "admin",
                    passwordEncoder.encode("admin123"),
                    admin,
                    true
            ));
//            userRepository.save(new User(
//                    "User",
//                    "user",
//                    passwordEncoder.encode("user123"),
//                    user,
//                    true
//            ));
        }
    }
}
