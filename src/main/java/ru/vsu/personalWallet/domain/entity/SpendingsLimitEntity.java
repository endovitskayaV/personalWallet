package ru.vsu.personalWallet.domain.entity;


import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;

@Entity
@EqualsAndHashCode
@Table(name="spendings_limit")
public class SpendingsLimitEntity {
    private long id;
    private String comment;
    private long maxSum;
    private Date date;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    @Column(name = "comment")
    public String getComment() {
        return comment;
    }


    @Column(name = "max_sum", nullable = false)
    public long getMaxSum() {
        return maxSum;
    }

    @Column(name = "date", nullable = false)
    public Date getDate() {
        return date;
    }

    SpendingsLimitEntity setId(long id) {
        this.id = id;
        return this;
    }

    SpendingsLimitEntity setComment(String comment) {
        this.comment = comment;
        return this;
    }

    SpendingsLimitEntity setMaxSum(long maxSum) {
        this.maxSum = maxSum;
        return this;
    }

    SpendingsLimitEntity setDate(Date date) {
        this.date = date;
        return this;
    }
}
