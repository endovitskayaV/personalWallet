package ru.vsu.personalWallet.domain.repository;

import org.springframework.data.repository.CrudRepository;
import ru.vsu.personalWallet.domain.OperationType;
import ru.vsu.personalWallet.domain.entity.AimEntity;
import ru.vsu.personalWallet.domain.entity.UserEntity;

import java.sql.Timestamp;
import java.util.List;

public interface AimRepository extends CrudRepository<AimEntity, String> {
    List<AimEntity> findAimEntitiesByName(String name);

    List<AimEntity> findAimEntitiesByMoneyValue(long moneyValue);

    List<AimEntity> findAimEntitiesByPeriod(Timestamp period);

    List<AimEntity> findAimEntitiesByOperationType(OperationType operationType);

    List<AimEntity> findAimEntitiesByCreationDate(Timestamp creationDate);

    List<AimEntity> findAllByUserId(String userId);
}
