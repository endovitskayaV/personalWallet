package ru.vsu.personalWallet.domain.repository;

import org.springframework.data.repository.CrudRepository;
import ru.vsu.personalWallet.domain.entity.SpendingsLimitEntity;

import java.util.Date;

public interface SpendingsLimitRepository extends CrudRepository<SpendingsLimitEntity, Long> {
    SpendingsLimitEntity findSpendingsLimitEntityByMaxSum(long maxSum);
    SpendingsLimitEntity findSpendingsLimitEntityByDate(Date date);
}
