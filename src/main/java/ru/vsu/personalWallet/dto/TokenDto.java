package ru.vsu.personalWallet.dto;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class TokenDto {
    private String authorizationToken;
    private String refreshToken;
    public TokenDto(){

    }

    public String getAuthorizationToken() {
        return authorizationToken;
    }

    public TokenDto setAuthorizationToken(String authorizationToken) {
        this.authorizationToken = authorizationToken;
        return this;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public TokenDto setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
        return this;
    }
}
