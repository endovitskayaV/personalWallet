package ru.vsu.personalWallet.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vsu.personalWallet.domain.dto.ChequeDto;
import ru.vsu.personalWallet.service.ChequeService;
import ru.vsu.personalWallet.util.HttpResponse;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import static ru.vsu.personalWallet.util.Constant.USER_ID_HEADER;

@RequestMapping("/cheques")
@RestController
public class ChequeController {
    private ChequeService chequeService;

    @Autowired
    ChequeController(ChequeService chequeService) {
        this.chequeService=chequeService;
    }

    @RequestMapping(value = "delete", method = RequestMethod.DELETE)
    public ResponseEntity delete(long id, @RequestHeader(USER_ID_HEADER) long userId) {
        if (chequeService.delete(id, userId)) return ResponseEntity.noContent().build();
        else {
            HttpHeaders httpHeader = new HttpHeaders();
            httpHeader.setConnection("close");
            return new ResponseEntity<>(
                    new HttpResponse()
                            .setTimestamp(Instant.now().getEpochSecond())
                            .setStatus(404)
                            .setError("Not found")
                            .setMessage("Cheque with id=" + id + " not found")
                            .setPath("/cheques/delete"),
                    httpHeader,
                    HttpStatus.NOT_FOUND);
        }
    }


    @RequestMapping(value = "add", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity add(@RequestBody ChequeDto chequeDto,
                              @RequestHeader(USER_ID_HEADER) long userId) {
        chequeDto.setUserId(userId);
        return new ResponseEntity<>(chequeService.add(chequeDto),HttpStatus.OK);
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity edit(@RequestBody ChequeDto chequeDto,
                               @RequestHeader(USER_ID_HEADER) long userId) {
        chequeDto.setUserId(userId);
        if (chequeService.edit(chequeDto)) return ResponseEntity.noContent().build();
        else {
            HttpHeaders httpHeader = new HttpHeaders();
            httpHeader.setConnection("close");
            return new ResponseEntity<>(
                    new HttpResponse()
                            .setTimestamp(Instant.now().getEpochSecond())
                            .setStatus(404)
                            .setError("Not found")
                            .setMessage("Cheque with id=" + chequeDto.getId() + " not found")
                            .setPath("/cheques/edit"),
                    httpHeader,
                    HttpStatus.NOT_FOUND);
        }
    }

    private ResponseEntity getChequeDtoOrCode404(long id, long userId, String path) {
        if (chequeService.findByIdAndUserId(id, userId) == null) {
            HttpHeaders httpHeader = new HttpHeaders();
            httpHeader.setConnection("close");
            return new ResponseEntity<>(
                    new HttpResponse()
                            .setTimestamp(Instant.now().getEpochSecond())
                            .setStatus(404)
                            .setError("Not found")
                            .setMessage("Cheque with id=" + id + " not found")
                            .setPath("/cheques"+path),
                    httpHeader,
                    HttpStatus.NOT_FOUND);
        } else return new ResponseEntity<>(chequeService.findByIdAndUserId(id, userId),
                HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, params = {"id"})
    public ResponseEntity getById(long id, @RequestHeader(USER_ID_HEADER) long userId) {
        return getChequeDtoOrCode404(id, userId, "");
    }

    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public ResponseEntity edit(long id, @RequestHeader(USER_ID_HEADER) long userId) {
        return getChequeDtoOrCode404(id, userId, "/edit");
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getAll(@RequestHeader(USER_ID_HEADER) long userId) {
        List<ChequeDto> chequeDtoList = chequeService.findAllByUserId(userId);
        if (chequeDtoList.size() == 0)
            return ResponseEntity.noContent().build();
        return new ResponseEntity<>(chequeDtoList, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, params = {"name"})
    public ResponseEntity getByName(String name, @RequestHeader(USER_ID_HEADER) long userId) {
        List<ChequeDto> aimDtoList = chequeService.findByNameAndUserId(name, userId);
        if (aimDtoList.size() == 0)
            return ResponseEntity.noContent().build();
        return new ResponseEntity<>(aimDtoList, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, params = {"creationDate"})
    public ResponseEntity getByCreationDate(Timestamp creationDate,
                                            @RequestHeader(USER_ID_HEADER) long userId) {
        List<ChequeDto> aimDtoList = chequeService.findByCreationDateAndUserId(creationDate, userId);
        if (aimDtoList.size() == 0)
            return ResponseEntity.noContent().build();
        return new ResponseEntity<>(aimDtoList, HttpStatus.OK);
    }
}
