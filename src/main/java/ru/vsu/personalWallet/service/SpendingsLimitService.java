package ru.vsu.personalWallet.service;

import org.springframework.stereotype.Service;
import ru.vsu.personalWallet.dto.SpendingsLimitDto;
import ru.vsu.personalWallet.domain.entity.SpendingsLimitEntity;
import ru.vsu.personalWallet.domain.repository.SpendingsLimitRepository;
import ru.vsu.personalWallet.domain.repository.UserRepository;
import ru.vsu.personalWallet.util.EntityToDto;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class SpendingsLimitService {
    private SpendingsLimitRepository spendingsLimitRepository;
    private UserRepository userRepository;

    SpendingsLimitService (SpendingsLimitRepository spendingsLimitRepository,
                           UserRepository userRepository) {
        this.spendingsLimitRepository = spendingsLimitRepository;
        this.userRepository=userRepository;
    }

    public boolean delete(long id, long userId) {
       SpendingsLimitEntity spendingsLimitEntity = spendingsLimitRepository
               .findSpendingsLimitEntityByIdAndUserId(id, userId);
        if (spendingsLimitEntity == null) return false; //no such entity, cannot delete
        else spendingsLimitRepository.delete(spendingsLimitEntity);
        return true;
    }

    public SpendingsLimitDto add(SpendingsLimitDto spendingsLimitDto) {
        //always can add
        return EntityToDto.toDto(spendingsLimitRepository.save(toEntity(spendingsLimitDto)));
    }

    public boolean edit(SpendingsLimitDto spendingsLimitDto) {
        if (spendingsLimitRepository.findSpendingsLimitEntityByIdAndUserId
                (spendingsLimitDto.getId(), spendingsLimitDto.getUserId()) == null)
            return false; //no such entity, cannot edit
        else spendingsLimitRepository.save(toEntity(spendingsLimitDto));
        return true;
    }

    public SpendingsLimitDto findByIdAndUserId(long id, long userId) {
        return EntityToDto.toDto(spendingsLimitRepository
                .findSpendingsLimitEntityByIdAndUserId(id, userId));
    }

    public List<SpendingsLimitDto> findAllByUserId(long userId) {
        List<SpendingsLimitDto> spendingsLimitDtoList=new ArrayList<>();
        spendingsLimitRepository.findSpendingsLimitEntitiesByUserId(userId)
                .forEach(x->spendingsLimitDtoList.add(EntityToDto.toDto(x)));
        return spendingsLimitDtoList;
    }


    public List<SpendingsLimitDto> findByMaxSumAndUserId(long maxSum, long userId){
        List<SpendingsLimitDto> spendingsLimitDtoList=new ArrayList<>();
        spendingsLimitRepository.findSpendingsLimitEntitiesByMaxSumAndUserId(maxSum, userId)
                .forEach(x->spendingsLimitDtoList.add(EntityToDto.toDto(x)));
        return spendingsLimitDtoList;
    }

    public List<SpendingsLimitDto> findByCreationDateAndUserId(Timestamp creationDate, long userId){
        List<SpendingsLimitDto> spendingsLimitDtoList=new ArrayList<>();
        spendingsLimitRepository.findSpendingsLimitEntitiesByCreationDateAndUserId(creationDate, userId)
                .forEach(x->spendingsLimitDtoList.add(EntityToDto.toDto(x)));
        return spendingsLimitDtoList;
    }

    private SpendingsLimitEntity toEntity(SpendingsLimitDto spendingsLimitDto) {
        if (spendingsLimitDto != null) {
            return new SpendingsLimitEntity()
                    .setId(spendingsLimitDto.getId())
                    //cannot use here spendingsLimitRepository
                    //will have NullPointException while adding
                    //cannot get entity from db before it is added
                    .setUser(userRepository.findOne(spendingsLimitDto.getUserId()))
                    .setComment(spendingsLimitDto.getComment())
                    .setCreationDate(spendingsLimitDto.getCreationDate())
                    .setMaxSum(spendingsLimitDto.getMaxSum());
        } else return null;
    }

}
