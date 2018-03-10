package ru.vsu.personalWallet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vsu.personalWallet.domain.OperationType;
import ru.vsu.personalWallet.domain.dto.AimDto;
import ru.vsu.personalWallet.domain.repository.AimRepository;
import ru.vsu.personalWallet.domain.util.DtoToEntity;
import ru.vsu.personalWallet.domain.util.EntityToDto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    public void save(AimDto aimDto){
       aimRepository.save(DtoToEntity.toEntity(aimDto));
    }

    public AimDto findById(long id){
        return EntityToDto.toDto(aimRepository.findOne(id));
    }

    public List<AimDto> findAll(){
        List<AimDto> aimDtoList=new ArrayList<>();
        aimRepository.findAll().forEach(x->aimDtoList.add(EntityToDto.toDto(x)));
        return aimDtoList;
    }

    public AimDto findByName(String name){
        return EntityToDto.toDto(aimRepository.findAimEntityByName(name));
    }

    public AimDto findByOperationType(OperationType operationType){
        return EntityToDto.toDto(aimRepository.findAimEntityByOperationType(operationType));
    }

    public AimDto findByDate(Date date){
        return EntityToDto.toDto(aimRepository.findAimEntityByDate(date));
    }
}
