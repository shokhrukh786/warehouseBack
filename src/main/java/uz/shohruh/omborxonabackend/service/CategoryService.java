package uz.shohruh.omborxonabackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.shohruh.omborxonabackend.entity.Category;
import uz.shohruh.omborxonabackend.payload.ApiResponse;
import uz.shohruh.omborxonabackend.payload.CategoryDTO;
import uz.shohruh.omborxonabackend.repository.CategoryRepository;


import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public ApiResponse addCategory(CategoryDTO categoryDTO) {
        /**
         * category bo'lmasa yangi yaratadi
         */
        if (categoryRepository.existsByName(categoryDTO.getName()))
            return new ApiResponse("Bunday nomli category mavjud", false);
        Category category = new Category();
        category.setName(categoryDTO.getName());

        /**
         * ParentCategoryga null bo'lmasa uni yangi categoryni bo'g'lab qo'yish
         */
        if (categoryDTO.getParentCategoryId() != null) {
            Optional<Category> optional = categoryRepository.findById(categoryDTO.getParentCategoryId());
            if (!optional.isPresent())
                return new ApiResponse("ParentCategory mavjud emas", false);
            category.setParentCategory(optional.get());
        }
        categoryRepository.save(category);
        return new ApiResponse("Muvaqqiyatli saqlandi", true);
    }

    public ApiResponse editCategory(Long id, CategoryDTO categoryDTO) {
        Optional<Category> optional = categoryRepository.findById(id);
        if (!optional.isPresent())
            return new ApiResponse("Bunday nomli category mavjud emas", false);
        Category editCategory = optional.get();
//        if (categoryRepository.existsByNameNot(categoryDTO.getName()))
//            return new ApiResponse("Bunday nomli category mavjud", false);
        /**
         * ParentCategoryga null bo'lmasa uni yangi categoryni bo'g'lab qo'yish
         */
        if (categoryDTO.getParentCategoryId() != null) {
            Optional<Category> optionalParent = categoryRepository.findById(categoryDTO.getParentCategoryId());
            if (!optionalParent.isPresent())
                return new ApiResponse("Bunday parentCategory mavjud emas", false);
            editCategory.setParentCategory(optionalParent.get());
        }
        editCategory.setName(categoryDTO.getName());
        categoryRepository.save(editCategory);
        return new ApiResponse("Muvaqqiyatli saqlandi", true);
    }

    public ApiResponse deleteCategory(Long id){
        if (!categoryRepository.existsById(id))
            return new ApiResponse("Categoriya mavjud emas", false);
        categoryRepository.deleteById(id);
        return new ApiResponse("Muvaffaqiyatli o'chirildi", true);
    }

    public ApiResponse getByIdCategory(Long id){
        Optional<Category> optional = categoryRepository.findById(id);
        return optional.map(category -> new ApiResponse("Category", true, category))
                .orElseGet(() -> new ApiResponse("Bunday category topilmadi", false));
    }

    public ApiResponse getAllCategory(){
        List<Category> categoryList = categoryRepository.findAll();
        if (!categoryList.isEmpty())
            return new ApiResponse("Barcha categorylar", true, categoryList);
        return new ApiResponse("Categorylar mavjud emas", false);
    }

}
