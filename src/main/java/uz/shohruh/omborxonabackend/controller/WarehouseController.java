package uz.shohruh.omborxonabackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.shohruh.omborxonabackend.payload.ApiResponse;
import uz.shohruh.omborxonabackend.payload.WarehouseDTO;
import uz.shohruh.omborxonabackend.service.WarehouseService;


import javax.validation.Valid;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/warehouse")
public class WarehouseController {
    @Autowired
    WarehouseService warehouseService;


    /*@PreAuthorize(value = "hasAuthority('ADD_WAREHOUSE')")*/
    @PostMapping
    public HttpEntity<?> addWarehouse(@Valid @RequestBody WarehouseDTO warehouseDTO){
        ApiResponse apiResponse = warehouseService.addWarehouse(warehouseDTO);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    /*@PreAuthorize(value = "hasAuthority('EDIT_WAREHOUSE')")*/
    @PutMapping("/{id}")
    public HttpEntity<?> editWarehouse(@Valid @RequestBody WarehouseDTO warehouseDTO,
                                         @PathVariable Long id){
        ApiResponse apiResponse = warehouseService.editWarehouse(warehouseDTO, id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    /*@PreAuthorize(value = "hasAuthority('DELETE_WAREHOUSE')")*/
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteWarehouse(@PathVariable Long id){
        ApiResponse apiResponse = warehouseService.deleteWarehouse(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    /*@PreAuthorize(value = "hasAuthority('VIEW_WAREHOUSE')")*/
    @GetMapping("/{id}")
    public HttpEntity<?> getById(@PathVariable Long id){
        ApiResponse apiResponse = warehouseService.getById(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    /*@PreAuthorize(value = "hasAuthority('VIEW_WAREHOUSE')")*/
    @GetMapping
    public HttpEntity<?> getAll(){
        ApiResponse apiResponse = warehouseService.getAll();
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }
}
