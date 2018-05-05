package ru.vsu.personalWallet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import ru.vsu.personalWallet.dto.HttpResponseDto;
import ru.vsu.personalWallet.dto.LoginUserDto;
import ru.vsu.personalWallet.dto.TokenDto;
import ru.vsu.personalWallet.dto.UserDto;
import ru.vsu.personalWallet.jwt.JwtTokenUtil;
import ru.vsu.personalWallet.service.UserService;

import java.time.Instant;

import static ru.vsu.personalWallet.util.Constant.REFRESH_TOKEN_HEADER;
import static ru.vsu.personalWallet.util.Constant.USER_ID_HEADER;

@RestController
public class AuthenticationController {

    private AuthenticationManager authenticationManager;
    private JwtTokenUtil jwtTokenUtil;
    private UserService userService;
    private UserDetailsService userDetailsService;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager,
                                    JwtTokenUtil jwtTokenUtil,
                                    UserService userService,
                                    UserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userService = userService;
        this.userDetailsService = userDetailsService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<TokenDto> login(@RequestBody LoginUserDto loginUser) throws AuthenticationException {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUser.getEmail(),
                        loginUser.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDto userDto = userService.findByEmail(loginUser.getEmail());
        final String authToken = jwtTokenUtil.generateAuthToken(userDto);
        final String refreshToken = jwtTokenUtil.generateRefreshToken(userDto);
        return new ResponseEntity<>(
                new TokenDto()
                        .setAuthorizationToken(authToken)
                        .setRefreshToken(refreshToken)
                        .setUserId(userDto.getId()),
                HttpStatus.OK);
    }

    @RequestMapping(value = "tokens/refresh", method = RequestMethod.GET)
    public ResponseEntity<?> refresh(@RequestHeader(USER_ID_HEADER) long userId,
                                     @RequestHeader(REFRESH_TOKEN_HEADER) String refreshToken) {

        UserDetails userDetails = null;
        try {
            userDetails = userDetailsService.loadUserByUsername(jwtTokenUtil.getUsernameFromToken(refreshToken));
        } catch (Exception e) {
            return generateUnauthorizedAnswer("Invalid refresh token");
        }

        UserDto userDto = userService.findById(userId);
        if (!jwtTokenUtil.validateRefreshToken(refreshToken, userDetails)) {
            return generateUnauthorizedAnswer("Refresh token expired");
        } else if (userDto == null) {
            HttpHeaders httpHeader = new HttpHeaders();
            httpHeader.setConnection("close");
            return new ResponseEntity<>(
                    new HttpResponseDto()
                            .setTimestamp(Instant.now().getEpochSecond())
                            .setStatus(404)
                            .setError("Not found")
                            .setMessage("User with id=" + userId + " not found")
                            .setPath("tokens/refresh"),
                    httpHeader,
                    HttpStatus.UNAUTHORIZED);
        } else if (!userDto.getEmail().equals(userDetails.getUsername())) {
            return generateUnauthorizedAnswer("Invalid refresh token");
        } else {
            return ResponseEntity.ok(jwtTokenUtil.refresh(userDto));
        }
    }

    private ResponseEntity generateUnauthorizedAnswer(String message) {
        HttpHeaders httpHeader = new HttpHeaders();
        httpHeader.setConnection("close");
        return new ResponseEntity<>(
                new HttpResponseDto()
                        .setTimestamp(Instant.now().getEpochSecond())
                        .setStatus(403)
                        .setError("Bad request")
                        .setMessage(message)
                        .setPath("tokens/refresh"),
                httpHeader,
                HttpStatus.UNAUTHORIZED);
    }

    @RequestMapping(value = "/tokens/auth/validate", method = RequestMethod.GET)
    public ResponseEntity validateAuthToken() {
        return ResponseEntity.noContent().build();
    }
}
