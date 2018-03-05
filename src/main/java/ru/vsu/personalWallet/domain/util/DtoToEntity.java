package ru.vsu.personalWallet.domain.util;

import ru.vsu.personalWallet.domain.dto.*;
import ru.vsu.personalWallet.domain.entity.*;

public class DtoToEntity {
    //TODO: finish class
    public static AimEntity toEntity(AimDto aimDto){
        if (aimDto!=null){
            return new AimEntity()
                    .setId(aimDto.getId())
                    .setName(aimDto.getName())
                    .setOperationType(aimDto.getOperationType())
                    .setDescription(aimDto.getDescription())
                    .setReminderSec(aimDto.getReminderSec())
                    .setDate(aimDto.getDate());
        }
        else return null;
    }

    public static CategoryEntity toEntity(CategoryDto categoryDto){
        if (categoryDto!=null){
            return new CategoryEntity()
                    .setId(categoryDto.getId())
                    .setName(categoryDto.getName());
        }
        else return null;
    }

    public static ChequeEntity toEntity(ChequeDto chequeDto){
        if (chequeDto!=null){
            return new ChequeEntity()
                    .setId(chequeDto.getId())
                    .setName(chequeDto.getName())
                    .setContent(chequeDto.getContent())
                    .setComment(chequeDto.getComment())
                    .setDate(chequeDto.getDate());
        }
            else return null;
    }

    public static SpendingsLimitEntity toEntity(SpendingsLimitDto spendingsLimitDto){
        if (spendingsLimitDto!=null){
            return new SpendingsLimitEntity()
                    .setId(spendingsLimitDto.getId())
                    .setComment(spendingsLimitDto.getComment())
                    .setDate(spendingsLimitDto.getDate())
                    .setMaxSum(spendingsLimitDto.getMaxSum());
        }
        else return null;
    }

    public static TransactionEntity toEntity(TransactionDto transactionDto){
        if (transactionDto!=null){
            return new TransactionEntity()
                    .setId(transactionDto.getId())
                    .setOperationType(transactionDto.getOperationType())
                    .setCategory(toEntity(transactionDto.getCategory()))
                    .setDate(transactionDto.getDate())
                    .setMoneyValue(transactionDto.getMoneyValue())
                    .setComment(transactionDto.getComment());
        }
        else return null;
    }
}
