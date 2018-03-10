package ru.vsu.personalWallet.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.vsu.personalWallet.domain.OperationType;
import ru.vsu.personalWallet.domain.dto.AimDto;
import ru.vsu.personalWallet.service.AimService;

import java.util.Date;

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

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public boolean add(AimDto aimDto) {
        return aimService.add(aimDto);
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public boolean edit(AimDto aimDto) {
        return aimService.save(aimDto);
    }

    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String edit(long id) {
        return gson.toJson(aimService.findById(id));
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getAll() {
        return gson.toJson(aimService.findAll());
    }

    @RequestMapping(method = RequestMethod.GET, params = {"id"})
    public String getById(long id) {
        return gson.toJson(aimService.findById(id));
    }

    @RequestMapping(method = RequestMethod.GET, params = {"name"})
    public String getByName(String name) {
        return gson.toJson(aimService.findByName(name));
    }

    @RequestMapping(method = RequestMethod.GET, params = {"operationType"})
    public String getByOperationType(OperationType operationType) {
        return gson.toJson(aimService.findByOperationType(operationType));
    }

    @RequestMapping(method = RequestMethod.GET, params = {"date"})
    public String getByDate(Date date) {
        return gson.toJson(aimService.findByDate(date));
    }
}
