package ru.vsu.personalWallet.domain.repository;

import org.springframework.data.repository.CrudRepository;
import ru.vsu.personalWallet.domain.entity.AimEntity;
import ru.vsu.personalWallet.domain.entity.CategoryEntity;

import java.util.List;

public interface CategoryRepository extends CrudRepository<CategoryEntity, Long> {
    CategoryEntity findCategoryEntityByIdAndUserId(long id, long userId);

    List<CategoryEntity> findCategoryEntitiesByUserId(long userId);

    List<CategoryEntity> findCategoryEntityByNameAndUserId(String name, long userId);
}
