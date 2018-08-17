package org.khanal.SbHibernateShoppingcart.services.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Clock;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClock;
import org.khanal.SbHibernateShoppingcart.services.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenUtil {
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    private Clock clock = DefaultClock.INSTANCE;

    public String getUsernameFromToken(String token){
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getIssuedAtDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getIssuedAt);
    }

    public Date getExpirationDateFromToken(String token){
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver){
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset){
        return (lastPasswordReset != null && created.before(lastPasswordReset));
    }

    private boolean ignoreTokenExpiration(String token){
        return false;
    }

    public boolean canTokenBeRefreshed(String token, Date lastPasswordReset){
        final Date createdDate = getIssuedAtDateFromToken(token);

        return !isCreatedBeforeLastPasswordReset(createdDate, lastPasswordReset) && (!isTokenExpired(token) || ignoreTokenExpiration(token));
    }

    public String generateToken(UserDetails userDetails){
        Map<String, Object> claims= new HashMap<>();
        return doGenerateToken(claims, userDetails.getUsername());
    }

    private String doGenerateToken(Map<String, Object> claims, String subject){
        final Date createdDate = clock.now();
        final Date expirationDate = calculateExpirationDate(createdDate);
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(createdDate).setExpiration(expirationDate).signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    private Date calculateExpirationDate(Date createdDate) {
        return new Date(createdDate.getTime() + expiration * 1000);
    }

    private Claims getAllClaimsFromToken(String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    private boolean isTokenExpired(String token){
        return getExpirationDateFromToken(token).before(clock.now());
    }

    public String refreshToken(String token){
        final Date createdDate = clock.now();
        final Date expirationDate = calculateExpirationDate(createdDate);
        final Claims claims = getAllClaimsFromToken(token);
        claims.setIssuedAt(createdDate);
        claims.setExpiration(expirationDate);

        return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    public boolean validateToken(String token, UserDetailsImpl userDetails){
        final String username = getUsernameFromToken(token);
        final Date created = getIssuedAtDateFromToken(token);
        return username.equals(userDetails.getUsername()) && !isCreatedBeforeLastPasswordReset(created, userDetails.getLastPasswordResetDate()) && !isTokenExpired(token);
    }
}
