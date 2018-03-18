package ru.vsu.personalWallet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vsu.personalWallet.domain.dto.AimDto;
import ru.vsu.personalWallet.domain.dto.CategoryDto;
import ru.vsu.personalWallet.domain.entity.AimEntity;
import ru.vsu.personalWallet.domain.entity.CategoryEntity;
import ru.vsu.personalWallet.domain.repository.CategoryRepository;
import ru.vsu.personalWallet.domain.repository.UserRepository;
import ru.vsu.personalWallet.util.EntityToDto;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {
    private CategoryRepository categoryRepository;
    private UserRepository userRepository;

    @Autowired
    CategoryService(CategoryRepository categoryRepository, UserRepository userRepository){
        this.categoryRepository=categoryRepository;
        this.userRepository=userRepository;
    }

    public boolean delete(long id, long userId) {
        CategoryEntity categoryEntity = categoryRepository.findCategoryEntityByIdAndUserId(id, userId);
        if (categoryEntity == null) return false; //no such entity, cannot delete
        else categoryRepository.delete(categoryEntity);
        return true;
    }

    public CategoryDto add(CategoryDto categoryDto) {
        //always can add
       return EntityToDto.toDto(categoryRepository.save(toEntity(categoryDto)));
    }

    public boolean edit(CategoryDto categoryDto, long userId) {
        long id = categoryDto.getId();
        if (categoryRepository.findCategoryEntityByIdAndUserId(id, userId)== null)
            return false;//no such entity, cannot edit
        else categoryRepository.save(toEntity(categoryDto));
        return true;
    }

    public List<CategoryDto> findAllByUserId(long userId){
        List<CategoryDto> categoryDtoList=new ArrayList<>();
        categoryRepository.findCategoryEntitiesByUserId(userId)
                .forEach(x->categoryDtoList.add(EntityToDto.toDto(x)));
        return categoryDtoList;
    }

    public CategoryDto findByIdAndUserId(long id, long userId){
        return EntityToDto.toDto(categoryRepository.findCategoryEntityByIdAndUserId(id, userId));
    }

    public List<CategoryDto> findByNameAndUserId(String name, long userId){
        List<CategoryDto> categoryDtoList=new ArrayList<>();
        categoryRepository.findCategoryEntityByNameAndUserId(name, userId)
                .forEach(x->categoryDtoList.add(EntityToDto.toDto(x)));
        return categoryDtoList;
    }

    private CategoryEntity toEntity(CategoryDto categoryDto) {
        if (categoryDto != null) {
            return new CategoryEntity()
                    .setId(categoryDto.getId())
                    .setUser(userRepository.findOne(categoryDto.getUserId()))
                    .setName(categoryDto.getName());
        } else return null;
    }

}
