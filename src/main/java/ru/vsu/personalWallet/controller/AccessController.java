package ru.vsu.personalWallet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.vsu.personalWallet.HttpResponse.HttpResponse;
import ru.vsu.personalWallet.domain.dto.UserDto;
import ru.vsu.personalWallet.domain.entity.UserEntity;
import ru.vsu.personalWallet.service.UserService;

import java.time.Instant;

@RestController

public class AccessController {
    private UserService userService;

    @Autowired
    AccessController(UserService userService) {
        this.userService = userService;
    }


    @RequestMapping(value = "login", method = RequestMethod.GET, params = {"email", "password"})
    public ResponseEntity getById(String email, String password) {
        if (userService.findByEmailAndPassword(email, password) == null) {
            HttpHeaders httpHeader = new HttpHeaders();
            httpHeader.setConnection("close");
            return new ResponseEntity<>(
                    new HttpResponse()
                            .setTimestamp(Instant.now().getEpochSecond())
                            .setStatus(404)
                            .setError("Not found")
                            .setMessage("User not found")
                            .setPath("/login"),
                    httpHeader,
                    HttpStatus.NOT_FOUND);
        } else return new ResponseEntity<>(HttpStatus.OK);
    }

}
