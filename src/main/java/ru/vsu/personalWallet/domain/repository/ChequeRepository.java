package ru.vsu.personalWallet.domain.repository;

import org.springframework.data.repository.CrudRepository;
import ru.vsu.personalWallet.domain.entity.ChequeEntity;

import java.util.Date;

public interface ChequeRepository extends CrudRepository<ChequeEntity, Long> {
    ChequeEntity findChequeEntityByName(String name);
    ChequeEntity findChequeEntityByDate(Date date );
}
