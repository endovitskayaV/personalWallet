package ru.vsu.personalWallet.domain.dto;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class UserDto {
    private long id;
    private String email;
    private String password;

    public long getId() {
        return id;
    }

    public UserDto setId(long id) {
        this.id = id;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserDto setPassword(String password) {
        this.password = password;
        return this;
    }

}
