package ru.vsu.personalWallet.domain.entity;

import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;

@Entity
@EqualsAndHashCode
@Table(name="cheque")
public class ChequeEntity {
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

    ChequeEntity setId(long id) {
        this.id = id;
        return this;
    }

    ChequeEntity setContent(String content) {
        this.content = content;
        return this;
    }

    ChequeEntity setComment(String comment) {
        this.comment = comment;
        return this;
    }

    ChequeEntity setDate(Date date) {
        this.date = date;
        return this;
    }
}
