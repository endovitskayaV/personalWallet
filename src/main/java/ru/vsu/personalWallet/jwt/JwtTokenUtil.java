package ru.vsu.personalWallet.jwt;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import ru.vsu.personalWallet.dto.TokenDto;
import ru.vsu.personalWallet.dto.UserDto;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtTokenUtil implements Serializable {
    private static final long ACCESS_TOKEN_VALIDITY_SECONDS = 5 * 60;//*60*60;
    private static final long REFRESH_TOKEN_VALIDITY_SECONDS = 10 * 60;//*60*60;
    private static final String SIGNING_KEY = "evv98V";
    private static final String ISSUER_AUTH_TOKEN = "evv_authToken";
    private static final String ISSUER_REFRESH_TOKEN = "evv_refreshToken";

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    private String getIssuerFromToken(String token) {
        return getClaimFromToken(token, Claims::getIssuer);
    }

    private Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(SIGNING_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public String generateAuthToken(UserDto userDto) {
        return doGenerateAuthToken(userDto.getEmail());
    }

    public String generateRefreshToken(UserDto userDto) {
        return doGenerateRefreshToken(userDto.getEmail());
    }

    private String doGenerateAuthToken(String subject) {

        Claims claims = Jwts.claims().setSubject(subject);
        claims.put("scopes", Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN")));

        return Jwts.builder()
                .setClaims(claims)
                .setIssuer(ISSUER_AUTH_TOKEN)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY_SECONDS * 1000))
                .signWith(SignatureAlgorithm.HS256, SIGNING_KEY)
                .compact();
    }

    private String doGenerateRefreshToken(String subject) {

        Claims claims = Jwts.claims().setSubject(subject);
        claims.put("scopes", Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN")));

        return Jwts.builder()
                .setClaims(claims)
                .setIssuer(ISSUER_REFRESH_TOKEN)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_VALIDITY_SECONDS * 1000))
                .signWith(SignatureAlgorithm.HS256, SIGNING_KEY)
                .compact();
    }

    public Boolean validateAuthToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (
                getIssuerFromToken(token).equals(ISSUER_AUTH_TOKEN) &&
                        username.equals(userDetails.getUsername())
                        && !isTokenExpired(token));
    }

    public Boolean validateRefreshToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (
                getIssuerFromToken(token).equals(ISSUER_REFRESH_TOKEN) &&
                        username.equals(userDetails.getUsername())
                        && !isTokenExpired(token));
    }

    public TokenDto refresh(UserDto userDto) {
        return new TokenDto()
                .setAuthorizationToken(generateAuthToken(userDto))
                .setRefreshToken(generateRefreshToken(userDto));
    }
}
