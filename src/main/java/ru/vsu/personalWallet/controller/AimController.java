package ru.vsu.personalWallet.controller;

import com.google.gson.Gson;
import com.sun.javaws.exceptions.ErrorCodeResponseException;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vsu.personalWallet.HttpResponse.HttpResponse;
import ru.vsu.personalWallet.domain.OperationType;
import ru.vsu.personalWallet.domain.dto.AimDto;
import ru.vsu.personalWallet.service.AimService;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@RequestMapping("/aims")
@RestController
public class AimController {
    private AimService aimService;

    @Autowired
    AimController(AimService aimService) {
        this.aimService = aimService;
    }

    @RequestMapping(value = "delete", method = RequestMethod.DELETE)
    public boolean delete(long id) {
        return aimService.delete(id);
    }

    @RequestMapping(value = "add", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public boolean add(@RequestBody AimDto aimDto) {
        return aimService.add(aimDto);
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public boolean edit(@RequestBody AimDto aimDto) {
        return aimService.edit(aimDto);
    }

    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public AimDto edit(long id) {
        return aimService.findById(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<AimDto> getAll() {
        return aimService.findAll();
    }


    @RequestMapping(method = RequestMethod.GET, params = {"id"})
    public ResponseEntity getById1(long id) {
        if (aimService.findById(id) == null) {
//         return gson.toJson(ResponseEntity.status(HttpStatus.NOT_FOUND).
//        return ResponseEntity.notFound().build();
//        ResponseEntity.status(HttpStatus.NOT_FOUND).body(, HttpStatus.NOT_FOUND, )//ResponseEntity.notFound().build();
//        throw new NotFoundException("bla");
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
        else return new ResponseEntity<>(aimService.findById(id), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, params = {"name"})
    public String getByName(String name) {
        //return  gson.toJson(aimService.findByName(name));
        return Integer.toString(HttpServletResponseWrapper.SC_NOT_FOUND);
    }

    @RequestMapping(method = RequestMethod.GET, params = {"operationType"})
    public List<AimDto> getByOperationType(OperationType operationType) {
        return aimService.findByOperationType(operationType);
    }

    @RequestMapping(method = RequestMethod.GET, params = {"date"})
    public List<AimDto> getByDate(Date date) {
        return aimService.findByDate(date);
    }
}
