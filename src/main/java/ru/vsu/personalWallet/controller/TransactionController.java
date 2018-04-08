package ru.vsu.personalWallet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vsu.personalWallet.domain.OperationType;
import ru.vsu.personalWallet.dto.TransactionDto;
import ru.vsu.personalWallet.service.TransactionService;
import ru.vsu.personalWallet.dto.HttpResponseDto;

import java.time.Instant;
import java.util.List;

import static ru.vsu.personalWallet.util.Constant.USER_ID_HEADER;

@RequestMapping("/transactions")
@RestController
public class TransactionController {
    private TransactionService transactionService;

    @Autowired
    TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @RequestMapping(value = "delete", method = RequestMethod.DELETE)
    public ResponseEntity delete(long id, @RequestHeader(USER_ID_HEADER) long userId) {
        if (transactionService.delete(id, userId)) return ResponseEntity.noContent().build();
        else {
            HttpHeaders httpHeader = new HttpHeaders();
            httpHeader.setConnection("close");
            return new ResponseEntity<>(
                    new HttpResponseDto()
                            .setTimestamp(Instant.now().getEpochSecond())
                            .setStatus(404)
                            .setError("Not found")
                            .setMessage("Transaction with id=" + id + " not found")
                            .setPath("/transactions/delete"),
                    httpHeader,
                    HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "add", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity add(@RequestBody TransactionDto transactionDto,
                              @RequestHeader(USER_ID_HEADER) long userId) {
        transactionDto.setUserId(userId);
        TransactionDto transactionDtoFromDb=transactionService.add(transactionDto);
        if (transactionDtoFromDb==null){
            HttpHeaders httpHeader = new HttpHeaders();
            httpHeader.setConnection("close");
            return new ResponseEntity<>(
                    new HttpResponseDto()
                            .setTimestamp(Instant.now().getEpochSecond())
                            .setStatus(404)
                            .setError("Not found")
                            .setMessage("Category with id=" + transactionDto.getCategoryId()
                                    + " not found. Cannot add transaction")
                            .setPath("/transactions/add"),
                    httpHeader,
                    HttpStatus.NOT_FOUND);

        }
       else return new ResponseEntity<>(transactionDtoFromDb,HttpStatus.OK);
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity edit(@RequestBody TransactionDto transactionDto,
                               @RequestHeader(USER_ID_HEADER) long userId) {
        transactionDto.setUserId(userId);
        if (transactionService.edit(transactionDto)) return ResponseEntity.noContent().build();
        else {
            HttpHeaders httpHeader = new HttpHeaders();
            httpHeader.setConnection("close");
            return new ResponseEntity<>(
                    new HttpResponseDto()
                            .setTimestamp(Instant.now().getEpochSecond())
                            .setStatus(404)
                            .setError("Not found")
                            .setMessage("Transaction with id=" + transactionDto.getId() + " not found")
                            .setPath("/transactions/edit"),
                    httpHeader,
                    HttpStatus.NOT_FOUND);
        }
    }

    private ResponseEntity getTransactionDtoOrCode404(long id, long userId, String path) {
        if (transactionService.findByIdAndUserId(id, userId) == null) {
            HttpHeaders httpHeader = new HttpHeaders();
            httpHeader.setConnection("close");
            return new ResponseEntity<>(
                    new HttpResponseDto()
                            .setTimestamp(Instant.now().getEpochSecond())
                            .setStatus(404)
                            .setError("Not found")
                            .setMessage("Transaction with id=" + id + " not found")
                            .setPath("/transactions"+path),
                    httpHeader,
                    HttpStatus.NOT_FOUND);
        } else return new ResponseEntity<>(transactionService.findByIdAndUserId(id, userId),
                HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, params = {"id"})
    public ResponseEntity getById(long id, @RequestHeader(USER_ID_HEADER) long userId) {
        return getTransactionDtoOrCode404(id, userId, "");
    }

    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public ResponseEntity edit(long id, @RequestHeader(USER_ID_HEADER) long userId) {
        return getTransactionDtoOrCode404(id, userId, "/edit");
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getAll(@RequestHeader(USER_ID_HEADER) long userId) {
        List<TransactionDto> transactionDtoList = transactionService.findAllByUserId(userId);
        if (transactionDtoList.size() == 0)
            return ResponseEntity.noContent().build();
        return new ResponseEntity<>(transactionDtoList, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, params = {"operationType"})
    public ResponseEntity getByMoneyValue(OperationType operationType,
                                          @RequestHeader(USER_ID_HEADER) long userId) {
        List<TransactionDto> transactionDtoList = transactionService
                .findByOperationTypeAndUserId(operationType, userId);
        if (transactionDtoList.size() == 0)
            return ResponseEntity.noContent().build();
        return new ResponseEntity<>(transactionDtoList, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, params = {"moneyValue"})
    public ResponseEntity getByMoneyValue(long moneyValue,
                                          @RequestHeader(USER_ID_HEADER) long userId) {
        List<TransactionDto> transactionDtoList = transactionService
                .findByMoneyValueAndUserId(moneyValue, userId);
        if (transactionDtoList.size() == 0)
            return ResponseEntity.noContent().build();
        return new ResponseEntity<>(transactionDtoList, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, params = {"categoryId"})
    public ResponseEntity getByCategoryId(long categoryId,
                                          @RequestHeader(USER_ID_HEADER) long userId) {
        List<TransactionDto> transactionDtoList = transactionService
                .findByCategoryIdAndUserId(categoryId, userId);
        if (transactionDtoList.size() == 0)
            return ResponseEntity.noContent().build();
        return new ResponseEntity<>(transactionDtoList, HttpStatus.OK);
    }
}


