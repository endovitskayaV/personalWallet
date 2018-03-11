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
import ru.vsu.personalWallet.domain.OperationType;
import ru.vsu.personalWallet.domain.dto.TransactionDto;
import ru.vsu.personalWallet.service.TransactionService;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@RequestMapping("/transactions")
@RestController
public class TransactionController {
    private TransactionService transactionService;

    @Autowired
    TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @RequestMapping(value = "delete", method = RequestMethod.DELETE)
    public boolean delete(String id) {
        transactionService.delete(id);
        return true;
    }

    @RequestMapping(value = "add", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public boolean add(@RequestBody TransactionDto transactionDto) {
        return transactionService.add(transactionDto);
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public boolean edit(@RequestBody TransactionDto transactionDto) {
        return transactionService.edit(transactionDto);
    }

    private ResponseEntity getTransactionDtoOrCode404(String id) {
        if (transactionService.findById(id) == null) {
            HttpHeaders httpHeader = new HttpHeaders();
            httpHeader.setConnection("close");
            return new ResponseEntity<>(
                    new HttpResponse()
                            .setTimestamp(Instant.now().getEpochSecond())
                            .setStatus(404)
                            .setError("Not found")
                            .setMessage("Transaction with id=" + id + " not found")
                            .setPath("/transactions"),
                    httpHeader,
                    HttpStatus.NOT_FOUND);
        } else return new ResponseEntity<>(transactionService.findById(id), HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.GET, params = {"id"})
    public ResponseEntity getById(String id) {
        return getTransactionDtoOrCode404(id);
    }

    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public ResponseEntity edit(String id) {
        return getTransactionDtoOrCode404(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<TransactionDto> getAll() {
        return transactionService.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, params = {"operationType"})
    public List<TransactionDto> getByOperationType(OperationType operationType) {
        return transactionService.findByOperationType(operationType);
    }

    @RequestMapping(method = RequestMethod.GET, params = {"creationDate"})
    public List<TransactionDto> getByCreationDate(Timestamp creationDate) {
        return transactionService.findByCreationDate(creationDate);
    }

    @RequestMapping(method = RequestMethod.GET, params = {"moneyValue"})
    public List<TransactionDto> getByMoneyValue(long moneyValue) {
        return transactionService.findByMoneyValue(moneyValue);
    }

    @RequestMapping(method = RequestMethod.GET, params = {"comment"})
    public List<TransactionDto> getByComment(String comment) {
        return transactionService.findByComment(comment);
    }

    @RequestMapping(method = RequestMethod.GET, params = {"categoryName"})
    public List<TransactionDto> getByCategoryName(String categoryName) {
        return transactionService.findByCategoryName(categoryName);
    }
}


