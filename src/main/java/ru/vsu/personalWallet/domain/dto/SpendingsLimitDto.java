package ru.vsu.personalWallet.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;

import java.sql.Timestamp;

@EqualsAndHashCode
public class SpendingsLimitDto {
    private long id;
    private long userId;
    private String comment;
    private long maxSum;
    private Timestamp creationDate;

    public long getId() {
        return id;
    }

    public SpendingsLimitDto setId(long id) {
        this.id = id;
        return this;
    }

    @JsonIgnore
    public long getUserId() {
        return userId;
    }

    public SpendingsLimitDto setUserId(long userId) {
        this.userId = userId;
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
