package ru.vsu.personalWallet.domain;


import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;

@Entity
@EqualsAndHashCode
public class SpendingsLimit {
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

    SpendingsLimit setId(long id) {
        this.id = id;
        return this;
    }

    SpendingsLimit setComment(String comment) {
        this.comment = comment;
        return this;
    }

    SpendingsLimit setMaxSum(long maxSum) {
        this.maxSum = maxSum;
        return this;
    }

    SpendingsLimit setDate(Date date) {
        this.date = date;
        return this;
    }
}
