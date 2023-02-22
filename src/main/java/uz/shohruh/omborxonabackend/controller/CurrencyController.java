package uz.shohruh.omborxonabackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.shohruh.omborxonabackend.payload.ApiResponse;
import uz.shohruh.omborxonabackend.payload.CurrencyDTO;
import uz.shohruh.omborxonabackend.service.CurrencyService;


import javax.validation.Valid;

@RestController
@RequestMapping("/api/currency")
public class CurrencyController {
    @Autowired
    CurrencyService currencyService;


    @PreAuthorize(value = "hasAuthority('ADD_CURRENCY')")
    @PostMapping
    public HttpEntity<?> addCurrency(@Valid @RequestBody CurrencyDTO currencyDTO){
        ApiResponse apiResponse = currencyService.addCurrency(currencyDTO);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @PreAuthorize(value = "hasAuthority('EDIT_CURRENCY')")
    @PutMapping("/{id}")
    public HttpEntity<?> editCurrency(@Valid @RequestBody CurrencyDTO currencyDTO,
                                         @PathVariable Long id){
        ApiResponse apiResponse = currencyService.editCurrency(currencyDTO, id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @PreAuthorize(value = "hasAuthority('DELETE_CURRENCY')")
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteCurrency(@PathVariable Long id){
        ApiResponse apiResponse = currencyService.deleteCurrency(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @PreAuthorize(value = "hasAuthority('VIEW_CURRENCY')")
    @GetMapping("/{id}")
    public HttpEntity<?> getByIdCurrency(@PathVariable Long id){
        ApiResponse apiResponse = currencyService.getByIdCurrency(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @PreAuthorize(value = "hasAuthority('VIEW_CURRENCY')")
    @GetMapping
    public HttpEntity<?> getAllCurrency(){
        ApiResponse apiResponse = currencyService.getAllCurrency();
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }
}
