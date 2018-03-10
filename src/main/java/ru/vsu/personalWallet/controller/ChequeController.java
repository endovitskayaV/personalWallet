package ru.vsu.personalWallet.controller;


import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.vsu.personalWallet.domain.dto.ChequeDto;
import ru.vsu.personalWallet.service.ChequeService;

import java.util.Date;

@RequestMapping("/cheques")
@Controller
public class ChequeController {
    private ChequeService chequeService;
    private Gson gson;

    @Autowired
    ChequeController(ChequeService chequeService) {
        this.chequeService=chequeService;
        gson = new Gson();
    }

    @RequestMapping(value="delete", method = RequestMethod.DELETE)
    public boolean delete(long id){
       chequeService.delete(id);
        return true;
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public boolean add(ChequeDto chequeDto){
       chequeService.save(chequeDto);
        return true;
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public boolean edit(ChequeDto chequeDto){
      chequeService.save(chequeDto);
        return true;
    }

    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String edit(long id){
        return gson.toJson( chequeService.findById(id));
    }

    @RequestMapping(method = RequestMethod.POST)
    public String getAll(){
        return gson.toJson(chequeService.findAll());
    }

    @RequestMapping(method = RequestMethod.POST)
    public String getById(long id){
        return gson.toJson( chequeService.findById(id));
    }


    @RequestMapping(method = RequestMethod.POST)
    public String getName(String name){
        return gson.toJson(chequeService.findByName(name));
    }

    @RequestMapping(method = RequestMethod.POST)
    public String getByDate(Date date){
        return gson.toJson( chequeService.findByDate(date));
    }
}
