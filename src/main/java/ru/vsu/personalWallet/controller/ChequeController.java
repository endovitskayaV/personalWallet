package ru.vsu.personalWallet.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.vsu.personalWallet.HttpResponse.HttpResponse;
import ru.vsu.personalWallet.domain.dto.ChequeDto;
import ru.vsu.personalWallet.service.ChequeService;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@RequestMapping("/cheques")
@RestController
public class ChequeController {
    private ChequeService chequeService;

    @Autowired
    ChequeController(ChequeService chequeService) {
        this.chequeService=chequeService;
    }

    @RequestMapping(value = "delete", method = RequestMethod.DELETE)
    public boolean delete(String id) {
        return chequeService.delete(id);
    }

    @RequestMapping(value = "add", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public boolean add(@RequestBody ChequeDto chequeDto) {
        return chequeService.add(chequeDto);
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public boolean edit(@RequestBody ChequeDto chequeDto) {
        return chequeService.edit(chequeDto);
    }

    private ResponseEntity getCategoryDtoOrCode404(String id) {
        if (chequeService.findById(id) == null) {
            HttpHeaders httpHeader = new HttpHeaders();
            httpHeader.setConnection("close");
            return new ResponseEntity<>(
                    new HttpResponse()
                            .setTimestamp(Instant.now().getEpochSecond())
                            .setStatus(404)
                            .setError("Not found")
                            .setMessage("Cheque with id=" + id + " not found")
                            .setPath("/categories"),
                    httpHeader,
                    HttpStatus.NOT_FOUND);
        } else return new ResponseEntity<>(chequeService.findById(id), HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.GET, params = {"id"})
    public ResponseEntity getById(String id) {
        return getCategoryDtoOrCode404(id);
    }

    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public ResponseEntity edit(String id) {
        return getCategoryDtoOrCode404(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<ChequeDto> getAll() {
        return chequeService.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, params = {"name"})
    public List<ChequeDto> getByName(String name) {
        return chequeService.findByName(name);
    }

    @RequestMapping(method = RequestMethod.GET, params = {"creationDate"})
    public List<ChequeDto> getByCreationDate(Timestamp creationDate) {
        return chequeService.findByCreationDate(creationDate);
    }
}
