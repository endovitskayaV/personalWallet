package ru.vsu.personalWallet.domain.repository;

import org.springframework.data.repository.CrudRepository;
import ru.vsu.personalWallet.domain.OperationType;
import ru.vsu.personalWallet.domain.entity.TransactionEntity;

import java.util.List;

public interface TransactionRepository extends CrudRepository<TransactionEntity, Long> {
    TransactionEntity findTransactionEntityByIdAndUserId(long id, long userId);

    List<TransactionEntity> findTransactionEntitiesByUserId(long userId);

    List<TransactionEntity> findTransactionEntityByOperationTypeAndUserId
            (OperationType operationType, long userId);

    List<TransactionEntity> findTransactionEntityByMoneyValueAndUserId(long moneyValue, long userId);

    List<TransactionEntity> findTransactionEntityByCategoryIdAndUserId(long id, long userId);
}
