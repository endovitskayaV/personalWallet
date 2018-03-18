package ru.vsu.personalWallet.util;

import lombok.EqualsAndHashCode;

//TODO: mark as @Data and solve problem with constructor and builder
@EqualsAndHashCode
public class LoginUser {
    private String email;
    private String password;

    public LoginUser(){}
    public LoginUser(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public LoginUser setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    LoginUser setPassword(String password) {
        this.password = password;
        return this;
    }
}
