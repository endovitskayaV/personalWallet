package ru.vsu.personalWallet.domain.repository;

import org.springframework.data.repository.CrudRepository;
import ru.vsu.personalWallet.domain.entity.ChequeEntity;

public interface ChequeRepository extends CrudRepository<ChequeEntity, Long> {
}
