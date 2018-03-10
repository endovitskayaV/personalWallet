package ru.vsu.personalWallet.domain.dto;

import ru.vsu.personalWallet.domain.OperationType;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

public class AimDto {
    private long id;
    private String name;
    private OperationType operationType;
    private String description;
    private long reminderSec;
    private Timestamp date;

    public long getId() {
        return id;
    }

    public AimDto setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public AimDto setName(String name) {
        this.name = name;
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

    public Timestamp getDate() {
        return date;
    }

    public AimDto setDate(Timestamp date) {
        this.date = date;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AimDto aimDto = (AimDto) o;
        return id == aimDto.id &&
                reminderSec == aimDto.reminderSec &&
                Objects.equals(name, aimDto.name) &&
                operationType == aimDto.operationType &&
                Objects.equals(description, aimDto.description) &&
                Objects.equals(date, aimDto.date);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, operationType, description, reminderSec, date);
    }
}
