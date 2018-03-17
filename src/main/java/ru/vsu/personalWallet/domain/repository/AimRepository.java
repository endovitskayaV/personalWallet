package ru.vsu.personalWallet.domain.repository;

import org.springframework.data.repository.CrudRepository;
import ru.vsu.personalWallet.domain.OperationType;
import ru.vsu.personalWallet.domain.entity.AimEntity;

import java.sql.Timestamp;
import java.util.List;

public interface AimRepository extends CrudRepository<AimEntity, Long> {

    AimEntity findAimEntityByIdAndUserId(long id, long userId);

    List<AimEntity> findAimEntitiesByUserId(long userId);

    List<AimEntity> findAimEntitiesByNameAndUserId(String name, long userId);

    List<AimEntity> findAimEntitiesByMoneyValueAndUserId(long moneyValue, long userId);

    List<AimEntity> findAimEntitiesByOperationTypeAndUserId(OperationType operationType, long userId);

}
