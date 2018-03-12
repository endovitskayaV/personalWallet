package ru.vsu.personalWallet.domain.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vsu.personalWallet.domain.entity.CategoryEntity;
import ru.vsu.personalWallet.domain.repository.CategoryRepository;
import ru.vsu.personalWallet.domain.repository.TransactionRepository;

@Service
public class CategoryConverter {
    private static CategoryRepository categoryRepository;
    @Autowired
    public CategoryConverter(CategoryRepository _categoryRepository) {
        categoryRepository=_categoryRepository;
    }

    public static CategoryEntity dtoToEntity(String categoryId){
        return categoryRepository.findCategoryEntityById(categoryId);
    }

}
