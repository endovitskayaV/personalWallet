//package ru.vsu.personalWallet.s2;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import ru.vsu.personalWallet.domain.entity.UserEntity;
//import ru.vsu.personalWallet.domain.repository.UserRepository;
//
//import java.util.ArrayList;
//
//@Service
//public class MyUserService implements UserDetailsService {
//    private UserRepository userRepository;
//
//    @Autowired
//    MyUserService (UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @Override
//    public MyUser loadUserByUsername(final String email)
//            throws UsernameNotFoundException {
//        UserEntity userEntity=userRepository.findUserEntityByEmail(email);
//        return  new MyUser
//                (userEntity.getEmail(), userEntity.getPassword(), new ArrayList<>());
//    }
//}
