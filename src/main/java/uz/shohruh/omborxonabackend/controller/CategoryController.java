package uz.shohruh.omborxonabackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.shohruh.omborxonabackend.payload.ApiResponse;
import uz.shohruh.omborxonabackend.payload.CategoryDTO;
import uz.shohruh.omborxonabackend.service.CategoryService;

import javax.validation.Valid;

@RestController

@RequestMapping("/api/category")
@CrossOrigin("http://localhost:3000")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @PostMapping
    public HttpEntity<?> addCategory(@Valid @RequestBody CategoryDTO categoryDTO){
        ApiResponse apiResponse = categoryService.addCategory(categoryDTO);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editCategory(@Valid @PathVariable Long id,
                                      @RequestBody CategoryDTO categoryDTO){
        ApiResponse apiResponse = categoryService.editCategory(id, categoryDTO);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteCategory(@PathVariable Long id){
        ApiResponse apiResponse = categoryService.deleteCategory(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getByIdCategory(@PathVariable Long id){
        ApiResponse apiResponse = categoryService.getByIdCategory(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @GetMapping
    public HttpEntity<?> getAllCategory(){
        ApiResponse apiResponse = categoryService.getAllCategory();
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }
}
