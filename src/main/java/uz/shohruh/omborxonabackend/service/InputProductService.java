package uz.shohruh.omborxonabackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.shohruh.omborxonabackend.entity.Input;
import uz.shohruh.omborxonabackend.entity.InputProduct;
import uz.shohruh.omborxonabackend.entity.Product;
import uz.shohruh.omborxonabackend.payload.ApiResponse;
import uz.shohruh.omborxonabackend.payload.InputProductDTO;
import uz.shohruh.omborxonabackend.repository.InputProductRepository;
import uz.shohruh.omborxonabackend.repository.InputRepository;
import uz.shohruh.omborxonabackend.repository.ProductRepository;


import java.util.List;
import java.util.Optional;

@Service
public class InputProductService {
    @Autowired
    InputProductRepository inputProductRepository;
    @Autowired
    InputRepository inputRepository;
    @Autowired
    ProductRepository productRepository;

    public ApiResponse addInputProduct(InputProductDTO inputProductDTO){

        Optional<Input> optionalInput = inputRepository.findById(inputProductDTO.getInputId());
        if (!optionalInput.isPresent())
            return new ApiResponse("Input mavjud emas", false);

        Optional<Product> optionalProduct = productRepository.findById(inputProductDTO.getProductId());
        if (!optionalProduct.isPresent())
            return new ApiResponse("Product mavjud emas", false);

        InputProduct inputProduct = new InputProduct();
        Input input = optionalInput.get();
        Product product = optionalProduct.get();
        inputProduct.setInput(input);
        inputProduct.setProduct(product);
        inputProduct.setAmount(inputProductDTO.getAmount());
        inputProduct.setPrice(inputProductDTO.getPrice());
        inputProduct.setExpireDate(inputProductDTO.getExpireDate());
        inputProductRepository.save(inputProduct);
        return new ApiResponse("Muvaqqiyatli saqlandi", true);
    }

    public ApiResponse editInputProduct(InputProductDTO inputProductDTO, Long id){

        Optional<InputProduct> optionalInputProduct = inputProductRepository.findById(id);
        if (!optionalInputProduct.isPresent())
            return new ApiResponse("Mavjud emas", false);

        Optional<Input> optionalInput = inputRepository.findById(inputProductDTO.getInputId());
        if (!optionalInput.isPresent())
            return new ApiResponse("Input mavjud emas", false);

        Optional<Product> optionalProduct = productRepository.findById(inputProductDTO.getProductId());
        if (!optionalProduct.isPresent())
            return new ApiResponse("Product mavjud emas", false);

        InputProduct oldInputProduct = optionalInputProduct.get();
        oldInputProduct.setProduct(optionalProduct.get());
        oldInputProduct.setInput(optionalInput.get());
        oldInputProduct.setPrice(inputProductDTO.getPrice());
        oldInputProduct.setAmount(inputProductDTO.getAmount());
        oldInputProduct.setExpireDate(inputProductDTO.getExpireDate());
        inputProductRepository.save(oldInputProduct);
        return new ApiResponse("Muvaqqiyatli saqlandi", true);
    }

    public ApiResponse getByIdInputProduct(Long id){
        Optional<InputProduct> optionalInputProduct = inputProductRepository.findById(id);
        if (!optionalInputProduct.isPresent())
            return new ApiResponse("Mavjud emas", false);
        return new ApiResponse("input prduct", true, optionalInputProduct.get());
    }

    public ApiResponse getAllInputProduct(){
        List<InputProduct> inputProductList = inputProductRepository.findAll();
        return new ApiResponse("InputProducts", true, inputProductList);
    }

    public ApiResponse deleteInputProduct(Long id){
        if (!inputProductRepository.existsById(id))
            return new ApiResponse("Mavjud emas", false);
        inputProductRepository.deleteById(id);
        return new ApiResponse("Muvaqqiyatli o'chirildi", true);
    }

}















