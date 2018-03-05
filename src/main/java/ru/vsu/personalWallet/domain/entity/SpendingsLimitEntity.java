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

    public SpendingsLimitEntity setId(long id) {
        this.id = id;
        return this;
    }

    public SpendingsLimitEntity setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public SpendingsLimitEntity setMaxSum(long maxSum) {
        this.maxSum = maxSum;
        return this;
    }

    public SpendingsLimitEntity setDate(Date date) {
        this.date = date;
        return this;
    }
}
