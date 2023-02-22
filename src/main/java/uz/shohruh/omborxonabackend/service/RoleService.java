package uz.shohruh.omborxonabackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.shohruh.omborxonabackend.entity.Role;
import uz.shohruh.omborxonabackend.payload.ApiResponse;
import uz.shohruh.omborxonabackend.payload.RoleDTO;
import uz.shohruh.omborxonabackend.repository.RoleRepository;


import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;

    public ApiResponse addRole(RoleDTO roleDTO){
        if (roleRepository.existsByName(roleDTO.getName()))
            return new ApiResponse("bunday nomli role allaqachom mavjud", false);
        Role role = new Role(
                roleDTO.getName(),
                roleDTO.getPermissionList(),
                roleDTO.getDescription()
        );
        roleRepository.save(role);
        return new ApiResponse("muvaqqiyatli saqlandi", true);
    }

    public ApiResponse editRole(RoleDTO roleDTO, Long id){
        if (!roleRepository.existsByName(roleDTO.getName()))
            return new ApiResponse("role mavjud emas", false);
        Optional<Role> optionalRole = roleRepository.findById(id);
        if (optionalRole.isPresent()){
            Role editRole = optionalRole.get();
            editRole.setName(roleDTO.getName());
            editRole.setDescription(roleDTO.getDescription());
            editRole.setPermissionList(roleDTO.getPermissionList());
            roleRepository.save(editRole);
            return new ApiResponse("muvaqqiyatli tahrirlandi", true);
        }
        return new ApiResponse("role mavjud emas", true);
    }

    public ApiResponse deleteById(Long id){
        try {
            roleRepository.deleteById(id);
            return new ApiResponse("Muvaqqiyatli o'chirildi", true);
        }catch (Exception e){
            return new ApiResponse("O'chirilmadi", false);
        }
    }

    public Role getById(Long id){
        Optional<Role> optionalRole = roleRepository.findById(id);
        Role role = optionalRole.get();
        return role;
    }

    public List<Role> getAll(){
        List<Role> roleList = roleRepository.findAll();
        return roleList;
    }
}
