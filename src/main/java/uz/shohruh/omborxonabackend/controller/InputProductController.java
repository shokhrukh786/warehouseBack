package uz.shohruh.omborxonabackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.shohruh.omborxonabackend.payload.ApiResponse;
import uz.shohruh.omborxonabackend.payload.InputProductDTO;
import uz.shohruh.omborxonabackend.service.InputProductService;


@RestController
@RequestMapping("/api/inputProduct")
public class InputProductController {
    @Autowired
    InputProductService inputProductService;

    @PostMapping
    public HttpEntity<?> addInputProduct(@RequestBody InputProductDTO inputProductDTO){
        ApiResponse apiResponse = inputProductService.addInputProduct(inputProductDTO);
        return ResponseEntity.status(apiResponse.isSuccess()?201:409).body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editInputProduct(@RequestBody InputProductDTO inputProductDTO,
                                          @PathVariable Long id){
        ApiResponse apiResponse = inputProductService.editInputProduct(inputProductDTO, id);
        return ResponseEntity.status(apiResponse.isSuccess()?201:400).body(apiResponse);
    }

    @GetMapping({"/{id}"})
    public HttpEntity<?> getByIdInputProduct(@PathVariable Long id){
        ApiResponse apiResponse = inputProductService.getByIdInputProduct(id);
        return ResponseEntity.status(apiResponse.isSuccess()?201:409).body(apiResponse);
    }

    @GetMapping
    public HttpEntity<?> getAllInputProduct(){
        ApiResponse apiResponse = inputProductService.getAllInputProduct();
        return ResponseEntity.status(apiResponse.isSuccess()?201:409).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteInputProduct(@PathVariable Long id){
        ApiResponse apiResponse = inputProductService.deleteInputProduct(id);
        return ResponseEntity.status(apiResponse.isSuccess()?201:409).body(apiResponse);
    }
















}
