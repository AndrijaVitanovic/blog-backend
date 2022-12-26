package com.blog.security;

import com.blog.config.JwtProperties;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtProvider {

    private final UserDetailsService userDetailsService;
    private final JwtProperties jwtProperties;

    private static final String AUTHORIZATION = "Authorization";
    private static final String BEARER = "Bearer ";

    
    public Optional<Jws<Claims>> resolveToken(String token) {
        return Optional.ofNullable(Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token));
    }

    public String generateToken(String subject, Collection<? extends GrantedAuthority> roles) {
        Claims claims = Jwts.claims();
        claims.put("roles", roles);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setExpiration(new Date(System.currentTimeMillis() + jwtProperties.expirationTime() * 1000))
                .signWith(getSigningKey(), SignatureAlgorithm.HS512)
                .compact();
    }
    public String getUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public Boolean validateToken(Jws<Claims> claims) {
        return !claims.getBody().getExpiration().before(new Date());
    }

    public Optional<Authentication> getAuthentication(HttpServletRequest request) {
        String header = request.getHeader(AUTHORIZATION);

        if (header == null || !header.startsWith(BEARER)) {
            return Optional.empty();
        }

        String token = header.substring(BEARER.length());

        Jws<Claims> claims = resolveToken(token)
                .orElseThrow(() -> new JwtException("Invalid token!"));

        if (claims == null || !validateToken(claims)) {
            return Optional.empty();
        }

        String email = claims.getBody().getSubject();
        UserDetails user = userDetailsService.loadUserByUsername(email);

        return Optional.of(
                new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities()));

    }

    public Key getSigningKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtProperties.secret()));
    }
}

