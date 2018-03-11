package ru.vsu.personalWallet.domain.dto;

import lombok.EqualsAndHashCode;
import ru.vsu.personalWallet.domain.OperationType;

import java.sql.Timestamp;

@EqualsAndHashCode
public class AimDto {
    private String id;
    private String name;
    private long moneyValue;
    private Timestamp period;
    private OperationType operationType;
    private String description;
    private long reminderSec;
    private Timestamp creationDate;

    public String getId() {
        return id;
    }

    public AimDto setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public long getMoneyValue() {
        return moneyValue;
    }

    public Timestamp getPeriod() {
        return period;
    }

    public AimDto setName(String name) {
        this.name = name;
        return this;
    }

    public AimDto setMoneyValue(long moneyValue) {
        this.moneyValue = moneyValue;
        return this;
    }

    public AimDto setPeriod(Timestamp period) {
        this.period = period;
        return this;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public AimDto setOperationType(OperationType operationType) {
        this.operationType = operationType;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public AimDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public long getReminderSec() {
        return reminderSec;
    }

    public AimDto setReminderSec(long reminderSec) {
        this.reminderSec = reminderSec;
        return this;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public AimDto setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
        return this;
    }

}
