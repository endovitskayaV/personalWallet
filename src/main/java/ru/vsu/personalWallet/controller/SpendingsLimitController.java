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
import ru.vsu.personalWallet.domain.dto.SpendingsLimitDto;
import ru.vsu.personalWallet.service.SpendingsLimitService;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@RequestMapping("/spendingsLimits")
@RestController
public class SpendingsLimitController {
    private SpendingsLimitService spendingsLimitService;

    @Autowired
    SpendingsLimitController(SpendingsLimitService spendingsLimitService) {
        this.spendingsLimitService = spendingsLimitService;
    }

    @RequestMapping(value = "delete", method = RequestMethod.DELETE)
    public boolean delete(String id) {
        return spendingsLimitService.delete(id);
    }

    @RequestMapping(value = "add", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public boolean add(@RequestBody SpendingsLimitDto spendingsLimitDto) {
        return spendingsLimitService.add(spendingsLimitDto);
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public boolean edit(@RequestBody SpendingsLimitDto spendingsLimitDto) {
        return spendingsLimitService.edit(spendingsLimitDto);
    }

    private ResponseEntity getAimDtoOrCode404(String id) {
        if (spendingsLimitService.findById(id) == null) {
            HttpHeaders httpHeader = new HttpHeaders();
            httpHeader.setConnection("close");
            return new ResponseEntity<>(
                    new HttpResponse()
                            .setTimestamp(Instant.now().getEpochSecond())
                            .setStatus(404)
                            .setError("Not found")
                            .setMessage("SpendingsLimit with id=" + id + " not found")
                            .setPath("/aims"),
                    httpHeader,
                    HttpStatus.NOT_FOUND);
        } else return new ResponseEntity<>(spendingsLimitService.findById(id), HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.GET, params = {"id"})
    public ResponseEntity getById(String id) {
        return getAimDtoOrCode404(id);
    }

    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public ResponseEntity edit(String id) {
        return getAimDtoOrCode404(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<SpendingsLimitDto> getAll() {
        return spendingsLimitService.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, params = {"creationDate"})
    public List<SpendingsLimitDto> getByCreationDate(Timestamp creationDate) {
        return spendingsLimitService.findByCreationDate(creationDate);
    }

    @RequestMapping(method = RequestMethod.GET, params = {"maxSum"})
    public List<SpendingsLimitDto> getByMaxSum(long maxSum) {
        return spendingsLimitService.findByMaxSum(maxSum);
    }
}
