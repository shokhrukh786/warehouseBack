package uz.shohruh.omborxonabackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.shohruh.omborxonabackend.entity.Warehouse;
import uz.shohruh.omborxonabackend.payload.ApiResponse;
import uz.shohruh.omborxonabackend.payload.WarehouseDTO;
import uz.shohruh.omborxonabackend.repository.WarehouseRepository;


import java.util.List;
import java.util.Optional;

@Service
public class WarehouseService {
    @Autowired
    WarehouseRepository warehouseRepository;

    public ApiResponse addWarehouse(WarehouseDTO warehouseDTO){
        if (warehouseRepository.existsByName(warehouseDTO.getName()))
            return new ApiResponse("Bunday nomli omborxona mavjud", false);
        Warehouse warehouse = new Warehouse(
                warehouseDTO.getName(),
                true);
        warehouseRepository.save(warehouse);
        return new ApiResponse("Muavvaqiyatli saqlandi", true);
    }

    public ApiResponse editWarehouse(WarehouseDTO warehouseDTO, Long id){
        if (!warehouseRepository.existsById(id))
            return new ApiResponse("Bunday omborxona mavjud emas", false);
        Optional<Warehouse> optional = warehouseRepository.findById(id);
        if (!optional.isPresent())
            return new ApiResponse("Bunday omborxona mavjud emas", false);
        Warehouse editWarehouse = optional.get();
        editWarehouse.setName(warehouseDTO.getName());
        warehouseRepository.save(editWarehouse);
        return new ApiResponse("Muavvaqiyatli taxrirlandi", true);
    }

    public ApiResponse deleteWarehouse(Long id){
        if (!warehouseRepository.existsById(id))
            return new ApiResponse("Mavjud emas", false);
        warehouseRepository.deleteById(id);
        return new ApiResponse("Muvaqqiyatli o'chirildi", true);
    }

    public ApiResponse getById(Long id){
        Optional<Warehouse> optional = warehouseRepository.findById(id);
        return optional.map(warehouse ->
                        new ApiResponse("Omborxona", true, warehouse))
                .orElseGet(() ->
                        new ApiResponse("Bunday omborxona mavjud emas", false));
    }

    public ApiResponse getAll(){
        List<Warehouse> warehousesList = warehouseRepository.findAll();
        if (!warehousesList.isEmpty())
            return new ApiResponse("Barcha omborxonalar", true, warehousesList);
        return new ApiResponse("Omborxonalar mavjud emas", false);
    }

}
