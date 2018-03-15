package ru.vsu.personalWallet.domain.entity;

import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@EqualsAndHashCode
@Table(name = "user")
public class UserEntity {
    private String id;
    private String email;
    private String password;

    @Id
    @Column(name = "id", nullable = false, unique = true)
    String getId() {
        return id;
    }

    @Column(name = "email", nullable = false)
    public String getEmail() {
        return email;
    }

    @Column(name = "password", nullable = false)
    public String getPassword() {
        return password;
    }

    UserEntity setId(String id) {
        this.id = id;
        return this;
    }

    public UserEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserEntity setPassword(String password) {
        this.password = password;
        return this;
    }
}
