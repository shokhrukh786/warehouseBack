package uz.shohruh.omborxonabackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.shohruh.omborxonabackend.entity.Supplier;
import uz.shohruh.omborxonabackend.payload.ApiResponse;
import uz.shohruh.omborxonabackend.payload.SupplierDTO;
import uz.shohruh.omborxonabackend.repository.SupplierRepository;


import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {
    @Autowired
    SupplierRepository supplierRepository;

    public ApiResponse addSupplier(SupplierDTO supplierDTO){
        if (supplierRepository.existsByName(supplierDTO.getName()))
            return new ApiResponse("Bunday nomli taminotchi mavjud", false);
        Supplier supplier = new Supplier(
                supplierDTO.getName(),
                supplierDTO.getPhoneNumber(),
                true);
        supplierRepository.save(supplier);
        return new ApiResponse("Muavvaqiyatli saqlandi", true);
    }

    public ApiResponse editSupplier(SupplierDTO supplierDTO, Long id){
        if (!supplierRepository.existsById(id))
            return new ApiResponse("Bunday taminotchi mavjud emas", false);
        Optional<Supplier> optional = supplierRepository.findById(id);
        if (!optional.isPresent())
            return new ApiResponse("Bunday taminotchi mavjud emas", false);
        Supplier editSupplier = optional.get();
        editSupplier.setName(supplierDTO.getName());
        supplierRepository.save(editSupplier);
        return new ApiResponse("Muavvaqiyatli taxrirlandi", true);
    }

    public ApiResponse deleteSupplier(Long id){
        if (!supplierRepository.existsById(id))
            return new ApiResponse("Mavjud emas", false);
        supplierRepository.deleteById(id);
        return new ApiResponse("Muvaqqiyatli o'chirildi", true);
    }

    public ApiResponse getByIdSupplier(Long id){
        Optional<Supplier> optional = supplierRepository.findById(id);
        return optional.map(supplie ->
                        new ApiResponse("taminotchilar", true, supplie))
                .orElseGet(() ->
                        new ApiResponse("Bunday taminotchi mavjud emas", false));
    }

    public ApiResponse getAllSupplier(){
        List<Supplier> supplierList = supplierRepository.findAll();
        if (!supplierList.isEmpty())
            return new ApiResponse("Barcha taminotchilar", true, supplierList);
        return new ApiResponse("taminotchi mavjud emas", false);
    }
}
