package ru.vsu.personalWallet.domain.repository;

import org.springframework.data.repository.CrudRepository;
import ru.vsu.personalWallet.domain.entity.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Long>{
    UserEntity findUserEntityByEmail(String email);
}
