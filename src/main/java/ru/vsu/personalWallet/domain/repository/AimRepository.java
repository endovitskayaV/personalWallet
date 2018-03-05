package ru.vsu.personalWallet.domain.repository;

import org.springframework.data.repository.CrudRepository;
import ru.vsu.personalWallet.domain.OperationType;
import ru.vsu.personalWallet.domain.entity.AimEntity;

import java.util.Date;

public interface AimRepository extends CrudRepository<AimEntity, Long> {
    AimEntity findAimEntityByName(String name);
    AimEntity findAimEntityByOperationType(OperationType operationType);
    AimEntity findAimEntityByDate(Date date);
}
