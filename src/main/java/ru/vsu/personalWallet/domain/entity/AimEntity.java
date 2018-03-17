package ru.vsu.personalWallet.domain.entity;

import lombok.EqualsAndHashCode;
import ru.vsu.personalWallet.domain.OperationType;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@EqualsAndHashCode
@Table(name = "aim")
public class AimEntity {
    private long id;
    private UserEntity user;
    private String name;
    private long moneyValue;
    private Timestamp period;
    private OperationType operationType;
    private String description;
    private long reminderSec;
    private Timestamp creationDate;


    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    @ManyToOne
    public UserEntity getUser() {
        return user;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    @Column(name = "money_value")
    public long getMoneyValue() {
        return moneyValue;
    }

    @Column(name = "period")
    public Timestamp getPeriod() {
        return period;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "operation_type", nullable = false)
    public OperationType getOperationType() {
        return operationType;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    @Column(name = "creation_date", nullable = false)
    public Timestamp getCreationDate() {
        return creationDate;
    }

    @Column(name = "reminder_sec", nullable = false)
    public long getReminderSec() {
        return reminderSec;
    }


    public AimEntity setId(long id) {
        this.id = id;
        return this;
    }

    public AimEntity setUser(UserEntity user) {
        this.user = user;
        return this;
    }

    public AimEntity setName(String name) {
        this.name = name;
        return this;
    }

    public AimEntity setMoneyValue(long moneyValue) {
        this.moneyValue = moneyValue;
        return this;
    }

    public AimEntity setPeriod(Timestamp period) {
        this.period = period;
        return this;
    }

    public AimEntity setOperationType(OperationType operationType) {
        this.operationType = operationType;
        return this;
    }

    public AimEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public AimEntity setReminderSec(long reminderSec) {
        this.reminderSec = reminderSec;
        return this;
    }

    public AimEntity setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
        return this;
    }
}
