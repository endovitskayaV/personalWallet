package ru.vsu.personalWallet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vsu.personalWallet.domain.OperationType;
import ru.vsu.personalWallet.domain.dto.AimDto;
import ru.vsu.personalWallet.domain.dto.UserDto;
import ru.vsu.personalWallet.service.AimService;
import ru.vsu.personalWallet.util.HttpResponse;

import java.time.Instant;
import java.util.List;

import static ru.vsu.personalWallet.util.Constant.USER_ID_HEADER;

@RequestMapping("/aims")
@RestController
public class AimController {
    private AimService aimService;

    @Autowired
    AimController(AimService aimService) {
        this.aimService = aimService;
    }

    @RequestMapping(value = "delete", method = RequestMethod.DELETE)
    public boolean delete(long id, @RequestHeader(USER_ID_HEADER) long userId) {
        return aimService.delete(id, userId);
    }

    @RequestMapping(value = "add", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public AimDto add(@RequestBody AimDto aimDto, @RequestHeader(USER_ID_HEADER) long userId) {
        aimDto.setUserId(userId);
        return aimService.add(aimDto);
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public boolean edit(@RequestBody AimDto aimDto, @RequestHeader(USER_ID_HEADER) long userId) {
        return aimService.edit(aimDto, userId);
    }

    private ResponseEntity getAimDtoOrCode404(long id, long userId) {
        if (aimService.findByIdAndUserId(id, userId) == null) {
            HttpHeaders httpHeader = new HttpHeaders();
            httpHeader.setConnection("close");
            return new ResponseEntity<>(
                    new HttpResponse()
                            .setTimestamp(Instant.now().getEpochSecond())
                            .setStatus(404)
                            .setError("Not found")
                            .setMessage("Aim with id=" + id + " not found")
                            .setPath("/aims"),
                    httpHeader,
                    HttpStatus.NOT_FOUND);
        } else return new ResponseEntity<>(aimService.findByIdAndUserId(id, userId),
                HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.GET, params = {"id"})
    public ResponseEntity getById(long id,@RequestHeader(USER_ID_HEADER) long userId) {
        return getAimDtoOrCode404(id, userId);
    }

    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public ResponseEntity edit(long id, @RequestHeader(USER_ID_HEADER) long userId) {
        return getAimDtoOrCode404(id, userId);
    }


    @RequestMapping(method = RequestMethod.GET)
    public List<AimDto> getAll(@RequestHeader(USER_ID_HEADER) long userId) {
        return aimService.findAllByUserId(userId);
    }

    @RequestMapping(method = RequestMethod.GET, params = {"name"})
    public List<AimDto> getByName(String name, @RequestHeader(USER_ID_HEADER) long userId) {
        return aimService.findByNameAndUserId(name, userId);
    }

    @RequestMapping(method = RequestMethod.GET, params = {"operationType"})
    public List<AimDto> getByOperationType(OperationType operationType,
                                           @RequestHeader(USER_ID_HEADER) long userId) {
        return aimService.findByOperationTypeAndUserId(operationType, userId);
    }

    @RequestMapping(method = RequestMethod.GET, params = {"moneyValue"})
    public List<AimDto> getByMoneyValue(long moneyValue,
                                        @RequestHeader(USER_ID_HEADER) long userId) {
        return aimService.findByMoneyValueAndUserId(moneyValue,userId);
    }
}
