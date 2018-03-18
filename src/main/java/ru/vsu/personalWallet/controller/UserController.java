package ru.vsu.personalWallet.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vsu.personalWallet.domain.dto.UserDto;
import ru.vsu.personalWallet.service.UserService;
import ru.vsu.personalWallet.util.HttpResponse;

import java.time.Instant;

import static ru.vsu.personalWallet.util.Constant.USER_ID_HEADER;


@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/users/edit", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity edit(@RequestBody UserDto userDto, @RequestHeader(USER_ID_HEADER) long userId) {
        if (userId != userDto.getId()) {
            HttpHeaders httpHeader = new HttpHeaders();
            httpHeader.setConnection("close");
            return new ResponseEntity<>(
                    new HttpResponse()
                            .setTimestamp(Instant.now().getEpochSecond())
                            .setStatus(403)
                            .setError("Forbidden")
                            .setMessage("Forbidden access for user id=" + userDto.getId())
                            .setPath("/users/edit"),
                    httpHeader,
                    HttpStatus.FORBIDDEN);
        } else {
            if (userService.edit(userDto)) return ResponseEntity.noContent().build();
            else {
                HttpHeaders httpHeader = new HttpHeaders();
                httpHeader.setConnection("close");
                return new ResponseEntity<>(
                        new HttpResponse()
                                .setTimestamp(Instant.now().getEpochSecond())
                                .setStatus(404)
                                .setError("Not found")
                                .setMessage("User with id=" + userDto.getId() + " not found")
                                .setPath("/users/edit"),
                        httpHeader,
                        HttpStatus.NOT_FOUND);
            }
        }
    }

    private ResponseEntity getAimDtoOrCode404(long id) {
        if (userService.findById(id) == null) {
            HttpHeaders httpHeader = new HttpHeaders();
            httpHeader.setConnection("close");
            return new ResponseEntity<>(
                    new HttpResponse()
                            .setTimestamp(Instant.now().getEpochSecond())
                            .setStatus(404)
                            .setError("Not found")
                            .setMessage("User with id=" + id + " not found")
                            .setPath("/users/edit"),
                    httpHeader,
                    HttpStatus.NOT_FOUND);
        } else return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/users/edit", method = RequestMethod.GET)
    public ResponseEntity edit(@RequestHeader(USER_ID_HEADER) long userId) {
        return getAimDtoOrCode404(userId);
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity findById(@RequestHeader(USER_ID_HEADER) long userId) {
        return getAimDtoOrCode404(userId);
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ResponseEntity add(@RequestBody UserDto userDto) {
        UserDto userDtoFromDb = userService.add(userDto);
        if (userDtoFromDb == null) {
            HttpHeaders httpHeader = new HttpHeaders();
            httpHeader.setConnection("close");
            return new ResponseEntity<>(
                    new HttpResponse()
                            .setTimestamp(Instant.now().getEpochSecond())
                            .setStatus(400)
                            .setError("Bad request")
                            .setMessage("User with email=" + userDto.getEmail() + " already exists")
                            .setPath("/signup"),
                    httpHeader,
                    HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(userDtoFromDb, HttpStatus.OK);
    }
}