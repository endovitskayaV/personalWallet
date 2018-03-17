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
    public ResponseEntity delete(long id, @RequestHeader(USER_ID_HEADER) long userId) {
        if (aimService.delete(id, userId)) return ResponseEntity.ok().build();
        else {
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
        }
    }

    @RequestMapping(value = "add", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity add(@RequestBody AimDto aimDto, @RequestHeader(USER_ID_HEADER) long userId) {
        aimDto.setUserId(userId);
        return new ResponseEntity<>(aimService.add(aimDto),HttpStatus.OK);
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity edit(@RequestBody AimDto aimDto, @RequestHeader(USER_ID_HEADER) long userId) {
        if (aimService.edit(aimDto, userId)) return ResponseEntity.ok().build();
        else {
            HttpHeaders httpHeader = new HttpHeaders();
            httpHeader.setConnection("close");
            return new ResponseEntity<>(
                    new HttpResponse()
                            .setTimestamp(Instant.now().getEpochSecond())
                            .setStatus(404)
                            .setError("Not found")
                            .setMessage("Aim with id=" + aimDto.getId() + " not found")
                            .setPath("/aims"),
                    httpHeader,
                    HttpStatus.NOT_FOUND);
        }
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
    public ResponseEntity getById(long id, @RequestHeader(USER_ID_HEADER) long userId) {
        return getAimDtoOrCode404(id, userId);
    }

    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public ResponseEntity edit(long id, @RequestHeader(USER_ID_HEADER) long userId) {
        return getAimDtoOrCode404(id, userId);
    }


    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getAll(@RequestHeader(USER_ID_HEADER) long userId) {
        List<AimDto> aimDtoList = aimService.findAllByUserId(userId);
        if (aimDtoList.size() == 0)
            return ResponseEntity.noContent().build();
        return new ResponseEntity<>(aimDtoList, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, params = {"name"})
    public ResponseEntity getByName(String name, @RequestHeader(USER_ID_HEADER) long userId) {
        List<AimDto> aimDtoList = aimService.findByNameAndUserId(name, userId);
        if (aimDtoList.size() == 0)
            return ResponseEntity.noContent().build();
        return new ResponseEntity<>(aimDtoList, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, params = {"operationType"})
    public ResponseEntity getByOperationType(OperationType operationType,
                                             @RequestHeader(USER_ID_HEADER) long userId) {
        List<AimDto> aimDtoList = aimService.findByOperationTypeAndUserId(operationType, userId);
        if (aimDtoList.size() == 0)
            return ResponseEntity.noContent().build();
        return new ResponseEntity<>(aimDtoList, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, params = {"moneyValue"})
    public ResponseEntity getByMoneyValue(long moneyValue,
                                          @RequestHeader(USER_ID_HEADER) long userId) {
        List<AimDto> aimDtoList = aimService.findByMoneyValueAndUserId(moneyValue, userId);
        if (aimDtoList.size() == 0)
            return ResponseEntity.noContent().build();
        return new ResponseEntity<>(aimDtoList, HttpStatus.OK);
    }
}
