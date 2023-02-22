package uz.shohruh.omborxonabackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.shohruh.omborxonabackend.payload.ApiResponse;
import uz.shohruh.omborxonabackend.payload.OutputDTO;
import uz.shohruh.omborxonabackend.service.OutputService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/")
public class OutputController {
    @Autowired
    OutputService outputService;

    @PostMapping
    public HttpEntity<?> addOutput(@Valid @RequestBody OutputDTO outputDTO){
        ApiResponse apiResponse = outputService.addOutput(outputDTO);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editOutput(@Valid @PathVariable Long id,
                                   @RequestBody OutputDTO outputDTO){
        ApiResponse apiResponse = outputService.editOutput(id, outputDTO);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getByIdOutput(@PathVariable Long id){
        ApiResponse apiResponse = outputService.getByIdOutput(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @GetMapping
    public HttpEntity<?> getAllOutput(){
        ApiResponse apiResponse = outputService.getAllOutput();
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deletedOutput(@PathVariable Long id){
        ApiResponse apiResponse = outputService.deletedOutput(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

}
