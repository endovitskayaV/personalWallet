package ru.vsu.personalWallet.domain.repository;

import org.springframework.data.repository.CrudRepository;
import ru.vsu.personalWallet.domain.entity.SpendingsLimitEntity;

import java.sql.Timestamp;
import java.util.List;

public interface SpendingsLimitRepository extends CrudRepository<SpendingsLimitEntity, Long> {
    List<SpendingsLimitEntity> findSpendingsLimitEntitiesByMaxSum(long maxSum);

    List<SpendingsLimitEntity> findSpendingsLimitEntitiesByCreationDate(Timestamp creationDate);
}
