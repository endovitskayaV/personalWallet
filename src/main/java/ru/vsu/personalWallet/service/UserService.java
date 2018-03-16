package ru.vsu.personalWallet.service;



import org.springframework.stereotype.Service;
import ru.vsu.personalWallet.model.User;
import ru.vsu.personalWallet.model.UserDto;

import java.util.List;

@Service
public interface UserService {

    User save(UserDto user);
    List<User> findAll();
    void delete(long id);
    User findOne(String username);

    User findById(Long id);
}
