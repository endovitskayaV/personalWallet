package ru.vsu.personalWallet.dto;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class TokenDto {
    private String authorizationToken;
    private String refreshToken;
    private long userId;
    public TokenDto(){

    }

    public String getAuthorizationToken() {
        return authorizationToken;
    }

    public TokenDto setAuthorizationToken(String authorizationToken) {
        this.authorizationToken = authorizationToken;
        return this;
    }

    public long getUserId() {
        return userId;
    }

    public TokenDto setUserId(long userId) {
        this.userId = userId;
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
