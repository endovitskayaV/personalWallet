package ru.vsu.personalWallet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.vsu.personalWallet.service.AimService;
import ru.vsu.personalWallet.service.TransactionService;

@RequestMapping("/transaction")
@Controller
public class TransactionController {
    private TransactionService transactionService;

    @Autowired
    TransactionController(TransactionService transactionService) {
        this.transactionService =transactionService;
    }
}
