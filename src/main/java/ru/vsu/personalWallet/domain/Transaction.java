package ru.vsu.personalWallet.domain;

import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;

@Entity
@EqualsAndHashCode
public class Transaction {
    private long id;
    private OperationType operationType;
    private Category category;
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
    public Category getCategory() {
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


    private Transaction setId(long id) {
        this.id = id;
        return this;
    }

    Transaction setOperationType(OperationType operationType) {
        this.operationType = operationType;
        return this;
    }

    Transaction setCategory(Category category) {
        this.category = category;
        return this;
    }

    Transaction setDate(Date date) {
        this.date = date;
        return this;
    }

    Transaction setMoneyValue(long moneyValue) {
        this.moneyValue = moneyValue;
        return this;
    }

    Transaction setComment(String comment) {
        this.comment = comment;
        return this;
    }

}
