package ru.vsu.personalWallet.domain.repository;

import org.springframework.data.repository.CrudRepository;
import ru.vsu.personalWallet.domain.OperationType;
import ru.vsu.personalWallet.domain.entity.AimEntity;

import java.util.Date;
import java.util.List;

public interface AimRepository extends CrudRepository<AimEntity, Long> {
    List<AimEntity> findAimEntitiesByName(String name);

    List<AimEntity> findAimEntitiesByOperationType(OperationType operationType);

    List<AimEntity> findAimEntitiesByDate(Date date);
}
