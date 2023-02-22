package uz.shohruh.omborxonabackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.shohruh.omborxonabackend.entity.Currency;
import uz.shohruh.omborxonabackend.payload.ApiResponse;
import uz.shohruh.omborxonabackend.payload.CurrencyDTO;
import uz.shohruh.omborxonabackend.repository.CurrencyRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CurrencyService {
    @Autowired
    CurrencyRepository currencyRepository;

    public ApiResponse addCurrency(CurrencyDTO currencyDTO){
        if (currencyRepository.existsByName(currencyDTO.getName()))
            return new ApiResponse("Bunday valyuta nomli mavjud", false);
        Currency currency = new Currency(
                currencyDTO.getName(),
                true);
        currencyRepository.save(currency);
        return new ApiResponse("Muavvaqiyatli saqlandi", true);
    }

    public ApiResponse editCurrency(CurrencyDTO currencyDTO, Long id){
        if (!currencyRepository.existsById(id))
            return new ApiResponse("Bunday valyuta birligi mavjud emas", false);
        Optional<Currency> optional = currencyRepository.findById(id);
        if (!optional.isPresent())
            return new ApiResponse("Bunday valyuta birligi mavjud emas", false);
        Currency editCurrency = optional.get();
        editCurrency.setName(currencyDTO.getName());
        currencyRepository.save(editCurrency);
        return new ApiResponse("Muavvaqiyatli taxrirlandi", true);
    }

    public ApiResponse deleteCurrency(Long id){
        if (!currencyRepository.existsById(id))
            return new ApiResponse("Mavjud emas", false);
        currencyRepository.deleteById(id);
        return new ApiResponse("Muvaqqiyatli o'chirildi", true);
    }

    public ApiResponse getByIdCurrency(Long id){
        Optional<Currency> optional = currencyRepository.findById(id);
        return optional.map(currency ->
                        new ApiResponse("valyuta turlari", true, currency))
                .orElseGet(() ->
                        new ApiResponse("Bunday valyuta turi mavjud emas", false));
    }

    public ApiResponse getAllCurrency(){
        List<Currency> currencyList = currencyRepository.findAll();
        if (!currencyList.isEmpty())
            return new ApiResponse("Barcha valyuta birliklar", true, currencyList);
        return new ApiResponse("valyuta birliklari mavjud emas", false);
    }


}
