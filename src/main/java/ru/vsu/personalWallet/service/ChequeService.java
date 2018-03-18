package ru.vsu.personalWallet.service;

import org.springframework.stereotype.Service;
import ru.vsu.personalWallet.domain.dto.AimDto;
import ru.vsu.personalWallet.domain.dto.ChequeDto;
import ru.vsu.personalWallet.domain.entity.ChequeEntity;
import ru.vsu.personalWallet.domain.repository.ChequeRepository;
import ru.vsu.personalWallet.domain.repository.UserRepository;
import ru.vsu.personalWallet.util.EntityToDto;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class ChequeService {
    private ChequeRepository chequeRepository;
    private UserRepository userRepository;

    ChequeService(ChequeRepository chequeRepository, UserRepository userRepository) {
        this.chequeRepository = chequeRepository;
        this.userRepository=userRepository;
    }

    public boolean delete(long id, long userId) {
        ChequeEntity chequeEntity = chequeRepository.findChequeEntityByIdAndUserId(id, userId);
        if (chequeEntity == null) return false;//no such entity, cannot delete
        else chequeRepository.delete(chequeEntity);
        return true;
    }

    public ChequeDto add(ChequeDto chequeDto) {
        //always can add
        return EntityToDto.toDto(chequeRepository.save(toEntity(chequeDto)));
    }

    public boolean edit(ChequeDto chequeDto) {
        if (chequeRepository.findChequeEntityByIdAndUserId
                (chequeDto.getId(), chequeDto.getUserId()) == null)
            return false;//no such entity, cannot edit
        else chequeRepository.save(toEntity(chequeDto));
        return true;
    }

    public ChequeDto findByIdAndUserId(long id, long userId) {
        return EntityToDto.toDto(chequeRepository.findChequeEntityByIdAndUserId(id, userId));
    }

    public List<ChequeDto> findAllByUserId(long userId) {
        List<ChequeDto> chequeDtoList = new ArrayList<>();
        chequeRepository.findChequeEntitiesByUserId(userId)
                .forEach(x -> chequeDtoList.add(EntityToDto.toDto(x)));
        return chequeDtoList;
    }

    public List<ChequeDto> findByNameAndUserId(String name, long userId) {
        List<ChequeDto> categoryDtoList = new ArrayList<>();
        chequeRepository.findChequeEntitiesByNameAndUserId(name, userId)
                .forEach(x -> categoryDtoList.add(EntityToDto.toDto(x)));
        return categoryDtoList;
    }

    public List<ChequeDto> findByCreationDateAndUserId(Timestamp creationDate, long userId) {
        List<ChequeDto> categoryDtoList = new ArrayList<>();
        chequeRepository.findChequeEntitiesByCreationDateAndUserId(creationDate, userId)
                .forEach(x -> categoryDtoList.add(EntityToDto.toDto(x)));
        return categoryDtoList;
    }

    private ChequeEntity toEntity(ChequeDto chequeDto) {
        if (chequeDto != null) {
            return new ChequeEntity()
                    .setId(chequeDto.getId())
                    //cannot use here chequeRepository
                    //will have NullPointException while adding
                    //cannot get entity from db before it is added
                    .setUser(userRepository.findOne(chequeDto.getUserId()))
                    .setName(chequeDto.getName())
                    .setContent(chequeDto.getContent())
                    .setComment(chequeDto.getComment())
                    .setCreationDate(chequeDto.getCreationDate());
        } else return null;
    }
}
