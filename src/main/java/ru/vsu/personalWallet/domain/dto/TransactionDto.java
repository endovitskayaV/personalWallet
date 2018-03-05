package ru.vsu.personalWallet.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.vsu.personalWallet.domain.OperationType;

import java.util.Date;

public class TransactionDto {
    private long id;
    private OperationType operationType;
    private CategoryDto category;
    private Date date;
    private long moneyValue;
    private String comment;

    public long getId() {
        return id;
    }

    public TransactionDto setId(long id) {
        this.id = id;
        return this;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public TransactionDto setOperationType(OperationType operationType) {
        this.operationType = operationType;
        return this;
    }

    public CategoryDto getCategory() {
        return category;
    }

    public TransactionDto setCategory(CategoryDto category) {
        this.category = category;
        return this;
    }

    public Date getDate() {
        return date;
    }

    public TransactionDto setDate(Date date) {
        this.date = date;
        return this;
    }

    public long getMoneyValue() {
        return moneyValue;
    }

    public TransactionDto setMoneyValue(long moneyValue) {
        this.moneyValue = moneyValue;
        return this;
    }

    public String getComment() {
        return comment;
    }

    public TransactionDto setComment(String comment) {
        this.comment = comment;
        return this;
    }
}
