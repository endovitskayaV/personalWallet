package ru.vsu.personalWallet.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.vsu.personalWallet.domain.entity.UserEntity;
import ru.vsu.personalWallet.domain.dto.UserDto;
import ru.vsu.personalWallet.domain.repository.UserRepository;
import ru.vsu.personalWallet.util.EntityToDto;

import java.util.Arrays;
import java.util.List;


@Service
public class UserService implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findUserEntityByEmail(email);
        if (userEntity == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new User(userEntity.getEmail(), userEntity.getPassword(), getAuthority());
    }

    private List<SimpleGrantedAuthority> getAuthority() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }

    public UserDto add(UserDto userDto) {
       if (userRepository.findUserEntityByEmail(userDto.getEmail())!=null)//exists, cannot add
           return null;
        userRepository.save(toEntity(userDto));
        return EntityToDto.toDto
                (userRepository.findUserEntityByEmail(userDto.getEmail()));
    }

    public boolean edit(UserDto userDto) {
        long id = userDto.getId();
        if (userRepository.findOne(id) == null) return false; //no such entity, cannot edit
        else userRepository.save(toEntity(userDto));
        return true;
    }

    public UserDto findById(long id) {
        return EntityToDto.toDto(userRepository.findOne(id));
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
