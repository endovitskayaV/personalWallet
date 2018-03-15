package ru.vsu.personalWallet.service;

import org.springframework.stereotype.Service;
import ru.vsu.personalWallet.domain.dto.ChequeDto;
import ru.vsu.personalWallet.domain.entity.ChequeEntity;
import ru.vsu.personalWallet.domain.repository.ChequeRepository;
import ru.vsu.personalWallet.domain.util.EntityToDto;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class ChequeService {
    private ChequeRepository chequeRepository;

    ChequeService(ChequeRepository chequeRepository) {
        this.chequeRepository = chequeRepository;
    }

    public boolean delete(String id) {
        ChequeEntity chequeEntity = chequeRepository.findOne(id);
        if (chequeEntity == null) return false;
        else chequeRepository.delete(chequeEntity);
        return true;
    }

    public boolean add(ChequeDto chequeDto) {
        if (chequeRepository.findOne(chequeDto.getId()) != null) return false;
        else chequeRepository.save(toEntity(chequeDto));
        return true;
    }

    public boolean edit(ChequeDto chequeDto) {
        if (chequeRepository.findOne(chequeDto.getId()) == null) return false;
        else chequeRepository.save(toEntity(chequeDto));
        return true;
    }

    public ChequeDto findById(String id) {
        return EntityToDto.toDto(chequeRepository.findOne(id));
    }

    public List<ChequeDto> findAll() {
        List<ChequeDto> chequeDtoList = new ArrayList<>();
        chequeRepository.findAll().forEach(x -> chequeDtoList.add(EntityToDto.toDto(x)));
        return chequeDtoList;
    }

    public List<ChequeDto> findByName(String name) {
        List<ChequeDto> categoryDtoList = new ArrayList<>();
        chequeRepository.findChequeEntitiesByName(name)
                .forEach(x -> categoryDtoList.add(EntityToDto.toDto(x)));
        return categoryDtoList;
    }

    public List<ChequeDto> findByCreationDate(Timestamp creationDate) {
        List<ChequeDto> categoryDtoList = new ArrayList<>();
        chequeRepository.findChequeEntitiesByCreationDate(creationDate)
                .forEach(x -> categoryDtoList.add(EntityToDto.toDto(x)));
        return categoryDtoList;
    }

    private ChequeEntity toEntity(ChequeDto chequeDto) {
        if (chequeDto != null) {
            return new ChequeEntity()
                    .setId(chequeDto.getId())
                    .setUser(chequeRepository.findOne(chequeDto.getId()).getUser())
                    .setName(chequeDto.getName())
                    .setContent(chequeDto.getContent())
                    .setComment(chequeDto.getComment())
                    .setCreationDate(chequeDto.getCreationDate());
        } else return null;
    }
}
