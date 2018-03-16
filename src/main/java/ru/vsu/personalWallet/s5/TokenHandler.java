package ru.vsu.personalWallet.s5;

import ru.vsu.personalWallet.domain.entity.UserEntity;

public class TokenHandler {
    public String createRefreshToken(UserEntity user) {
       return "";
        //возвращает токен, в котором хранится только username
    }
    public UserEntity parseRefreshToken(String token) {
        return null;
        //парсит username из токена и получает данные пользователя из реализации UserDetailsService
    }
    public String createAccessToken(UserEntity user) {
        return null;
        //возвращает токен, в котором хранятся все данные для воссоздания SessionUser
    }
    public UserEntity parseAccessToken(String token) {
        return null;

        //использует данные из токена для создания нового SessionUser
    }
}
