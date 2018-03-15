package ru.vsu.personalWallet.domain.repository;

import org.springframework.data.repository.CrudRepository;
import ru.vsu.personalWallet.domain.entity.ChequeEntity;

import java.sql.Timestamp;
import java.util.List;

public interface ChequeRepository extends CrudRepository<ChequeEntity, String> {
    List<ChequeEntity> findChequeEntitiesByName(String name);

    List<ChequeEntity> findChequeEntitiesByCreationDate(Timestamp creationDate);
}
