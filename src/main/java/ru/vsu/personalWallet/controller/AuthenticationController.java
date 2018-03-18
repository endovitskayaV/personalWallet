package ru.vsu.personalWallet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.vsu.personalWallet.domain.dto.UserDto;
import ru.vsu.personalWallet.jwt.JwtTokenUtil;
import ru.vsu.personalWallet.service.UserService;
import ru.vsu.personalWallet.util.LoginUser;

@RestController
public class AuthenticationController {

    private AuthenticationManager authenticationManager;
    private JwtTokenUtil jwtTokenUtil;
    private UserService userService;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager,
                                    JwtTokenUtil jwtTokenUtil,
                                    UserService userService){
        this.authenticationManager=authenticationManager;
        this.jwtTokenUtil=jwtTokenUtil;
        this.userService=userService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> register(@RequestBody LoginUser loginUser) throws AuthenticationException {

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUser.getEmail(),
                        loginUser.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final UserDto userDto = userService.findByEmail(loginUser.getEmail());
        final String token = jwtTokenUtil.generateToken(userDto);
        return ResponseEntity.ok(token);
    }
}
