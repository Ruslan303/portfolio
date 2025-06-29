package com.example.portfolio.service.auth;

import com.example.portfolio.Entity.RefreshToken;
import com.example.portfolio.Entity.User;
import com.example.portfolio.repository.RefreshTokenRepository;
import com.example.portfolio.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;


import java.security.Key;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.function.Function;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenServiceImpl {
    //@Value("${app.jwt.secret}")
    private String secretKey = "RXlKbGJXRnBiQ0k2SW5SaGRHVXRjR3hsYmlJNklqSTBOakE0TkRBek5UVTRPU3dpYVdGMElqb3hOVEl6TXpFd016TTNOakFpTENKcFlYUWlPakV3TnpNek5qTTNNakU1Z3lJc0ltVjRjQ0k2TVRVMk16YzBNR0k1WlRReE1HTmtNbUl4TVRJMlpHVTFNVEF6T1RVNFlpSXNJbWx1ZEdWNGRDSTZJbXhwYm1WamRDOTJhV0YwSWl3aWFXRjBJam94TmpneE1ETXdNakl3TENKcFlYUWlPakV3TWpJM01EQXdPRGcwTENKcFlYUWlPakV3TXpBek56ZzNORE0xTENKcFlYUWlPakV3TURjeE1EQXdOamczZ2lKOS5BbU5hbEdZeTVHRUhvYURVUlVKRXlDaThVZldDb29zeEM=";

    //@Value("${app.jwt.access-token-expiration}")
    private long accessTokenExpiration = 86400000;

    //@Value("${app.jwt.refresh-token-expiration}")
    private long refreshTokenExpiration = 604800000;

    /**/private final RefreshTokenRepository refreshTokenRepository;
    /**/private final UserRepository userRepository;

    public String generateAccessToken(UserDetails userDetails) {
        return buildToken(userDetails, accessTokenExpiration);
    }

    /**************************************************************************************************************/
    public String generateRefreshToken(UserDetails userDetails) {
        User user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(()-> new RuntimeException("User not found"));

        String token = buildToken(userDetails, refreshTokenExpiration);

        RefreshToken refreshToken = RefreshToken.builder()
                .token(token)
                .user(user)
                .expiryDate(Instant.now().plus(Duration.ofMillis(refreshTokenExpiration)))
                .build();

        refreshTokenRepository.save(refreshToken); //

        return token;
    }
    /**************************************************************************************************************/

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private String buildToken(UserDetails userDetails, long expiration) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
