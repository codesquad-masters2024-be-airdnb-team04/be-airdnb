package com.airdnb.clone.global.security.filter;

import static com.airdnb.clone.global.security.constants.SecurityConstants.JWT_EXPIRATION_TIME;
import static com.airdnb.clone.global.security.constants.SecurityConstants.JWT_HEADER;
import static com.airdnb.clone.global.security.constants.SecurityConstants.JWT_ISSUER;
import static com.airdnb.clone.global.security.constants.SecurityConstants.JWT_SUBJECT;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

public class JWTTokenGeneratorFilter extends OncePerRequestFilter {

    @Value("#{environment['jwt.secret']}")
    private String JWT_SECRET;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) { // 인증 정보가 없으면 조기 종료
            return;
        }

        // 인증 정보가 있으면
        SecretKey key = Keys.hmacShaKeyFor(JWT_SECRET.getBytes(StandardCharsets.UTF_8)); // 비밀키 해싱

        /* jwt 빌드 */
        LocalDateTime now = LocalDateTime.now();
        String jwt = Jwts.builder().issuer(JWT_ISSUER).subject(JWT_SUBJECT)
                .claim("username", authentication.getName())
                .claim("authorities", populateAuthorities(authentication.getAuthorities()))
                .issuedAt(Timestamp.valueOf(now))
                .expiration(Timestamp.valueOf(now.plusMinutes(JWT_EXPIRATION_TIME)))
                .signWith(key).compact();
        response.addHeader(JWT_HEADER, jwt); // 응답 헤더에 추가

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return !request.getServletPath().equals("/members"); // /members 경로가 아니면(true) 해당 필터가 실행
    }

    private String populateAuthorities(Collection<? extends GrantedAuthority> collection) {
        Set<String> authorities = new HashSet<>();
        for (GrantedAuthority authority : collection) {
            authorities.add(authority.getAuthority());
        }
        return String.join(",", authorities);
    }
}
