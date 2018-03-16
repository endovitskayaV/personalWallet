package ru.vsu.personalWallet.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {
   // @Autowired
    private AuthenticationManager authenticationManager;

    //@Autowired
    private UserDetailsService userDetailsService;

    //@Autowired
    private TokenUtils tokenUtils;

    @Autowired
    public SecurityService(AuthenticationManager authenticationManager,
                           UserDetailsService userDetailsService,
                           TokenUtils tokenUtils){
        this.authenticationManager=authenticationManager;
        this.userDetailsService=userDetailsService;
        this.tokenUtils=tokenUtils;
    }

//    public String findLoggedInEmail() {
//        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        if (userDetails instanceof UserDetails) {
//            return ((UserDetails) userDetails).getUsername();
//        }
//        return null;
//    }

    public void logout(){
        SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
        //TODO: delete token from db
    }

    public String login(String email, String password) {   //throws Exception {
//        UsernamePasswordAuthenticationToken authenticationToken =
//                new UsernamePasswordAuthenticationToken(email, password);
//        Authentication authentication = authenticationManager.authenticate(authenticationToken);
//        if (authentication.isAuthenticated()) {
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//            UserDetails userDetails = userDetailsService.loadUserByUsername(email);
//            return this.tokenUtils.generateToken(userDetails);
//            //TODO: save token to db
//        }
//        return null;
        return "";
    }

//    public String refresh(String token) throws Exception {
//        String username = this.tokenUtils.getUsernameFromToken(token);
//        SpringSecurityUser user = (SpringSecurityUser) this.userDetailsService.loadUserByUsername(username);
//        if (this.tokenUtils.canTokenBeRefreshed(token, user.getLastPasswordReset())) {
//            String refreshedToken = this.tokenUtils.refreshToken(token);
//            return refreshedToken;
//        } else {
//            throw new Exception("Unable to refresh token");
//        }
//    }
}