package ru.vsu.personalWallet.domain.entity;


import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
@EqualsAndHashCode
@Table(name="spendings_limit")
public class SpendingsLimitEntity {
    private long id;
    private UserEntity user;
    private String comment;
    private long maxSum;
    private Timestamp creationDate;

    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    @ManyToOne
    @NotNull
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

    public SpendingsLimitEntity setId(long id) {
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
