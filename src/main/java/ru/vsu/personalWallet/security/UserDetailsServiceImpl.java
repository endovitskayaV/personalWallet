package ru.vsu.personalWallet.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vsu.personalWallet.domain.entity.UserEntity;
import ru.vsu.personalWallet.domain.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    //@Autowired
    private UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    @Override
   // @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity user = userRepository.findUserEntityByEmail(email);
        if (user != null)
            return new SpringSecurityUser(
                    user.getId(),
                    user.getEmail(),
                    user.getPassword());//,
//                    null,
//                    null);
        else throw new UsernameNotFoundException("");
               // (String.format("No User found with username '%s'.", email));

    }
}

