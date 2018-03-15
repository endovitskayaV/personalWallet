package ru.vsu.personalWallet.domain.entity;

import lombok.EqualsAndHashCode;
import ru.vsu.personalWallet.domain.OperationType;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@EqualsAndHashCode
@Table(name="transaction")
public class TransactionEntity {
    private String id;
    private String userId;
    private OperationType operationType;
    private CategoryEntity category;
    private Timestamp creationDate;
    private long moneyValue;
    private String comment;


    @Id
    @Column(name = "id", nullable = false, unique = true)
    public String getId() {
        return id;
    }

    @Column(name = "user_id", nullable = false)
    public String getUserId() {
        return userId;
    }

    @Enumerated(value = EnumType.STRING)
    @Column(name = "operation_type", nullable = false)
    public OperationType getOperationType() {
        return operationType;
    }

    @ManyToOne
    //@JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false, table = "category")
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


    public TransactionEntity setId(String id) {
        this.id = id;
        return this;
    }

    public TransactionEntity setUserId(String userId) {
        this.userId = userId;
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
