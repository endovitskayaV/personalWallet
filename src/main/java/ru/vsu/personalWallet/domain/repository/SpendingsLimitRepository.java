package ru.vsu.personalWallet.domain.repository;

import org.springframework.data.repository.CrudRepository;
import ru.vsu.personalWallet.domain.entity.SpendingsLimitEntity;

public interface SpendingsLimitRepository extends CrudRepository<SpendingsLimitEntity, Long> {
}
