package ru.elmanov.jwt.security.demo.utils;

import io.jsonwebtoken.*;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import ru.elmanov.jwt.security.demo.model.security.UserDetailsImpl;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.TemporalUnit;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JwtUtils {

    @Value("${jwt.secret}")
    String secret;

    @Value("${jwt.accessExpirationTimeMs}")
    String accessExpirationTimeMs;

    public String getUserNameFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public String generateToken(Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        final LocalDateTime now = LocalDateTime.now();
        final Instant accessExpirationInstant = now.plusMinutes(5).atZone(ZoneId.systemDefault()).toInstant();
        final Date issuedAt = Date.from(now.atZone(ZoneId.systemDefault()).toInstant());
        final Date accessExpiration = Date.from(accessExpirationInstant);

        return Jwts.builder()
                .setIssuer("myApp")
                .setSubject(userDetails.getUsername())
                .setIssuedAt(issuedAt)
                .setExpiration(accessExpiration)
                .signWith(SignatureAlgorithm.HS512, secret)
                .claim("roles", userDetails.getAuthorities())
                .compact();
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            log.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }
}
