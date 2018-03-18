package ru.vsu.personalWallet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vsu.personalWallet.domain.OperationType;
import ru.vsu.personalWallet.domain.dto.AimDto;
import ru.vsu.personalWallet.domain.dto.TransactionDto;
import ru.vsu.personalWallet.domain.entity.AimEntity;
import ru.vsu.personalWallet.domain.entity.TransactionEntity;
import ru.vsu.personalWallet.domain.repository.CategoryRepository;
import ru.vsu.personalWallet.domain.repository.TransactionRepository;
import ru.vsu.personalWallet.domain.repository.UserRepository;
import ru.vsu.personalWallet.util.EntityToDto;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {
    private TransactionRepository transactionRepository;
    private CategoryRepository categoryRepository;
    private UserRepository userRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository,
                              CategoryRepository categoryRepository,
                              UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.categoryRepository=categoryRepository;
        this.userRepository=userRepository;

    }

    public boolean delete(long id, long userId) {
        TransactionEntity transactionEntity =
                transactionRepository.findTransactionEntityByIdAndUserId(id, userId);
        if (transactionEntity == null) return false; //no such entity, cannot delete
        else transactionRepository.delete(transactionEntity);
        return true;
    }
    public TransactionDto add(TransactionDto transactionDto) {
        //always can add
        return EntityToDto.toDto(transactionRepository.save(toEntity(transactionDto)));
    }

    public boolean edit(TransactionDto transactionDto, long userId) {
        long id = transactionDto.getId();
        if (transactionRepository.findTransactionEntityByIdAndUserId(id, userId) == null)
            return false;
        else transactionRepository.save(toEntity(transactionDto));
        return true;

    }

    public List<TransactionDto> findAllByUserId(long userId) {
        List<TransactionDto> transactionDtoList = new ArrayList<>();
        transactionRepository.findTransactionEntitiesByUserId(userId)
                .forEach(x -> transactionDtoList.add(EntityToDto.toDto(x)));
        return transactionDtoList;
    }

    public TransactionDto findByIdAndUserId(long id, long userId) {
        return EntityToDto.toDto(transactionRepository.findTransactionEntityByIdAndUserId(id, userId));
    }


    public List<TransactionDto> findByOperationTypeAndUserId(OperationType operationType, long userId) {
        List<TransactionDto> transactionDtoList = new ArrayList<>();
        transactionRepository.findTransactionEntityByOperationTypeAndUserId(operationType, userId)
                .forEach(x -> transactionDtoList.add(EntityToDto.toDto(x)));
        return transactionDtoList;
    }

    public List<TransactionDto> findByMoneyValueAndUserId(long moneyValue, long userId) {
        List<TransactionDto> transactionDtoList = new ArrayList<>();
        transactionRepository.findTransactionEntityByMoneyValueAndUserId(moneyValue, userId)
                .forEach(x -> transactionDtoList.add(EntityToDto.toDto(x)));
        return transactionDtoList;
    }

    public List<TransactionDto> findByCategoryIdAndUserId(long categoryId, long userId) {
        List<TransactionDto> transactionDtoList = new ArrayList<>();
        transactionRepository.findTransactionEntityByCategoryIdAndUserId(categoryId, userId)
                .forEach(x -> transactionDtoList.add(EntityToDto.toDto(x)));
        return transactionDtoList;
    }

    private TransactionEntity toEntity(TransactionDto transactionDto) {
        if (transactionDto != null) {
            return new TransactionEntity()
                    .setId(transactionDto.getId())
                    .setUser(userRepository.findOne(transactionDto.getUserId()))
                    .setOperationType(transactionDto.getOperationType())
                    .setCategory(categoryRepository.findOne(transactionDto.getCategoryId()))
                    .setCreationDate(transactionDto.getCreationDate())
                    .setMoneyValue(transactionDto.getMoneyValue())
                    .setComment(transactionDto.getComment());
        } else return null;
    }
}
