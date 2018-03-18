package ru.vsu.personalWallet.domain.entity;

import lombok.EqualsAndHashCode;
import ru.vsu.personalWallet.domain.OperationType;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@EqualsAndHashCode
@Table(name="transaction")
public class TransactionEntity {
    private long id;
    private UserEntity user;
    private OperationType operationType;
    private CategoryEntity category;
    private Timestamp creationDate;
    private long moneyValue;
    private String comment;


    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    @ManyToOne
    public UserEntity getUser() {
        return user;
    }

    @Enumerated(value = EnumType.STRING)
    @Column(name = "operation_type", nullable = false)
    public OperationType getOperationType() {
        return operationType;
    }


    //TODO: make it not nul
    @ManyToOne
    public CategoryEntity getCategory() {
        return category;
    }

    @Column(name = "creation_date", nullable = false)
    public Timestamp getCreationDate() {
        return creationDate;
    }

    @Column(name = "money_value", nullable = false)
    public long getMoneyValue() {
        return moneyValue;
    }

    @Column(name = "comment")
    public String getComment() {
        return comment;
    }


    public TransactionEntity setId(long id) {
        this.id = id;
        return this;
    }

    public TransactionEntity setUser(UserEntity user) {
        this.user = user;
        return this;
    }
    public TransactionEntity setOperationType(OperationType operationType) {
        this.operationType = operationType;
        return this;
    }

    public TransactionEntity setCategory(CategoryEntity category) {
        this.category = category;
        return this;
    }

    public TransactionEntity setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public TransactionEntity setMoneyValue(long moneyValue) {
        this.moneyValue = moneyValue;
        return this;
    }

    public TransactionEntity setComment(String comment) {
        this.comment = comment;
        return this;
    }

}
