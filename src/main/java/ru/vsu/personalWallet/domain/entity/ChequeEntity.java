package ru.vsu.personalWallet.domain.entity;

import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
@EqualsAndHashCode
@Table(name="cheque")
public class ChequeEntity {
    private long id;
    private UserEntity user;
    private String name;
    private String content;
    private String comment;
    private Timestamp creationDate;

    @Id
    @Column(name = "id", nullable = false, unique = true)
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

    @Column(name = "content", nullable = false)
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

    public ChequeEntity setId(long id) {
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
