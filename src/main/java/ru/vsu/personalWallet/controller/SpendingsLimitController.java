package ru.vsu.personalWallet.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.vsu.personalWallet.domain.dto.SpendingsLimitDto;
import ru.vsu.personalWallet.service.SpendingsLimitService;

import java.util.Date;

@RequestMapping("/spendingsLimits")
@Controller
public class  SpendingsLimitController {
    private SpendingsLimitService spendingsLimitService;
    private Gson gson;

    @Autowired
    SpendingsLimitController(SpendingsLimitService spendingsLimitService) {
        this.spendingsLimitService =spendingsLimitService;
        gson = new Gson();
    }

    @RequestMapping(value="delete", method = RequestMethod.DELETE)
    public boolean delete(long id){
        spendingsLimitService.delete(id);
        return true;
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public boolean add(SpendingsLimitDto spendingsLimitDto){
        spendingsLimitService.save(spendingsLimitDto);
        return true;
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public boolean edit(SpendingsLimitDto spendingsLimitDto){
        spendingsLimitService.save(spendingsLimitDto);
        return true;
    }

    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String edit(long id){
        return gson.toJson( spendingsLimitService.findById(id));
    }

    @RequestMapping(method = RequestMethod.POST)
    public String getAll(){
        return gson.toJson(spendingsLimitService.findAll());
    }

    @RequestMapping(method = RequestMethod.POST)
    public String getById(long id){
        return gson.toJson( spendingsLimitService.findById(id));
    }


    @RequestMapping(method = RequestMethod.POST)
    public String getByMaxSum(long maxSum){
        return gson.toJson( spendingsLimitService.findByMaxSum(maxSum));
    }

    @RequestMapping(method = RequestMethod.POST)
    public String getByDate(Date date){
        return gson.toJson( spendingsLimitService.findByDate(date));
    }
}
