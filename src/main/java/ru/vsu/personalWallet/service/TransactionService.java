package ru.vsu.personalWallet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vsu.personalWallet.domain.OperationType;
import ru.vsu.personalWallet.domain.dto.TransactionDto;
import ru.vsu.personalWallet.domain.entity.TransactionEntity;
import ru.vsu.personalWallet.domain.repository.CategoryRepository;
import ru.vsu.personalWallet.domain.repository.TransactionRepository;
import ru.vsu.personalWallet.domain.util.EntityToDto;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {
    private TransactionRepository transactionRepository;
    private CategoryRepository categoryRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository,
                              CategoryRepository categoryRepository) {
        this.transactionRepository = transactionRepository;
        this.categoryRepository=categoryRepository;

    }

    public boolean delete(String id) {
        TransactionEntity transactionEntity = transactionRepository.findOne(id);
        if (transactionEntity == null) return false;
        else transactionRepository.delete(transactionEntity);
        return true;
    }

    public boolean add(TransactionDto transactionDto) {
        if (transactionRepository.findOne(transactionDto.getId()) != null)
            return false;
        else transactionRepository.save(toEntity(transactionDto));
        return true;
    }

    public boolean edit(TransactionDto transactionDto) {
        if (transactionRepository.findOne(transactionDto.getId()) == null)
            return false;
        else transactionRepository.save(toEntity(transactionDto));
        return true;

    }

    public List<TransactionDto> findAll() {
        List<TransactionDto> transactionDtoList = new ArrayList<>();
        transactionRepository.findAll().forEach(x -> transactionDtoList.add(EntityToDto.toDto(x)));
        return transactionDtoList;
    }

    public TransactionDto findById(String id) {
        return EntityToDto.toDto(transactionRepository.findOne(id));
    }

    public TransactionEntity findEntityById(String id) {
        return transactionRepository.findOne(id);
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

    private TransactionEntity toEntity(TransactionDto transactionDto) {
        if (transactionDto != null) {
            return new TransactionEntity()
                    .setId(transactionDto.getId())
                    .setUserId(transactionRepository.findOne(transactionDto.getId()).getUserId())
                    .setOperationType(transactionDto.getOperationType())
                    .setCategory(categoryRepository.findOne(transactionDto.getCategoryId()))
                    .setCreationDate(transactionDto.getCreationDate())
                    .setMoneyValue(transactionDto.getMoneyValue())
                    .setComment(transactionDto.getComment());
        } else return null;
    }
}
