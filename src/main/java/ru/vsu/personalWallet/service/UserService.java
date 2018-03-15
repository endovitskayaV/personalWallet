package ru.vsu.personalWallet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vsu.personalWallet.domain.dto.UserDto;
import ru.vsu.personalWallet.domain.entity.UserEntity;
import ru.vsu.personalWallet.domain.repository.UserRepository;
import ru.vsu.personalWallet.domain.util.EntityToDto;

import java.util.ArrayList;
import java.util.List;

//@Component("userDetailsService")

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    public boolean delete(String id) {
       UserEntity userEntity = userRepository.findOne(id);
        if (userEntity == null) return false;
        else userRepository.delete(userEntity);
        return true;
    }

    public boolean add(UserDto userDto) {
        if (userRepository.findOne(userDto.getId()) != null) return false;
        else userRepository.save(toEntity(userDto));
        return true;
    }

    public boolean edit(UserDto userDto) {
        if (userRepository.findOne(userDto.getId()) == null) return false;
        else userRepository.save(toEntity(userDto));
        return true;
    }

    public UserDto findById(String id) {
        return EntityToDto.toDto(userRepository.findOne(id));
    }

    public List<UserDto> findAll() {
        List<UserDto> userDtoList = new ArrayList<>();
        userRepository.findAll().forEach(x -> userDtoList.add(EntityToDto.toDto(x)));
        return userDtoList;
    }

    public UserDto findByEmailAndPassword(String email, String password) {
       return EntityToDto.toDto(userRepository.findUserEntityByEmailAndPassword(email, password));
    }

    public UserDto findByEmail(String email) {
        return EntityToDto.toDto(userRepository.findUserEntityByEmail(email));
    }
    private UserEntity toEntity(UserDto userDto) {
        if (userDto != null) {
            return new UserEntity()
                    .setId(userDto.getId())
                    .setEmail(userDto.getEmail())
                    .setPassword(userDto.getPassword());
        } else return null;
    }
}
