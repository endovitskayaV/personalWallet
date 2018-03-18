package ru.vsu.personalWallet.domain.entity;

import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
    private String description;
    private long reminderSec;
    private Timestamp creationDate;


    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    @ManyToOne
    @NotNull
    public UserEntity getUser() {
        return user;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    @Column(name = "money_value", nullable = false)
    public long getMoneyValue() {
        return moneyValue;
    }

    @Column(name = "period", nullable = false)
    public Timestamp getPeriod() {
        return period;
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
