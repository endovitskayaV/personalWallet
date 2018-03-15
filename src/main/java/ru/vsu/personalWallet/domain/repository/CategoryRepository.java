package ru.vsu.personalWallet.domain.repository;

import org.springframework.data.repository.CrudRepository;
import ru.vsu.personalWallet.domain.entity.CategoryEntity;

import java.util.List;

public interface CategoryRepository extends CrudRepository<CategoryEntity, String> {
    List<CategoryEntity> findCategoryEntityByName(String name);
}
