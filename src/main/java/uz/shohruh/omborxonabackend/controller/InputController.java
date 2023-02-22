package uz.shohruh.omborxonabackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.shohruh.omborxonabackend.payload.ApiResponse;
import uz.shohruh.omborxonabackend.payload.InputDTO;
import uz.shohruh.omborxonabackend.service.InputService;


import javax.validation.Valid;

@RestController
@RequestMapping("/api/input")
public class InputController {

    @Autowired
    InputService inputService;

    @PostMapping
    public HttpEntity<?> addInput(@Valid @RequestBody InputDTO inputDTO){
        ApiResponse apiResponse = inputService.addInput(inputDTO);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editInput(@Valid @PathVariable Long id,
                                   @RequestBody InputDTO inputDTO){
        ApiResponse apiResponse = inputService.editInput(id, inputDTO);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getByIdInput(@PathVariable Long id){
        ApiResponse apiResponse = inputService.getById(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @GetMapping
    public HttpEntity<?> getAllInput(){
        ApiResponse apiResponse = inputService.getAll();
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deletedInput(@PathVariable Long id){
        ApiResponse apiResponse = inputService.deletedInput(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

}
