package uz.shohruh.omborxonabackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.shohruh.omborxonabackend.entity.Currency;
import uz.shohruh.omborxonabackend.entity.Input;
import uz.shohruh.omborxonabackend.entity.Supplier;
import uz.shohruh.omborxonabackend.entity.Warehouse;
import uz.shohruh.omborxonabackend.payload.ApiResponse;
import uz.shohruh.omborxonabackend.payload.InputDTO;
import uz.shohruh.omborxonabackend.repository.CurrencyRepository;
import uz.shohruh.omborxonabackend.repository.InputRepository;
import uz.shohruh.omborxonabackend.repository.SupplierRepository;
import uz.shohruh.omborxonabackend.repository.WarehouseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class InputService {
    @Autowired
    InputRepository inputRepository;
    @Autowired
    WarehouseRepository warehouseRepository;
    @Autowired
    SupplierRepository supplierRepository;
    @Autowired
    CurrencyRepository currencyRepository;


    public ApiResponse addInput(InputDTO inputDTO) {
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(inputDTO.getWarehouseId());
        Optional<Supplier> optionalSupplier = supplierRepository.findById(inputDTO.getSupplierId());
        Optional<Currency> optionalCurrency = currencyRepository.findById(inputDTO.getCurrencyId());
        if (!optionalWarehouse.isPresent())
            return new ApiResponse("Warehouse Not Found", false);
        if (!optionalSupplier.isPresent())
            return new ApiResponse("Supplier Not Found", false);
        if (!optionalCurrency.isPresent())
            return new ApiResponse("Currency Not Found", false);
        Input input = new Input(
                inputDTO.getData(),
                optionalWarehouse.get(),
                optionalSupplier.get(),
                optionalCurrency.get(),
                inputDTO.getFactureNumber(),
                codes());
        inputRepository.save(input);
        return new ApiResponse("Successfully added", true);
    }

    public ApiResponse editInput(Long id, InputDTO inputDTO){
        Optional<Input> optional = inputRepository.findById(id);
        if (!optional.isPresent())
            return new ApiResponse("Input Not Found", false);
        Optional<Currency> optionalCurrency = currencyRepository.findById(inputDTO.getCurrencyId());
        Optional<Supplier> optionalSupplier = supplierRepository.findById(inputDTO.getSupplierId());
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(inputDTO.getWarehouseId());
        if (!optionalWarehouse.isPresent())
            return new ApiResponse("Warehouse Not Found", false);
        if (!optionalSupplier.isPresent())
            return new ApiResponse("Supplier Not Found", false);
        if (!optionalCurrency.isPresent())
            return new ApiResponse("Currency Not Found", false);
        Input editInput = optional.get();
        editInput.setData(inputDTO.getData());
        editInput.setFactureNumber(inputDTO.getFactureNumber());
        editInput.setCurrency(optionalCurrency.get());
        editInput.setSupplier(optionalSupplier.get());
        editInput.setWarehouse(optionalWarehouse.get());
        editInput.setCode(editInput.getCode());
        inputRepository.save(editInput);
        return new ApiResponse("Successfully edited", true);
    }

    public ApiResponse getById(Long id){
        Optional<Input> input = inputRepository.findById(id);
        if (!input.isPresent())
            return new ApiResponse("Input Not Found", false);
        return new ApiResponse("Input", true, input.get());
    }

    public ApiResponse getAll(){
        List<Input> inputList = inputRepository.findAll();
        if (inputList.isEmpty())
            return new ApiResponse("Input Not Found", false);
        return new ApiResponse("Input", true, inputList);
    }

    public ApiResponse deletedInput(Long id) {
        Optional<Input> optionalInput = inputRepository.findById(id);
        if (optionalInput.isPresent()){
            inputRepository.deleteById(id);
            return new ApiResponse("Successfully deleted",true);
        }
        return new ApiResponse("Deleted Error",false);
    }




    public String codes(){
        List<Input> inputs = inputRepository.findAll();
        if (inputs.size() == 0)
            return String.valueOf(1);
        long code = inputs.size() - 1;
        long i = Long.parseLong(inputs.get((int) code).getCode().trim());
        return String.valueOf(++i);
    }




}
