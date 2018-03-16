package ru.vsu.personalWallet.security;

import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class TokenUtils {
    public  static final String TOKEN_HEADER = "X-Auth-Token";
    private static final String SECRET = "sssshhhh!";
//    private final String AUDIENCE_UNKNOWN = "unknown";
//    private final String AUDIENCE_WEB = "web";
//    private final String AUDIENCE_MOBILE = "mobile";
//    private final String AUDIENCE_TABLET = "tablet";


    //private Long expiration = 604800L;

    public String getUsernameFromToken(String token) {
        String username;
        try {
            //  final Claims claims = this.getClaimsFromToken(token);
            username = this.getClaimsFromToken(token).getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    private Date getCreatedDateFromToken(String token) {
        Date created;
        try {
            //  final Claims claims = this.getClaimsFromToken(token);
            created = new Date((Long) this.getClaimsFromToken(token).get("created"));
        } catch (Exception e) {
            created = null;
        }
        return created;
    }

//     private Date getExpirationDateFromToken(String token) {
//        Date expiration;
//        try {
//            //final Claims claims = this.getClaimsFromToken(token);
//            expiration = this.getClaimsFromToken(token).getExpiration();
//        } catch (Exception e) {
//            expiration = null;
//        }
//        return expiration;
//    }

//    public String getAudienceFromToken(String token) {
//        String audience;
//        try {
//            final Claims claims = this.getClaimsFromToken(token);
//            audience = (String) claims.get("audience");
//        } catch (Exception e) {
//            audience = null;
//        }
//        return audience;
//    }

    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

//    private Date generateExpirationDate() {
//        return new Date(System.currentTimeMillis() + this.expiration * 1000);
//    }

//    private Boolean isTokenExpired(String token) {
//        final Date expiration = this.getExpirationDateFromToken(token);
//        return expiration.before(this.generateCurrentDate());
//    }

//    private Boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset) {
//        return (lastPasswordReset != null && created.before(lastPasswordReset));
//    }


//    private Boolean ignoreTokenExpiration(String token) {
//        String audience = this.getAudienceFromToken(token);
//        return (this.AUDIENCE_TABLET.equals(audience) || this.AUDIENCE_MOBILE.equals(audience));
//    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<String, Object>();
        claims.put("sub", userDetails.getUsername());
        // claims.put("audience", "web");
        claims.put("created", new Date(System.currentTimeMillis()));
        return this.generateToken(claims);
    }

    private String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                // .setExpiration(this.generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

//
//    public Boolean canTokenBeRefreshed(String token, Date lastPasswordReset) {
//        final Date created = this.getCreatedDateFromToken(token);
//        return (!(this.isCreatedBeforeLastPasswordReset(created, lastPasswordReset))
//                && (!(this.isTokenExpired(token)) /*|| this.ignoreTokenExpiration(token)*/));
//    }

//    public String refreshToken(String token) {
//        String refreshedToken;
//        try {
//            final Claims claims = this.getClaimsFromToken(token);
//            claims.put("created", this.generateCurrentDate());
//            refreshedToken = this.generateToken(claims);
//        } catch (Exception e) {
//            refreshedToken = null;
//        }
//        return refreshedToken;
//    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        SpringSecurityUser user = (SpringSecurityUser) userDetails;
        final String username = this.getUsernameFromToken(token);
        final Date created = this.getCreatedDateFromToken(token); //TODO: check equals
//        final Date expiration = this.getExpirationDateFromToken(token);
        return (username.equals(user.getUsername()));
//                && !(this.isTokenExpired(token))
//                && !(this.isCreatedBeforeLastPasswordReset(created, user.getLastPasswordReset())));
    }

}