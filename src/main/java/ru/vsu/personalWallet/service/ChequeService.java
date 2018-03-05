package ru.vsu.personalWallet.service;

import org.springframework.stereotype.Service;
import ru.vsu.personalWallet.domain.dto.ChequeDto;
import ru.vsu.personalWallet.domain.repository.ChequeRepository;
import ru.vsu.personalWallet.domain.util.DtoToEntity;
import ru.vsu.personalWallet.domain.util.EntityToDto;

import java.util.Date;

@Service
public class ChequeService {
    private ChequeRepository chequeRepository;

    ChequeService(ChequeRepository chequeRepository) {
        this.chequeRepository = chequeRepository;
    }

    public void delete(long aimId) {
        chequeRepository.delete(aimId);

    }
    public void save(ChequeDto chequeDto){
        chequeRepository.save(DtoToEntity.toEntity(chequeDto));
    }

    public ChequeDto findById(long id){
        return EntityToDto.toDto(chequeRepository.findOne(id));
    }

    public ChequeDto findByName(String name){
        return EntityToDto.toDto(chequeRepository.findChequeEntityByName(name));
    }

    public ChequeDto findByDate(Date date){
        return EntityToDto.toDto(chequeRepository.findChequeEntityByDate(date));
    }

}
