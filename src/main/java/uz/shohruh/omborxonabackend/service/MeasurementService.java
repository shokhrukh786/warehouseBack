package uz.shohruh.omborxonabackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.shohruh.omborxonabackend.entity.Measurement;
import uz.shohruh.omborxonabackend.payload.ApiResponse;
import uz.shohruh.omborxonabackend.payload.MeasurementDTO;
import uz.shohruh.omborxonabackend.repository.MeasurementRepository;


import java.util.List;
import java.util.Optional;
//@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
@Service
public class MeasurementService {
    @Autowired
    MeasurementRepository measurementRepository;

    public ApiResponse addMeasurement(MeasurementDTO measurementDTO){
        if (measurementRepository.existsByName(measurementDTO.getName()))
            return new ApiResponse("Bunday nomli mavjud", false);
        Measurement measurement = new Measurement(
                measurementDTO.getName(),
                true);
        measurementRepository.save(measurement);
        return new ApiResponse("Muavvaqiyatli saqlandi", true);
    }

    public ApiResponse editMeasurement(MeasurementDTO measurementDTO, Long id){
        if (!measurementRepository.existsById(id))
            return new ApiResponse("Bunday o'lchov birligi mavjud emas", false);
        Optional<Measurement> optional = measurementRepository.findById(id);
        if (!optional.isPresent())
            return new ApiResponse("Bunday o'lchov birligi mavjud emas", false);
        Measurement editMeasurement = optional.get();
        editMeasurement.setName(measurementDTO.getName());
        measurementRepository.save(editMeasurement);
        return new ApiResponse("Muavvaqiyatli taxrirlandi", true);
    }

    public ApiResponse deleteMeasurement(Long id){
        if (!measurementRepository.existsById(id))
            return new ApiResponse("Mavjud emas", false);
        measurementRepository.deleteById(id);
        return new ApiResponse("Muvaqqiyatli o'chirildi", true);
    }

    public ApiResponse getById(Long id){
        Optional<Measurement> optional = measurementRepository.findById(id);
        return optional.map(measurement ->
                new ApiResponse("O'lchov turlari", true, measurement))
                .orElseGet(() ->
                        new ApiResponse("Bunday o'lchov turi mavjud emas", false));
    }

    public ApiResponse getAll(){
        List<Measurement> measurementList = measurementRepository.findAll();
        if (!measurementList.isEmpty())
            return new ApiResponse("Barcha o'lchov birliklar", true, measurementList);
        return new ApiResponse("O'lchov birliklari mavjud emas", false);
    }

}
