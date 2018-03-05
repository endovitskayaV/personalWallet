package ru.vsu.personalWallet.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

public class ChequeDto {
    private long id;
    private String name;
    private String content;
    private String comment;
    private Date date;

    public long getId() {
        return id;
    }

    public ChequeDto setId(long id) {
        this.id = id;
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

    public Date getDate() {
        return date;
    }

    public ChequeDto setDate(Date date) {
        this.date = date;
        return this;
    }
}
