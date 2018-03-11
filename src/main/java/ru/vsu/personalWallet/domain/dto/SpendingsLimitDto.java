package ru.vsu.personalWallet.domain.dto;

import lombok.EqualsAndHashCode;

import java.sql.Timestamp;

@EqualsAndHashCode
public class SpendingsLimitDto {
    private String id;
    private String comment;
    private long maxSum;
    private Timestamp creationDate;

    public String getId() {
        return id;
    }

    public SpendingsLimitDto setId(String id) {
        this.id = id;
        return this;
    }

    public String getComment() {
        return comment;
    }

    public SpendingsLimitDto setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public long getMaxSum() {
        return maxSum;
    }

    public SpendingsLimitDto setMaxSum(long maxSum) {
        this.maxSum = maxSum;
        return this;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public SpendingsLimitDto setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
        return this;
    }
}
