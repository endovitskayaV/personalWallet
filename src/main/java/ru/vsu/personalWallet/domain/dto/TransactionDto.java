package ru.vsu.personalWallet.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.vsu.personalWallet.domain.OperationType;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class TransactionDto {
    private long id;
    private OperationType operationType;
    private CategoryDto category;
    private Date date;
    private long moneyValue;
    private String comment;
}
