package ru.vsu.personalWallet.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.vsu.personalWallet.domain.repository.AimRepository;
import ru.vsu.personalWallet.service.AimService;

@RequestMapping("/aim")
@Controller
public class AimController {
    private AimService aimService;
    private Gson gson;

    @Autowired
    AimController(AimService aimService) {
        this.aimService =aimService;
    }



}
