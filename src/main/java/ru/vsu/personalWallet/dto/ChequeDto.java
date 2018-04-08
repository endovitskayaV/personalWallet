package ru.vsu.personalWallet.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;

import java.sql.Timestamp;

@EqualsAndHashCode
public class ChequeDto {
    private long id;
    private long userId;
    private String name;
    private String content;
    private String comment;
    private Timestamp creationDate;

    public long getId() {
        return id;
    }

    public ChequeDto setId(long id) {
        this.id = id;
        return this;
    }

    @JsonIgnore
    public long getUserId() {
        return userId;
    }

    public ChequeDto setUserId(long userId) {
        this.userId = userId;
        return this;
    }

    public String getName() {
        return name;
    }

    public ChequeDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getContent() {
        return content;
    }

    public ChequeDto setContent(String content) {
        this.content = content;
        return this;
    }

    public String getComment() {
        return comment;
    }

    public ChequeDto setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public ChequeDto setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
        return this;
    }
}
