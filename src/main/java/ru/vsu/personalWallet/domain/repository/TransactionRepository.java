package ru.vsu.personalWallet.domain.repository;

import org.springframework.data.repository.CrudRepository;
import ru.vsu.personalWallet.domain.OperationType;
import ru.vsu.personalWallet.domain.entity.TransactionEntity;

import java.util.Date;
import java.util.Locale;

public interface TransactionRepository extends CrudRepository<TransactionEntity, Long> {
    TransactionEntity findTransactionEntityByOperationType(OperationType operationType);
    TransactionEntity findTransactionEntityByDate(Date date);
    TransactionEntity findTransactionEntityByMoneyValue(long moneyValue);
    TransactionEntity findTransactionEntityByComment(String comment);
    TransactionEntity findTransactionEntityByCategoryName(String name);
}
