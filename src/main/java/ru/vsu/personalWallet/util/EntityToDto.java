package ru.vsu.personalWallet.util;

import ru.vsu.personalWallet.domain.dto.*;
import ru.vsu.personalWallet.domain.entity.*;

public class EntityToDto {
    public static AimDto toDto(AimEntity aimEntity){
        if (aimEntity!=null){
            return new AimDto()
                    .setId(aimEntity.getId())
                    .setName(aimEntity.getName())
                    .setMoneyValue(aimEntity.getMoneyValue())
                    .setPeriod(aimEntity.getPeriod())
                    .setOperationType(aimEntity.getOperationType())
                    .setDescription(aimEntity.getDescription())
                    .setReminderSec(aimEntity.getReminderSec())
                    .setCreationDate(aimEntity.getCreationDate());
        }
        else return null;
    }

    public static CategoryDto toDto(CategoryEntity categoryEntity){
        if (categoryEntity!=null){
            return new CategoryDto()
                    .setId(categoryEntity.getId())
                    .setName(categoryEntity.getName());
        }
        else return null;
    }

    public static ChequeDto toDto(ChequeEntity chequeEntity){
        if (chequeEntity!=null){
            return new ChequeDto()
                    .setId(chequeEntity.getId())
                    .setName(chequeEntity.getName())
                    .setContent(chequeEntity.getContent())
                    .setComment(chequeEntity.getComment())
                    .setCreationDate(chequeEntity.getCreationDate());
        }
        else return null;
    }

    public static SpendingsLimitDto toDto(SpendingsLimitEntity spendingsLimitEntity){
        if (spendingsLimitEntity!=null){
            return new SpendingsLimitDto()
                    .setId(spendingsLimitEntity.getId())
                    .setComment(spendingsLimitEntity.getComment())
                    .setCreationDate(spendingsLimitEntity.getCreationDate())
                    .setMaxSum(spendingsLimitEntity.getMaxSum());
        }
        else return null;
    }

    public static TransactionDto toDto(TransactionEntity transactionEntity){
        if (transactionEntity!=null){
            return new TransactionDto()
                    .setId(transactionEntity.getId())
                    .setOperationType(transactionEntity.getOperationType())
                    .setCategoryId(transactionEntity.getCategory().getId())
                    .setCreationDate(transactionEntity.getCreationDate())
                    .setMoneyValue(transactionEntity.getMoneyValue())
                    .setComment(transactionEntity.getComment());
        }
        else return null;
    }

    public static UserDto toDto(UserEntity userEntity){
        if (userEntity!=null){
            return new UserDto()
                    .setId(userEntity.getId())
                    .setEmail(userEntity.getEmail())
                    .setPassword(userEntity.getPassword());
        }
        else return null;
    }
}