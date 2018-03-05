package ru.vsu.personalWallet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vsu.personalWallet.domain.repository.AimRepository;

@Service
public class AimService {
    private AimRepository aimRepository;

    @Autowired
    public AimService(AimRepository aimRepository) {
        this.aimRepository=aimRepository;
    }

    public void delete(long aimId) {
       aimRepository.delete(aimId);

    }
    //TODO: finish class
}
