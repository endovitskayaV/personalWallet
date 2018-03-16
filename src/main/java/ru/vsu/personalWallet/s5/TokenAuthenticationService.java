package ru.vsu.personalWallet.s5;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import org.springframework.security.core.Authentication;
import ru.vsu.personalWallet.domain.entity.UserEntity;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TokenAuthenticationService {
    public void addAuthentication(HttpServletResponse response, UserEntity user) {
//      //  Cookie access = new Cookie("access_token", tokenHandler.createAccessToken(user));
//        access.setPath("/");
//        access.setHttpOnly(true);
//        response.addCookie(access);
//
//        Cookie refresh = new Cookie("refresh_token", tokenHandler.createRefreshToken(user));
//        refresh.setPath("/");
//        refresh.setHttpOnly(true);
//        response.addCookie(refresh);
    }

    public Authentication getAuthentication(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();

        String accessToken = null;
        String refreshToken = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (("access_token").equals(cookie.getName())) {
                    accessToken = cookie.getValue();
                }
                if (("refresh_token").equals(cookie.getName())) {
                    refreshToken = cookie.getValue();
                }
            }
        }

        if (accessToken != null && !accessToken.isEmpty()) {
            try {
//               UserEntity user = tokenHandler.parseAccessToken(accessToken);
//                return new UserAuthentication(user);
            } catch (ExpiredJwtException ex) {
                if (refreshToken != null && !refreshToken.isEmpty()) {
                    try {
                       // SessionUser user = tokenHandler.parseRefreshToken(refreshToken);
                      //  Cookie access = new Cookie("access_token", tokenHandler.createAccessToken(user));
//                        access.setPath("/");
//                        access.setHttpOnly(true);
//                        response.addCookie(access);
//                        return new UserAuthentication(user);
                    } catch (JwtException e) {
                        return null;
                    }
                }
                return null;
            } catch (JwtException ex) {
                return null;
            }
        }
        return null;
    }
}
