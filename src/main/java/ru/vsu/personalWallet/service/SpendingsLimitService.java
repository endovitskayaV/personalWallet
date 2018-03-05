package ru.vsu.personalWallet.service;

import org.springframework.stereotype.Service;
import ru.vsu.personalWallet.domain.dto.SpendingsLimitDto;
import ru.vsu.personalWallet.domain.repository.SpendingsLimitRepository;
import ru.vsu.personalWallet.domain.util.DtoToEntity;
import ru.vsu.personalWallet.domain.util.EntityToDto;

import java.util.Date;

@Service
public class SpendingsLimitService {
    private SpendingsLimitRepository spendingsLimitRepository;

    SpendingsLimitService (SpendingsLimitRepository spendingsLimitRepository) {
        this.spendingsLimitRepository = spendingsLimitRepository;
    }

    public void delete(long aimId) {
       spendingsLimitRepository.delete(aimId);

    }
    public void save(SpendingsLimitDto spendingsLimitDto){
       spendingsLimitRepository.save(DtoToEntity.toEntity(spendingsLimitDto));
    }

    public SpendingsLimitDto findById(long id){
        return EntityToDto.toDto(spendingsLimitRepository.findOne(id));
    }

    public SpendingsLimitDto findByMaxSum(long maxSum){
        return EntityToDto.toDto(spendingsLimitRepository.findSpendingsLimitEntityByMaxSum(maxSum));
    }

    public SpendingsLimitDto findByDate(Date date){
        return EntityToDto.toDto(spendingsLimitRepository.findSpendingsLimitEntityByDate(date));
    }

}
