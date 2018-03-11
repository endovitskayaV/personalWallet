package ru.vsu.personalWallet.domain.entity;

import lombok.EqualsAndHashCode;
import ru.vsu.personalWallet.domain.OperationType;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@EqualsAndHashCode
@Table(name = "aim")
public class AimEntity {
    private long id;
    private String name;
    private OperationType operationType;
    private String description;
    private long reminderSec;
    private Timestamp date;


    @Id
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
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
    public Timestamp getDate() {
        return date;
    }

    @Column(name = "reminder_sec", nullable = false)
    public long getReminderSec() {
        return reminderSec;
    }

    public AimEntity setId(long id) {
        this.id = id;
        return this;
    }

    public AimEntity setName(String name) {
        this.name = name;
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

    public AimEntity setDate(Timestamp date) {
        this.date = date;
        return this;
    }
}
