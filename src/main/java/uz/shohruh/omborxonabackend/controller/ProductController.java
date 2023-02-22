package uz.shohruh.omborxonabackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.shohruh.omborxonabackend.payload.ApiResponse;
import uz.shohruh.omborxonabackend.payload.ProductDto;
import uz.shohruh.omborxonabackend.service.ProductService;


@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    ProductService productService;

//    @PreAuthorize(value = "hasAuthority('ADD_PRODUCT')")
    @PostMapping
    public HttpEntity<?> addProduct(@RequestBody ProductDto productDto) {
        ApiResponse apiResponse = productService.addProduct(productDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }

//    @PreAuthorize(value = "hasAuthority('EDIT_PRODUCT')")
    @PutMapping("/{id}")
    public HttpEntity<?> editProduct(@RequestBody ProductDto productDto, @PathVariable Long id){
        ApiResponse apiResponse = productService.editProduct(productDto, id);
        return ResponseEntity.status(apiResponse.isSuccess()?201:409).body(apiResponse);
    }

//    @PreAuthorize(value = "hasAuthority('VIEW_PRODUCT')")
    @GetMapping("/{id}")
    public HttpEntity<?> getByIdProduct(@PathVariable Long id){
        ApiResponse apiResponse = productService.getByIdProduct(id);
        return ResponseEntity.status(apiResponse.isSuccess()?201:409).body(apiResponse);
    }

//    @PreAuthorize(value = "hasAuthority('VIEW_PRODUCT')")
    @GetMapping
    public HttpEntity<?> getAllProduct(){
        ApiResponse apiResponse = productService.getAllProduct();
        return ResponseEntity.status(apiResponse.isSuccess()?201:409).body(apiResponse);
    }

//    @PreAuthorize(value = "hasAuthority('DELETE_PRODUCT')")
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteProduct(@PathVariable Long id){
        ApiResponse apiResponse = productService.deleteProduct(id);
        return ResponseEntity.status(apiResponse.isSuccess()?201:409).body(apiResponse);
    }
}
