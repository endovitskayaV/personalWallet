package ru.vsu.personalWallet.domain.entity;

import lombok.EqualsAndHashCode;
import ru.vsu.personalWallet.domain.OperationType;

import javax.persistence.*;
import java.util.Date;

@Entity
@EqualsAndHashCode
@Table(name="transaction")
public class TransactionEntity {
    private long id;
    private OperationType operationType;
    private CategoryEntity category;
    private Date date;
    private long moneyValue;
    private String comment;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }


    @Enumerated(EnumType.STRING)
    @Column(name = "operation_type", nullable = false)
    public OperationType getOperationType() {
        return operationType;
    }

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false)
    public CategoryEntity getCategory() {
        return category;
    }

    @Column(name = "date", nullable = false)
    public Date getDate() {
        return date;
    }

    @Column(name = "money_value", nullable = false)
    public long getMoneyValue() {
        return moneyValue;
    }

    @Column(name = "comment")
    public String getComment() {
        return comment;
    }


    private TransactionEntity setId(long id) {
        this.id = id;
        return this;
    }

    TransactionEntity setOperationType(OperationType operationType) {
        this.operationType = operationType;
        return this;
    }

    TransactionEntity setCategory(CategoryEntity category) {
        this.category = category;
        return this;
    }

    TransactionEntity setDate(Date date) {
        this.date = date;
        return this;
    }

    TransactionEntity setMoneyValue(long moneyValue) {
        this.moneyValue = moneyValue;
        return this;
    }

    TransactionEntity setComment(String comment) {
        this.comment = comment;
        return this;
    }

}
