package uz.shohruh.omborxonabackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.shohruh.omborxonabackend.entity.Category;
import uz.shohruh.omborxonabackend.entity.Measurement;
import uz.shohruh.omborxonabackend.entity.Product;
import uz.shohruh.omborxonabackend.payload.ApiResponse;
import uz.shohruh.omborxonabackend.payload.ProductDto;
import uz.shohruh.omborxonabackend.repository.CategoryRepository;
import uz.shohruh.omborxonabackend.repository.MeasurementRepository;
import uz.shohruh.omborxonabackend.repository.ProductRepository;


import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    MeasurementRepository measurementRepository;

    public ApiResponse addProduct(ProductDto productDto){
        if (productRepository.existsByName(productDto.getName()))
            return new ApiResponse("Bunday product mavjud", false);
        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
        if (!optionalCategory.isPresent())
            return new ApiResponse("Bunday category mavjud emas", false);
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(productDto.getMeasurementId());
        if (!optionalMeasurement.isPresent())
            return new ApiResponse("Bunday o'lchov birligi mavjud emas", false);

        Category category = optionalCategory.get();
        Measurement measurement = optionalMeasurement.get();
        Product product = new Product();
        product.setName(productDto.getName());
        product.setCategory(category);
        product.setMeasurement(measurement);
        product.setActive(true);
        product.setCode(productDto.getCode());
        productRepository.save(product);
        return new ApiResponse("Muvaqqiyatli saqlandi", true);
    }

    public ApiResponse editProduct(ProductDto productDto, Long id){

        Optional<Product> optionalProduct = productRepository.findById(id);
        if (!optionalProduct.isPresent())
            return new ApiResponse("Product mavjud emas", false);
        
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(productDto.getMeasurementId());
        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
        if (!optionalMeasurement.isPresent())
            return new ApiResponse("Bunday o'lchov mavjud emas", false);
        if (!optionalCategory.isPresent())
            return new ApiResponse("Bunday category mavjud emas", false);

        Product oldProduct = optionalProduct.get();
        oldProduct.setName(productDto.getName());
        oldProduct.setMeasurement(optionalMeasurement.get());
        oldProduct.setCategory(optionalCategory.get());
        oldProduct.setCode(oldProduct.getCode());
        productRepository.save(oldProduct);
        return new ApiResponse("Muvaqqiyatli saqlandi", true);
    }


    public ApiResponse getAllProduct(){
        List<Product> productList = productRepository.findAll();
        return new ApiResponse("Products", true, productList);
    }

    public ApiResponse getByIdProduct(Long id){
        Optional<Product> optionalProduct = productRepository.findById(id);
        return new ApiResponse("Product", true, optionalProduct.get());
    }

    public ApiResponse deleteProduct(Long id){
        if (!productRepository.existsById(id))
            return new ApiResponse("Product mavjud emas", false);
        productRepository.deleteById(id);
        return new ApiResponse("Muvaqqiyatli", true);
    }
}

















