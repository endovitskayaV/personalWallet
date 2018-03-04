package ru.vsu.personalWallet.domain;

import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;

@Entity
@EqualsAndHashCode
public class Aim {
    private long id;
    private String name;
    private OperationType operationType;
    private String description;
    private long reminderSec;
    private Date date;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
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

    @Column(name = "date", nullable = false)
    public Date getDate() {
        return date;
    }

    @Column(name = "reminder_sec", nullable = false)
    public long getReminderSec() {
        return reminderSec;
    }

    Aim setId(long id) {
        this.id = id;
        return this;
    }

    Aim setName(String name) {
        this.name = name;
        return this;
    }

    Aim setOperationType(OperationType operationType) {
        this.operationType = operationType;
        return this;
    }

    Aim setDescription(String description) {
        this.description = description;
        return this;
    }

    Aim setReminderSec(long reminderSec) {
        this.reminderSec = reminderSec;
        return this;
    }

    Aim setDate(Date date) {
        this.date = date;
        return this;
    }
}
