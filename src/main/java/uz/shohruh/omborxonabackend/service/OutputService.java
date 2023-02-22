package uz.shohruh.omborxonabackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.shohruh.omborxonabackend.entity.Client;
import uz.shohruh.omborxonabackend.entity.Currency;
import uz.shohruh.omborxonabackend.entity.Output;
import uz.shohruh.omborxonabackend.entity.Warehouse;
import uz.shohruh.omborxonabackend.payload.ApiResponse;
import uz.shohruh.omborxonabackend.payload.OutputDTO;
import uz.shohruh.omborxonabackend.repository.ClientRepository;
import uz.shohruh.omborxonabackend.repository.CurrencyRepository;
import uz.shohruh.omborxonabackend.repository.OutputRepository;
import uz.shohruh.omborxonabackend.repository.WarehouseRepository;


import java.util.List;
import java.util.Optional;

@Service
public class OutputService {
    @Autowired
    OutputRepository outputRepository;
    @Autowired
    WarehouseRepository warehouseRepository;
    @Autowired
    CurrencyRepository currencyRepository;
    @Autowired
    ClientRepository clientRepository;

    public ApiResponse addOutput(OutputDTO outputDTO) {
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(outputDTO.getWarehouseId());
        Optional<Currency> optionalCurrency = currencyRepository.findById(outputDTO.getCurrencyId());
        Optional<Client> optionalClient = clientRepository.findById(outputDTO.getClientId());

        if (!optionalWarehouse.isPresent())
            return new ApiResponse("Warehouse Not Found", false);
        if (!optionalCurrency.isPresent())
            return new ApiResponse("Currency Not Found", false);
        if (!optionalClient.isPresent())
            return new ApiResponse("Currency Not Found", false);
        Output output = new Output(
                outputDTO.getData(),
                optionalWarehouse.get(),
                optionalCurrency.get(),
                outputDTO.getFactureNumber(),
                codes(),
                optionalClient.get()
                );
        outputRepository.save(output);
        return new ApiResponse("Successfully added", true);
    }

    public ApiResponse editOutput(Long id, OutputDTO outputDTO){
        Optional<Output> optional = outputRepository.findById(id);
        if (!optional.isPresent())
            return new ApiResponse("Input Not Found", false);
        Optional<Currency> optionalCurrency = currencyRepository.findById(outputDTO.getCurrencyId());
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(outputDTO.getWarehouseId());
        Optional<Client> optionalClient = clientRepository.findById(outputDTO.getClientId());
        if (!optionalWarehouse.isPresent())
            return new ApiResponse("Warehouse Not Found", false);
        if (!optionalCurrency.isPresent())
            return new ApiResponse("Currency Not Found", false);
        if (!optionalClient.isPresent())
            return new ApiResponse("Client Not Found", false);
        Output editOutput = optional.get();
        editOutput.setDate(outputDTO.getData());
        editOutput.setFactureNumber(outputDTO.getFactureNumber());
        editOutput.setCurrency(optionalCurrency.get());
        editOutput.setWarehouse(optionalWarehouse.get());
        editOutput.setCode(codes());
        outputRepository.save(editOutput);
        return new ApiResponse("Successfully edited", true);
    }

    public ApiResponse getByIdOutput(Long id){
        Optional<Output> input = outputRepository.findById(id);
        if (!input.isPresent())
            return new ApiResponse("Input Not Found", false);
        return new ApiResponse("Input", true, input.get());
    }

    public ApiResponse getAllOutput(){
        List<Output> inputList = outputRepository.findAll();
        if (inputList.isEmpty())
            return new ApiResponse("Input Not Found", false);
        return new ApiResponse("Input", true, inputList);
    }

    public ApiResponse deletedOutput(Long id) {
        Optional<Output> optionalInput = outputRepository.findById(id);
        if (optionalInput.isPresent()){
            outputRepository.deleteById(id);
            return new ApiResponse("Successfully deleted",true);
        }
        return new ApiResponse("Deleted Error",false);
    }




    public String codes(){
        List<Output> inputs = outputRepository.findAll();
        if (inputs.size() == 0)
            return String.valueOf(1);
        long code = inputs.size() - 1;
        long i = Long.parseLong(inputs.get((int) code).getCode().trim());
        return String.valueOf(++i);
    }

}
