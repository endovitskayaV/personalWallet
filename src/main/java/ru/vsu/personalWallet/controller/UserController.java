package ru.vsu.personalWallet.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vsu.personalWallet.HttpResponse.HttpResponse;
import ru.vsu.personalWallet.domain.dto.UserDto;
import ru.vsu.personalWallet.service.UserService;

import java.time.Instant;
import java.util.List;

@RequestMapping("/users")
@RestController
public class UserController {
    private UserService userService;

    @Autowired
    UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "delete", method = RequestMethod.DELETE)
    public boolean delete(String id) {
        return userService.delete(id);
    }

    @RequestMapping(value = "add", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public boolean add(@RequestBody UserDto userDto) {
        return userService.add(userDto);
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public boolean edit(@RequestBody UserDto userDto) {
        return userService.edit(userDto);
    }

    private ResponseEntity getUserDtoOrCode404(String id) {
        if (userService.findById(id) == null) {
            HttpHeaders httpHeader = new HttpHeaders();
            httpHeader.setConnection("close");
            return new ResponseEntity<>(
                    new HttpResponse()
                            .setTimestamp(Instant.now().getEpochSecond())
                            .setStatus(404)
                            .setError("Not found")
                            .setMessage("User with id=" + id + " not found")
                            .setPath("/users"),
                    httpHeader,
                    HttpStatus.NOT_FOUND);
        } else return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, params = {"id"})
    public ResponseEntity getById(String id) {
        return getUserDtoOrCode404(id);
    }

    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public ResponseEntity edit(String id) {
        return getUserDtoOrCode404(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<UserDto> getAll() {
        return userService.findAll();
    }
}