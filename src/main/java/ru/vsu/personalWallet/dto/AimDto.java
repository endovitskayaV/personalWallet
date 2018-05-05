package ru.vsu.personalWallet.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;

import java.sql.Timestamp;

//TODO: if it is possible, get rid of userId field
@EqualsAndHashCode
public class AimDto {
    private long id;
    private long userId;
    private String name;
    private long moneyValue;
    private Timestamp period;
    private String description;
    private long reminderSec;
    private Timestamp creationDate;
    private long savedMoneyValue;

    public long getId() {
        return id;
    }

    public AimDto setId(long id) {
        this.id = id;
        return this;
    }

    @JsonIgnore
    public long getUserId() {
        return userId;
    }

    public AimDto setUserId(long userId) {
        this.userId = userId;
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

    public long getSavedMoneyValue() {
        return savedMoneyValue;
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

    public AimDto setSavedMoneyValue(long savedMoneyValue) {
        this.savedMoneyValue=savedMoneyValue;
        return this;
    }

}
