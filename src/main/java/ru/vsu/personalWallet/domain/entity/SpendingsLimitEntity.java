package ru.vsu.personalWallet.domain.entity;


import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@EqualsAndHashCode
@Table(name="spendings_limit")
public class SpendingsLimitEntity {
    private String id;
    private UserEntity user;
    private String comment;
    private long maxSum;
    private Timestamp creationDate;

    @Id
    @Column(name = "id", nullable = false)
    public String getId() {
        return id;
    }

    @ManyToOne
    public UserEntity getUser() {
        return user;
    }

    @Column(name = "comment")
    public String getComment() {
        return comment;
    }


    @Column(name = "max_sum", nullable = false)
    public long getMaxSum() {
        return maxSum;
    }

    @Column(name = "creation_date", nullable = false)
    public Timestamp getCreationDate() {
        return creationDate;
    }

    public SpendingsLimitEntity setId(String id) {
        this.id = id;
        return this;
    }

    public SpendingsLimitEntity setUser(UserEntity user) {
        this.user = user;
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

    public SpendingsLimitEntity setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
        return this;
    }
}
