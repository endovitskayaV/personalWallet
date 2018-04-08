package ru.vsu.personalWallet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vsu.personalWallet.dto.AimDto;
import ru.vsu.personalWallet.domain.entity.AimEntity;
import ru.vsu.personalWallet.domain.repository.AimRepository;
import ru.vsu.personalWallet.domain.repository.UserRepository;
import ru.vsu.personalWallet.util.EntityToDto;

import java.util.ArrayList;
import java.util.List;

@Service
public class AimService {
    private AimRepository aimRepository;
    private UserRepository userRepository;

    @Autowired
    public AimService(AimRepository aimRepository, UserRepository userRepository) {
        this.aimRepository = aimRepository;
        this.userRepository=userRepository;
    }

    public boolean delete(long id, long userId) {
        AimEntity aimEntity = aimRepository.findAimEntityByIdAndUserId(id, userId);
        if (aimEntity == null) return false; //no such entity, cannot delete
        else aimRepository.delete(aimEntity);
        return true;
    }

    public AimDto add(AimDto aimDto) {
        //always can add
        return EntityToDto.toDto(aimRepository.save(toEntity(aimDto)));
    }

    public boolean edit(AimDto aimDto) {
        if (aimRepository.findAimEntityByIdAndUserId(aimDto.getId(), aimDto.getUserId()) == null)
            return false; //no such entity, cannot edit
        else aimRepository.save(toEntity(aimDto));
        return true;
    }

    public AimDto findByIdAndUserId(long id, long userId) {
        return EntityToDto.toDto(aimRepository.findAimEntityByIdAndUserId(id, userId));
    }

    public List<AimDto> findAllByUserId(long userId) {
        List<AimDto> aimDtoList = new ArrayList<>();
        aimRepository.findAimEntitiesByUserId(userId)
                .forEach(x -> aimDtoList.add(EntityToDto.toDto(x)));
        return aimDtoList;
    }

    public List<AimDto> findByNameAndUserId(String name, long userId) {
        List<AimDto> aimDtoList = new ArrayList<>();
        aimRepository.findAimEntitiesByNameAndUserId(name, userId)
                .forEach(x -> aimDtoList.add(EntityToDto.toDto(x)));
        return aimDtoList;
    }

    public List<AimDto> findByMoneyValueAndUserId(long moneyValue, long userId) {
        List<AimDto> aimDtoList = new ArrayList<>();
        aimRepository.findAimEntitiesByMoneyValueAndUserId(moneyValue, userId)
                .forEach(x -> aimDtoList.add(EntityToDto.toDto(x)));
        return aimDtoList;
    }

    private AimEntity toEntity(AimDto aimDto) {
        if (aimDto != null) {
            return new AimEntity()
                    .setId(aimDto.getId())
                    //cannot use here aimRepository
                    //will have NullPointException while adding
                    //cannot get entity from db before it is added
                    .setUser(userRepository.findOne(aimDto.getUserId()))
                    .setName(aimDto.getName())
                    .setMoneyValue(aimDto.getMoneyValue())
                    .setPeriod(aimDto.getPeriod())
                    .setDescription(aimDto.getDescription())
                    .setReminderSec(aimDto.getReminderSec())
                    .setCreationDate(aimDto.getCreationDate());
        } else return null;
    }
}
