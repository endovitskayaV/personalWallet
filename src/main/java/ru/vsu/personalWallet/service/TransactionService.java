package ru.vsu.personalWallet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vsu.personalWallet.domain.OperationType;
import ru.vsu.personalWallet.domain.dto.TransactionDto;
import ru.vsu.personalWallet.domain.repository.TransactionRepository;
import ru.vsu.personalWallet.domain.util.DtoToEntity;
import ru.vsu.personalWallet.domain.util.EntityToDto;

import java.util.Date;

@Service
public class TransactionService {
    private TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository=transactionRepository;
    }

    public void delete(long aimId) {
        transactionRepository.delete(aimId);
    }

    public void save(TransactionDto transactionDto){
        transactionRepository.save(DtoToEntity.toEntity(transactionDto));
    }

    public TransactionDto findById(long id){
        return EntityToDto.toDto(transactionRepository.findOne(id));
    }

    public TransactionDto findByOperationType(OperationType operationType){
        return EntityToDto.toDto(transactionRepository.findTransactionEntityByOperationType(operationType));
    }

    public TransactionDto findByDate(Date date){
        return EntityToDto.toDto(transactionRepository.findTransactionEntityByDate(date));
    }

    public TransactionDto findByMoneyValue(long moneyValue){
        return EntityToDto.toDto(transactionRepository.findTransactionEntityByMoneyValue(moneyValue));
    }

    public TransactionDto findByComment(String comment){
        return EntityToDto.toDto(transactionRepository.findTransactionEntityByComment(comment));
    }

    public TransactionDto findByCategoryName(String categoryName){
        return EntityToDto.toDto(transactionRepository.findTransactionEntityByCategoryName(categoryName));
    }
}
