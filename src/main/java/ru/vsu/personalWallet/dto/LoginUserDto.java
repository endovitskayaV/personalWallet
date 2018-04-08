package ru.vsu.personalWallet.dto;

import lombok.EqualsAndHashCode;

//TODO: mark as @Data and solve problem with constructor and builder
@EqualsAndHashCode
public class LoginUserDto {
    private String email;
    private String password;

    public LoginUserDto(){}
    public LoginUserDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public LoginUserDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    LoginUserDto setPassword(String password) {
        this.password = password;
        return this;
    }
}
