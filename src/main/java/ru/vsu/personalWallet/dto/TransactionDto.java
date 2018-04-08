package ru.vsu.personalWallet.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import ru.vsu.personalWallet.domain.OperationType;

import java.sql.Timestamp;

@EqualsAndHashCode
public class TransactionDto {
    private long id;
    private long userId;
    private OperationType operationType;
    private long categoryId;
    private Timestamp creationDate;
    private long moneyValue;
    private String comment;

    public long getId() {
        return id;
    }

    public TransactionDto setId(long id) {
        this.id = id;
        return this;
    }

    @JsonIgnore
    public long getUserId() {
        return userId;
    }

    public TransactionDto setUserId(long userId) {
        this.userId = userId;
        return this;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public TransactionDto setOperationType(OperationType operationType) {
        this.operationType = operationType;
        return this;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public TransactionDto setCategoryId(long categoryId) {
        this.categoryId = categoryId;
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
