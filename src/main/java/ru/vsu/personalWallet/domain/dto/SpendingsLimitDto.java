package ru.vsu.personalWallet.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

public class SpendingsLimitDto {
    private long id;
    private String comment;
    private long maxSum;
    private Date date;

    public long getId() {
        return id;
    }

    public SpendingsLimitDto setId(long id) {
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

    public Date getDate() {
        return date;
    }

    public SpendingsLimitDto setDate(Date date) {
        this.date = date;
        return this;
    }
}
