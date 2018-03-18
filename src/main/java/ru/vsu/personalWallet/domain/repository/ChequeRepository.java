package ru.vsu.personalWallet.domain.repository;

import org.springframework.data.repository.CrudRepository;
import ru.vsu.personalWallet.domain.entity.ChequeEntity;

import java.sql.Timestamp;
import java.util.List;

public interface ChequeRepository extends CrudRepository<ChequeEntity, Long> {
    ChequeEntity findChequeEntityByIdAndUserId(long id, long userId);

    List<ChequeEntity> findChequeEntitiesByUserId(long userId);

    List<ChequeEntity> findChequeEntitiesByNameAndUserId(String name, long userId);

    List<ChequeEntity> findChequeEntitiesByCreationDateAndUserId(Timestamp creationDate, long userId);
}
