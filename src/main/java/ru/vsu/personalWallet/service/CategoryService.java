package ru.vsu.personalWallet.service;

import org.springframework.stereotype.Service;
import ru.vsu.personalWallet.domain.dto.CategoryDto;
import ru.vsu.personalWallet.domain.dto.TransactionDto;
import ru.vsu.personalWallet.domain.repository.CategoryRepository;
import ru.vsu.personalWallet.domain.util.DtoToEntity;
import ru.vsu.personalWallet.domain.util.EntityToDto;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {
    private CategoryRepository categoryRepository;
    CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository=categoryRepository;
    }

    public void delete(long aimId) {
        categoryRepository.delete(aimId);
    }

    public void save(CategoryDto categoryDto){
        categoryRepository.save(DtoToEntity.toEntity(categoryDto));
    }

    public List<CategoryDto> findAll(){
        List<CategoryDto> categoryDtoList=new ArrayList<>();
        categoryRepository.findAll().forEach(x->categoryDtoList.add(EntityToDto.toDto(x)));
        return categoryDtoList;
    }

    public CategoryDto findById(long id){
        return EntityToDto.toDto(categoryRepository.findOne(id));
    }

    public CategoryDto findByName(String name){
        return EntityToDto.toDto(categoryRepository.findCategoryEntityByName(name));
    }

}
