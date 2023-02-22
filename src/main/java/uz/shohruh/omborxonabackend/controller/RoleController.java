package uz.shohruh.omborxonabackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.shohruh.omborxonabackend.entity.Role;
import uz.shohruh.omborxonabackend.payload.ApiResponse;
import uz.shohruh.omborxonabackend.payload.RoleDTO;
import uz.shohruh.omborxonabackend.service.RoleService;


import javax.validation.Valid;
import java.util.List;

/**
 * admin tomonidan qo'shiladigan role lar uchun bu controller
 */
@RestController
@RequestMapping("/api/role")
public class RoleController {
    @Autowired
    RoleService roleService;


    @PreAuthorize(value = "hasAuthority('ADD_ROLE')")
    @PostMapping  //success larga 200 qaytariladi umumiy holda
    public HttpEntity<?> addRole(@Valid @RequestBody RoleDTO roleDTO){
        ApiResponse apiResponse = roleService.addRole(roleDTO);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @PreAuthorize(value = "hasAuthority('EDIT_ROLE')")
    @PutMapping("/{id}")
    public HttpEntity<?> editRole(@PathVariable Long id, @Valid @RequestBody RoleDTO roleDTO){
        ApiResponse apiResponse = roleService.editRole(roleDTO, id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @PreAuthorize(value = "hasAuthority('DELETE_ROLE')")
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteRole(@PathVariable Long id){
        ApiResponse apiResponse = roleService.deleteById(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @PreAuthorize(value = "hasAuthority('VIEW_ROLE')")
    @GetMapping("/{id}")
    public HttpEntity<Role> getById(@PathVariable Long id){
        Role role = roleService.getById(id);
        return ResponseEntity.ok(role);
    }

    @PreAuthorize(value = "hasAuthority('VIEW_ROLE')")
    @GetMapping
    public HttpEntity<List<Role>> getAll(){
        List<Role> roleList = roleService.getAll();
        return ResponseEntity.ok(roleList);
    }

}
