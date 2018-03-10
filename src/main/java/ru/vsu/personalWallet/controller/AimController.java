package ru.vsu.personalWallet.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.vsu.personalWallet.domain.OperationType;
import ru.vsu.personalWallet.domain.dto.AimDto;
import ru.vsu.personalWallet.service.AimService;

import javax.servlet.http.HttpServletResponseWrapper;
import java.util.Date;
import java.util.List;

@RequestMapping("/aims")
@RestController
public class AimController {
    private AimService aimService;
    private Gson gson;

    @Autowired
    AimController(AimService aimService) {
        this.aimService = aimService;
        gson = new Gson();
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
    public String getById(long id) {
        if (aimService.findById(id) == null)
            return Integer.toString(HttpServletResponseWrapper.SC_NOT_FOUND);
        else return gson.toJson(aimService.findById(id));
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
