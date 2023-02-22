package uz.shohruh.omborxonabackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.shohruh.omborxonabackend.payload.ApiResponse;
import uz.shohruh.omborxonabackend.payload.ClientDTO;
import uz.shohruh.omborxonabackend.service.ClientService;


import javax.validation.Valid;

@RestController
@RequestMapping("/api/client")
public class ClientController {
    @Autowired
    ClientService clientService;


    @PreAuthorize(value = "hasAuthority('ADD_CLIENT')")
    @PostMapping
    public HttpEntity<?> addClient(@Valid @RequestBody ClientDTO clientDTO){
        ApiResponse apiResponse = clientService.addClient(clientDTO);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @PreAuthorize(value = "hasAuthority('EDIT_CLIENT')")
    @PutMapping("/{id}")
    public HttpEntity<?> editClient(@Valid @RequestBody ClientDTO clientDTO,
                                         @PathVariable Long id){
        ApiResponse apiResponse = clientService.editClient(clientDTO, id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @PreAuthorize(value = "hasAuthority('DELETE_CLIENT')")
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteClient(@PathVariable Long id){
        ApiResponse apiResponse = clientService.deleteClient(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @PreAuthorize(value = "hasAuthority('VIEW_CLIENT')")
    @GetMapping("/{id}")
    public HttpEntity<?> getById(@PathVariable Long id){
        ApiResponse apiResponse = clientService.getById(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @PreAuthorize(value = "hasAuthority('VIEW_CLIENT')")
    @GetMapping
    public HttpEntity<?> getAll(){
        ApiResponse apiResponse = clientService.getAll();
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }
}
