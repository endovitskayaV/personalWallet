package ru.vsu.personalWallet.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.vsu.personalWallet.HttpResponse.HttpResponse;
import ru.vsu.personalWallet.domain.dto.UserDto;
import ru.vsu.personalWallet.domain.entity.UserEntity;
import ru.vsu.personalWallet.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@RestController

public class AccessController {
    private UserService userService;
    private SecurityService securityService;

    @Autowired
    AccessController(UserService userService, SecurityService securityService) {
        this.userService = userService;
        this.securityService = securityService;
    }


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ResponseEntity getById(@RequestBody UserDto userDto) {
        try {
            String token = securityService.login(userDto.getEmail(), userDto.getPassword());
            return new ResponseEntity<>(token, HttpStatus.OK);
            // return ResponseEntity.ok(token);
        } catch (Exception e) {
            HttpHeaders httpHeader = new HttpHeaders();
            httpHeader.setConnection("close");
            return new ResponseEntity<>(
                    new HttpResponse()
                            .setTimestamp(Instant.now().getEpochSecond())
                            .setStatus(403) //?
                            .setError("Bad request")
                            .setMessage("Wrong email or password")
                            .setPath("/login"),
                    httpHeader,
                    HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)//, params = {"email", "password"})
    public ResponseEntity<?> logout() {
        try {
            securityService.logout();
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            HttpHeaders httpHeader = new HttpHeaders();
            httpHeader.setConnection("close");
            return new ResponseEntity<>(
                    new HttpResponse()
                            .setTimestamp(Instant.now().getEpochSecond())
                            .setStatus(403) //?
                            .setError("Bad request")
                            .setMessage( e.getMessage())
                            .setPath("/login"),
                    httpHeader,
                    HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/refresh", method = RequestMethod.GET)
    public ResponseEntity<?> authenticationRequest(HttpServletRequest request) {
        try {
            String token = request.getHeader(TokenUtils.tokenHeader);
            String newToken = securityService.refresh(token);
            return ResponseEntity.ok(newToken);
        } catch (Exception e) {
            return new ResponseEntity<Object>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }


}
