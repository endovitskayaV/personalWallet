package ru.vsu.personalWallet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.vsu.personalWallet.domain.repository.UserRepository;

@Component("userDetailsService")
public class UserService implements UserDetailsService{
    private UserRepository userRepository;

//    @Autowired
//    public UserService(UserRepository userRepository){
//        this.userRepository=userRepository;
//    }

    @Override
    public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {
       userRepository.findUserEntityByEmail(email);
       return  new Permiss
    }
}
