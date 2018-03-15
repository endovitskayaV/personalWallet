package ru.vsu.personalWallet.domain.entity;

import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@EqualsAndHashCode
@Table(name="cheque")
public class ChequeEntity {
    private String id;
    private String userId;
    private String name;
    private String content;
    private String comment;
    private Timestamp creationDate;

    @Id
    @Column(name = "id", nullable = false)
    public String getId() {
        return id;
    }

    @Column(name = "user_id", nullable = false)
    public String getUserId() {
        return userId;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    @Column(name = "content")
    public String getContent() {
        return content;
    }

    @Column(name = "comment")
    public String getComment() {
        return comment;
    }


    @Column(name = "creation_date", nullable = false)
    public Timestamp getCreationDate() {
        return creationDate;
    }

    public ChequeEntity setId(String id) {
        this.id = id;
        return this;
    }

    public ChequeEntity setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public ChequeEntity setName(String name) {
        this.name= name;
        return this;
    }

    public ChequeEntity setContent(String content) {
        this.content = content;
        return this;
    }

    public ChequeEntity setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public ChequeEntity setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
        return this;
    }
}
