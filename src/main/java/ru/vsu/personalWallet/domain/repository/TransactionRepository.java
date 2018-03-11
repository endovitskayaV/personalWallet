package ru.vsu.personalWallet.domain.repository;

import org.springframework.data.repository.CrudRepository;
import ru.vsu.personalWallet.domain.OperationType;
import ru.vsu.personalWallet.domain.entity.TransactionEntity;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public interface TransactionRepository extends CrudRepository<TransactionEntity, Long> {
    TransactionEntity findTransactionEntityById(String id);

    List<TransactionEntity> findTransactionEntityByOperationType(OperationType operationType);

    List<TransactionEntity> findTransactionEntityByCreationDate(Timestamp creationDate);

    List<TransactionEntity> findTransactionEntityByMoneyValue(long moneyValue);

    List<TransactionEntity> findTransactionEntityByComment(String comment);

    List<TransactionEntity> findTransactionEntityByCategoryId(String id);
}
