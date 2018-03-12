package ru.vsu.personalWallet.service;

import org.springframework.stereotype.Service;
import ru.vsu.personalWallet.domain.dto.SpendingsLimitDto;
import ru.vsu.personalWallet.domain.entity.SpendingsLimitEntity;
import ru.vsu.personalWallet.domain.repository.SpendingsLimitRepository;
import ru.vsu.personalWallet.domain.util.DtoToEntity;
import ru.vsu.personalWallet.domain.util.EntityToDto;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class SpendingsLimitService {
    private SpendingsLimitRepository spendingsLimitRepository;

    SpendingsLimitService (SpendingsLimitRepository spendingsLimitRepository) {
        this.spendingsLimitRepository = spendingsLimitRepository;
    }

    public boolean delete(String id) {
       SpendingsLimitEntity spendingsLimitEntity =
               spendingsLimitRepository.findSpendingsLimitEntityById(id);
        if (spendingsLimitEntity == null) return false;
        else spendingsLimitRepository.delete(spendingsLimitEntity);
        return true;
    }

    public boolean add(SpendingsLimitDto spendingsLimitDto) {
        if (spendingsLimitRepository.findSpendingsLimitEntityById(spendingsLimitDto.getId()) != null)
            return false;
        else spendingsLimitRepository.save(DtoToEntity.toEntity(spendingsLimitDto));
        return true;
    }

    public boolean edit(SpendingsLimitDto spendingsLimitDto) {
        if (spendingsLimitRepository.findSpendingsLimitEntityById(spendingsLimitDto.getId()) == null)
            return false;
        else spendingsLimitRepository.save(DtoToEntity.toEntity(spendingsLimitDto));
        return true;
    }

    public SpendingsLimitDto findById(String id) {
        return EntityToDto.toDto(spendingsLimitRepository.findSpendingsLimitEntityById(id));
    }

    public List<SpendingsLimitDto> findAll(){
        List<SpendingsLimitDto> spendingsLimitDtoList=new ArrayList<>();
        spendingsLimitRepository.findAll().forEach(x->spendingsLimitDtoList.add(EntityToDto.toDto(x)));
        return spendingsLimitDtoList;
    }

    public List<SpendingsLimitDto> findByMaxSum(long maxSum){
        List<SpendingsLimitDto> spendingsLimitDtoList=new ArrayList<>();
        spendingsLimitRepository.findSpendingsLimitEntitiesByMaxSum(maxSum).forEach(x->spendingsLimitDtoList.add(EntityToDto.toDto(x)));
        return spendingsLimitDtoList;
    }

    public List<SpendingsLimitDto> findByCreationDate(Timestamp creationDate){
        List<SpendingsLimitDto> spendingsLimitDtoList=new ArrayList<>();
        spendingsLimitRepository.findSpendingsLimitEntitiesByCreationDate(creationDate).forEach(x->spendingsLimitDtoList.add(EntityToDto.toDto(x)));
        return spendingsLimitDtoList;
    }

}
