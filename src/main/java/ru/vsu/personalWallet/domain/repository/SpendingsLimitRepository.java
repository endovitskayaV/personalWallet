package ru.vsu.personalWallet.domain.repository;

import org.springframework.data.repository.CrudRepository;
import ru.vsu.personalWallet.domain.entity.ChequeEntity;
import ru.vsu.personalWallet.domain.entity.SpendingsLimitEntity;

import java.sql.Timestamp;
import java.util.List;

public interface SpendingsLimitRepository extends CrudRepository<SpendingsLimitEntity, Long> {
    SpendingsLimitEntity findSpendingsLimitEntityByIdAndUserId(long id, long userId);

    List<SpendingsLimitEntity> findSpendingsLimitEntitiesByUserId(long userId);

    List<SpendingsLimitEntity> findSpendingsLimitEntitiesByMaxSumAndUserId(long maxSum, long userId);

    List<SpendingsLimitEntity> findSpendingsLimitEntitiesByCreationDateAndUserId
            (Timestamp creationDate, long userId);

}
