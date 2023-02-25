package uz.shohruh.omborxonabackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.shohruh.omborxonabackend.payload.ApiResponse;
import uz.shohruh.omborxonabackend.payload.MeasurementDTO;
import uz.shohruh.omborxonabackend.service.MeasurementService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/measurement")
@CrossOrigin("http://localhost:3000")
public class MeasurementController {
    @Autowired
    MeasurementService measurementService;


//    @PreAuthorize(value = "hasAuthority('ADD_MEASUREMENT')")
    @PostMapping
    public HttpEntity<?> addMeasurement(@Valid @RequestBody MeasurementDTO measurementDTO){
        ApiResponse apiResponse = measurementService.addMeasurement(measurementDTO);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

//    @PreAuthorize(value = "hasAuthority('EDIT_MEASUREMENT')")
    @PutMapping ("/{id}")
    public HttpEntity<?> editMeasurement(@Valid @RequestBody MeasurementDTO measurementDTO,
                                         @PathVariable Long id){
        ApiResponse apiResponse = measurementService.editMeasurement(measurementDTO, id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

//    @PreAuthorize(value = "hasAuthority('DELETE_MEASUREMENT')")
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteMeasurement(@PathVariable Long id){
        ApiResponse apiResponse = measurementService.deleteMeasurement(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

//    @PreAuthorize(value = "hasAuthority('VIEW_MEASUREMENT')")
    @GetMapping("/{id}")
    public HttpEntity<?> getById(@PathVariable Long id){
        ApiResponse apiResponse = measurementService.getById(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

//    @PreAuthorize(value = "hasAuthority('VIEW_MEASUREMENT')")
    @GetMapping
    public HttpEntity<?> getAll(){
        ApiResponse apiResponse = measurementService.getAll();
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }
}
