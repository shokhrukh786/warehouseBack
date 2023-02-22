package uz.shohruh.omborxonabackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.shohruh.omborxonabackend.payload.ApiResponse;
import uz.shohruh.omborxonabackend.payload.SupplierDTO;
import uz.shohruh.omborxonabackend.service.SupplierService;


import javax.validation.Valid;

@RestController
@RequestMapping("/api/supplier")
public class SupplierController {
    @Autowired
    SupplierService supplierService;


    @PreAuthorize(value = "hasAuthority('ADD_SUPPLIER')")
    @PostMapping
    public HttpEntity<?> addSupplier(@Valid @RequestBody SupplierDTO supplierDTO){
        ApiResponse apiResponse = supplierService.addSupplier(supplierDTO);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @PreAuthorize(value = "hasAuthority('EDIT_SUPPLIER')")
    @PutMapping("/{id}")
    public HttpEntity<?> editSupplier(@Valid @RequestBody SupplierDTO supplierDTO,
                                         @PathVariable Long id){
        ApiResponse apiResponse = supplierService.editSupplier(supplierDTO, id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @PreAuthorize(value = "hasAuthority('DELETE_SUPPLIER')")
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteSupplier(@PathVariable Long id){
        ApiResponse apiResponse = supplierService.deleteSupplier(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @PreAuthorize(value = "hasAuthority('VIEW_SUPPLIER')")
    @GetMapping("/{id}")
    public HttpEntity<?> getById(@PathVariable Long id){
        ApiResponse apiResponse = supplierService.getByIdSupplier(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @PreAuthorize(value = "hasAuthority('VIEW_SUPPLIER')")
    @GetMapping
    public HttpEntity<?> getAll(){
        ApiResponse apiResponse = supplierService.getAllSupplier();
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

}
