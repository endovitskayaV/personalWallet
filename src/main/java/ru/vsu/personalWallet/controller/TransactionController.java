package ru.vsu.personalWallet.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.vsu.personalWallet.domain.OperationType;
import ru.vsu.personalWallet.domain.dto.TransactionDto;
import ru.vsu.personalWallet.service.AimService;
import ru.vsu.personalWallet.service.TransactionService;

import java.util.Date;
import java.util.List;

@RequestMapping("/transaction")
@Controller
public class TransactionController {
    private TransactionService transactionService;
    private Gson gson;

    @Autowired
    TransactionController(TransactionService transactionService) {
        this.transactionService =transactionService;
        gson=new Gson();
    }

    @RequestMapping(value="delete", method = RequestMethod.DELETE)
    public boolean delete(long id){
      transactionService.delete(id);
      return true;
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public boolean add(String transactionGson){
        transactionService.save(gson.fromJson(transactionGson, TransactionDto.class));
        return true;
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public boolean edit(String transactionGson){
        transactionService.save(gson.fromJson(transactionGson, TransactionDto.class));
        return true;
    }

    @RequestMapping(value = "get", method = RequestMethod.POST)
    public String getById(long id){
        return gson.toJson(transactionService.findById(id));
    }

    @RequestMapping(value = "get", method = RequestMethod.POST)
    public String getByDate(Date date){
        return gson.toJson(transactionService.findByDate(date));
    }

    @RequestMapping(value = "get", method = RequestMethod.POST)
    public String getByMoneyValue(long moneyValue){
        return gson.toJson(transactionService.findByMoneyValue(moneyValue));
    }

    @RequestMapping(value = "get", method = RequestMethod.POST)
    public String getByOperationType(OperationType operationType){
        return gson.toJson(transactionService.findByOperationType(operationType));
    }
    //POST
    //herokuapp.....com/transaction/get
    //query parameters:
    //    String comment, compulsory
    //returns:


    //{INCOME, OUTCOME }
    @RequestMapping(value = "get", method = RequestMethod.POST)
    public String getByComment(String comment){
        return gson.toJson(transactionService.findByComment(comment));
    }

    @RequestMapping(value = "get", method = RequestMethod.POST)
    public String getByCategoryName(String name){
        return gson.toJson(transactionService.findByCategoryName(name));
    }
}


