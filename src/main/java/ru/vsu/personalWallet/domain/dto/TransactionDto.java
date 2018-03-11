package ru.vsu.personalWallet.domain.dto;

import ru.vsu.personalWallet.domain.OperationType;

import java.sql.Timestamp;


public class TransactionDto {
    private String id;
    private OperationType operationType;
    private CategoryDto category;
    private Timestamp creationDate;
    private long moneyValue;
    private String comment;

    public String getId() {
        return id;
    }

    public TransactionDto setId(String id) {
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

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public TransactionDto setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
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
