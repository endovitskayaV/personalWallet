package ru.vsu.personalWallet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vsu.personalWallet.domain.OperationType;
import ru.vsu.personalWallet.domain.dto.AimDto;
import ru.vsu.personalWallet.domain.entity.AimEntity;
import ru.vsu.personalWallet.domain.repository.AimRepository;
import ru.vsu.personalWallet.domain.util.DtoToEntity;
import ru.vsu.personalWallet.domain.util.EntityToDto;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class AimService {
    private AimRepository aimRepository;

    @Autowired
    public AimService(AimRepository aimRepository) {
        this.aimRepository = aimRepository;
    }

    public boolean delete(String id) {
        AimEntity aimEntity = aimRepository.findOne(id);
        if (aimEntity == null) return false;
        else aimRepository.delete(aimEntity);
        return true;
    }

    public boolean add(AimDto aimDto) {
        if (aimRepository.findOne(aimDto.getId()) != null) return false;
        else aimRepository.save(DtoToEntity.toEntity(aimDto));
        return true;
    }

    public boolean edit(AimDto aimDto) {
        if (aimRepository.findOne(aimDto.getId()) == null) return false;
        else aimRepository.save(DtoToEntity.toEntity(aimDto));
        return true;
    }

    public AimDto findById(String id) {
        return EntityToDto.toDto(aimRepository.findOne(id));
    }

    public List<AimDto> findAll() {
        List<AimDto> aimDtoList = new ArrayList<>();
        aimRepository.findAll().forEach(x -> aimDtoList.add(EntityToDto.toDto(x)));
        return aimDtoList;
    }

    public List<AimDto> findByName(String name) {
        List<AimDto> aimDtoList = new ArrayList<>();
        aimRepository.findAimEntitiesByName(name).forEach(x -> aimDtoList.add(EntityToDto.toDto(x)));
        return aimDtoList;
    }

    public List<AimDto> findByOperationType(OperationType operationType) {
        List<AimDto> aimDtoList = new ArrayList<>();
        aimRepository.findAimEntitiesByOperationType(operationType).forEach(x -> aimDtoList.add(EntityToDto.toDto(x)));
        return aimDtoList;
    }

    public List<AimDto> findByCreationDate(Timestamp creationDate) {
        List<AimDto> aimDtoList = new ArrayList<>();
        aimRepository.findAimEntitiesByCreationDate(creationDate).forEach(x -> aimDtoList.add(EntityToDto.toDto(x)));
        return aimDtoList;
    }

    public List<AimDto> findByPeriod(Timestamp period) {
        List<AimDto> aimDtoList = new ArrayList<>();
        aimRepository.findAimEntitiesByPeriod(period).forEach(x -> aimDtoList.add(EntityToDto.toDto(x)));
        return aimDtoList;
    }

    public List<AimDto> findByMoneyValue(long moneyValue) {
        List<AimDto> aimDtoList = new ArrayList<>();
        aimRepository.findAimEntitiesByMoneyValue(moneyValue).forEach(x -> aimDtoList.add(EntityToDto.toDto(x)));
        return aimDtoList;
    }
}
