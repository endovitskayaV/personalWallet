package ru.vsu.personalWallet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vsu.personalWallet.domain.OperationType;
import ru.vsu.personalWallet.domain.dto.TransactionDto;
import ru.vsu.personalWallet.domain.entity.TransactionEntity;
import ru.vsu.personalWallet.domain.repository.TransactionRepository;
import ru.vsu.personalWallet.domain.util.DtoToEntity;
import ru.vsu.personalWallet.domain.util.EntityToDto;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {
    private TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public boolean delete(String id) {
        TransactionEntity transactionEntity = transactionRepository.findTransactionEntityById(id);
        if (transactionEntity == null) return false;
        else transactionRepository.delete(transactionEntity);
        return true;
    }

    public boolean add(TransactionDto transactionDto) {
        if (transactionRepository.findTransactionEntityById(transactionDto.getId()) != null)
            return false;
        else transactionRepository.save(DtoToEntity.toEntity(transactionDto));
        return true;
    }

    public boolean edit(TransactionDto transactionDto) {
        if (transactionRepository.findTransactionEntityById(transactionDto.getId()) == null)
            return false;
        else transactionRepository.save(DtoToEntity.toEntity(transactionDto));
        return true;

    }

    public List<TransactionDto> findAll() {
        List<TransactionDto> transactionDtoList = new ArrayList<>();
        transactionRepository.findAll().forEach(x -> transactionDtoList.add(EntityToDto.toDto(x)));
        return transactionDtoList;
    }

    public TransactionDto findById(String id) {
        return EntityToDto.toDto(transactionRepository.findTransactionEntityById(id));
    }

    public List<TransactionDto> findByOperationType(OperationType operationType) {
        List<TransactionDto> transactionDtoList = new ArrayList<>();
        transactionRepository.findTransactionEntityByOperationType(operationType)
                .forEach(x -> transactionDtoList.add(EntityToDto.toDto(x)));
        return transactionDtoList;
    }

    public List<TransactionDto> findByCreationDate(Timestamp creationDate) {
        List<TransactionDto> transactionDtoList = new ArrayList<>();
        transactionRepository.findTransactionEntityByCreationDate(creationDate)
                .forEach(x -> transactionDtoList.add(EntityToDto.toDto(x)));
        return transactionDtoList;
    }

    public List<TransactionDto> findByMoneyValue(long moneyValue) {
        List<TransactionDto> transactionDtoList = new ArrayList<>();
        transactionRepository.findTransactionEntityByMoneyValue(moneyValue)
                .forEach(x -> transactionDtoList.add(EntityToDto.toDto(x)));
        return transactionDtoList;
    }

    public List<TransactionDto> findByComment(String comment) {
        List<TransactionDto> transactionDtoList = new ArrayList<>();
        transactionRepository.findTransactionEntityByComment(comment)
                .forEach(x -> transactionDtoList.add(EntityToDto.toDto(x)));
        return transactionDtoList;
    }

    public List<TransactionDto> findByCategoryId(String categoryId) {
        List<TransactionDto> transactionDtoList = new ArrayList<>();
        transactionRepository.findTransactionEntityByCategoryId(categoryId)
                .forEach(x -> transactionDtoList.add(EntityToDto.toDto(x)));
        return transactionDtoList;
    }
}
