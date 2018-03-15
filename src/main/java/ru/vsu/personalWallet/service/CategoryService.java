package ru.vsu.personalWallet.service;

import org.springframework.stereotype.Service;
import ru.vsu.personalWallet.domain.dto.CategoryDto;
import ru.vsu.personalWallet.domain.entity.CategoryEntity;
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

    public boolean delete(String id) {
        CategoryEntity categoryEntity = categoryRepository.findOne(id);
        if (categoryEntity == null) return false;
        else categoryRepository.delete(categoryEntity);
        return true;
    }

    public boolean add(CategoryDto categoryDto) {
        if (categoryRepository.findOne(categoryDto.getId()) != null) return false;
        else categoryRepository.save(DtoToEntity.toEntity(categoryDto));
        return true;
    }

    public boolean edit(CategoryDto categoryDto) {
        if (categoryRepository.findOne(categoryDto.getId())== null) return false;
        else categoryRepository.save(DtoToEntity.toEntity(categoryDto));
        return true;
    }
    public List<CategoryDto> findAll(){
        List<CategoryDto> categoryDtoList=new ArrayList<>();
        categoryRepository.findAll().forEach(x->categoryDtoList.add(EntityToDto.toDto(x)));
        return categoryDtoList;
    }

    public CategoryDto findById(String id){
        return EntityToDto.toDto(categoryRepository.findOne(id));
    }

    public List<CategoryDto> findByName(String name){
        List<CategoryDto> categoryDtoList=new ArrayList<>();
        categoryRepository.findCategoryEntityByName(name)
                .forEach(x->categoryDtoList.add(EntityToDto.toDto(x)));
        return categoryDtoList;
    }

}
