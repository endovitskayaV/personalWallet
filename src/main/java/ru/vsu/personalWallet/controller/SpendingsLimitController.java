package ru.vsu.personalWallet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vsu.personalWallet.dto.SpendingsLimitDto;
import ru.vsu.personalWallet.service.SpendingsLimitService;
import ru.vsu.personalWallet.dto.HttpResponseDto;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import static ru.vsu.personalWallet.util.Constant.USER_ID_HEADER;

@RequestMapping("/spendingsLimits")
@RestController
public class SpendingsLimitController {
    private SpendingsLimitService spendingsLimitService;

    @Autowired
    SpendingsLimitController(SpendingsLimitService spendingsLimitService) {
        this.spendingsLimitService = spendingsLimitService;
    }

    @RequestMapping(value = "delete", method = RequestMethod.DELETE)
    public ResponseEntity delete(long id, @RequestHeader(USER_ID_HEADER) long userId) {
        if (spendingsLimitService.delete(id, userId)) return ResponseEntity.noContent().build();
        else {
            HttpHeaders httpHeader = new HttpHeaders();
            httpHeader.setConnection("close");
            return new ResponseEntity<>(
                    new HttpResponseDto()
                            .setTimestamp(Instant.now().getEpochSecond())
                            .setStatus(404)
                            .setError("Not found")
                            .setMessage("Spendings limit with id=" + id + " not found")
                            .setPath("/spendingsLimit/delete"),
                    httpHeader,
                    HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "add", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity add(@RequestBody SpendingsLimitDto spendingsLimitDto,
                              @RequestHeader(USER_ID_HEADER) long userId) {
        spendingsLimitDto.setUserId(userId);
        return new ResponseEntity<>(spendingsLimitService.add(spendingsLimitDto),HttpStatus.OK);
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity edit(@RequestBody SpendingsLimitDto spendingsLimitDto,
                               @RequestHeader(USER_ID_HEADER) long userId) {
        spendingsLimitDto.setUserId(userId);
        if (spendingsLimitService.edit(spendingsLimitDto))
            return ResponseEntity.noContent().build();
        else {
            HttpHeaders httpHeader = new HttpHeaders();
            httpHeader.setConnection("close");
            return new ResponseEntity<>(
                    new HttpResponseDto()
                            .setTimestamp(Instant.now().getEpochSecond())
                            .setStatus(404)
                            .setError("Not found")
                            .setMessage("Spendings limit with id=" + spendingsLimitDto.getId()
                                    + " not found")
                            .setPath("/spendingsLimits/edit"),
                    httpHeader,
                    HttpStatus.NOT_FOUND);
        }
    }

    private ResponseEntity getSpendingsLimitDtoOrCode404(long id, long userId, String path) {
        if (spendingsLimitService.findByIdAndUserId(id, userId) == null) {
            HttpHeaders httpHeader = new HttpHeaders();
            httpHeader.setConnection("close");
            return new ResponseEntity<>(
                    new HttpResponseDto()
                            .setTimestamp(Instant.now().getEpochSecond())
                            .setStatus(404)
                            .setError("Not found")
                            .setMessage("Spendings limit with id=" + id + " not found")
                            .setPath("/spendingsLimit"+path),
                    httpHeader,
                    HttpStatus.NOT_FOUND);
        } else return new ResponseEntity<>(spendingsLimitService.findByIdAndUserId(id, userId),
                HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, params = {"id"})
    public ResponseEntity getById(long id, @RequestHeader(USER_ID_HEADER) long userId) {
        return getSpendingsLimitDtoOrCode404(id, userId, "");
    }

    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public ResponseEntity edit(long id, @RequestHeader(USER_ID_HEADER) long userId) {
        return getSpendingsLimitDtoOrCode404(id, userId, "/edit");
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getAll(@RequestHeader(USER_ID_HEADER) long userId) {
        List<SpendingsLimitDto> spendingsLimitDtoList = spendingsLimitService.findAllByUserId(userId);
        if (spendingsLimitDtoList.size() == 0)
            return ResponseEntity.noContent().build();
        return new ResponseEntity<>(spendingsLimitDtoList, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, params = {"creationDate"})
    public ResponseEntity getByCreationDate(Timestamp creationDate,
                                            @RequestHeader(USER_ID_HEADER) long userId) {
        List<SpendingsLimitDto> aimDtoList = spendingsLimitService
                .findByCreationDateAndUserId(creationDate, userId);
        if (aimDtoList.size() == 0)
            return ResponseEntity.noContent().build();
        return new ResponseEntity<>(aimDtoList, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, params = {"maxSum"})
    public ResponseEntity getByCreationDate(long maxSum,
                                            @RequestHeader(USER_ID_HEADER) long userId) {
        List<SpendingsLimitDto> aimDtoList = spendingsLimitService
                .findByMaxSumAndUserId(maxSum, userId);
        if (aimDtoList.size() == 0)
            return ResponseEntity.noContent().build();
        return new ResponseEntity<>(aimDtoList, HttpStatus.OK);
    }
}
