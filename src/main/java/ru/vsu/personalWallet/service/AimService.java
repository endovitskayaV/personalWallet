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

    public boolean delete(long aimId) {
        if (!aimRepository.exists(aimId)) return false;
        else aimRepository.delete(aimId);
        return true;
    }

   public boolean add(AimDto aimDto){
        if (aimRepository.exists(aimDto.getId())) return false;
        else aimRepository.save(DtoToEntity.toEntity(aimDto));
        return true;
   }
    public boolean edit(AimDto aimDto){
        if (!aimRepository.exists(aimDto.getId())) return false;
        else aimRepository.save(DtoToEntity.toEntity(aimDto));
        return true;
    }

    public AimDto findById(long id){
        return EntityToDto.toDto(aimRepository.findOne(id));
    }

    public List<AimDto> findAll(){
        List<AimDto> aimDtoList=new ArrayList<>();
        aimRepository.findAll().forEach(x->aimDtoList.add(EntityToDto.toDto(x)));
        return aimDtoList;
    }

    public List<AimDto> findByName(String name){
        List<AimDto> aimDtoList=new ArrayList<>();
        aimRepository.findAimEntitiesByName(name).forEach(x->aimDtoList.add(EntityToDto.toDto(x)));
        return aimDtoList;
    }

    public List<AimDto> findByOperationType(OperationType operationType){
        List<AimDto> aimDtoList=new ArrayList<>();
        aimRepository.findAimEntitiesByOperationType(operationType).forEach(x->aimDtoList.add(EntityToDto.toDto(x)));
        return aimDtoList;
    }

    public List<AimDto> findByDate(Date date){
        List<AimDto> aimDtoList=new ArrayList<>();
        aimRepository.findAimEntitiesByDate(date).forEach(x->aimDtoList.add(EntityToDto.toDto(x)));
        return aimDtoList;
    }
}
