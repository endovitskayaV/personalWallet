package ru.vsu.personalWallet.domain;

import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;

@Entity
@EqualsAndHashCode
public class Cheque {
    private long id;
    private String content;
    private String comment;
    private Date date;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    @Column(name = "content")
    public String getContent() {
        return content;
    }

    @Column(name = "comment")
    public String getComment() {
        return comment;
    }


    @Column(name = "date", nullable = false)
    public Date getDate() {
        return date;
    }

    Cheque setId(long id) {
        this.id = id;
        return this;
    }

    Cheque setContent(String content) {
        this.content = content;
        return this;
    }

    Cheque setComment(String comment) {
        this.comment = comment;
        return this;
    }

    Cheque setDate(Date date) {
        this.date = date;
        return this;
    }
}
