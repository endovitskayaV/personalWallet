package ru.vsu.personalWallet.domain.dto;

public class UserDto {
    private String id;
    private String email;
    private String password;

    public String getId() {
        return id;
    }

    public UserDto setId(String id) {
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
