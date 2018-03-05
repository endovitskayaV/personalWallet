package ru.vsu.personalWallet.domain.repository;

import org.springframework.data.repository.CrudRepository;
import ru.vsu.personalWallet.domain.entity.TransactionEntity;

public interface TransactionRepository extends CrudRepository<TransactionEntity, Long> {
}
