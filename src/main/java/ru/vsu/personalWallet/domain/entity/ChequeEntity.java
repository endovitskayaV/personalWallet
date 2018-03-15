package ru.vsu.personalWallet.domain.entity;

import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@EqualsAndHashCode
@Table(name="cheque")
public class ChequeEntity {
    private String id;
    private UserEntity user;
    private String name;
    private String content;
    private String comment;
    private Timestamp creationDate;

    @Id
    @Column(name = "id", nullable = false)
    public String getId() {
        return id;
    }

    @ManyToOne
    public UserEntity getUser() {
        return user;
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

    public ChequeEntity setUser(UserEntity user) {
        this.user = user;
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
