package com.cmcni.sales_management_system_backend.common.security.jwt;

import com.cmcni.sales_management_system_backend.common.security.CustomUserDetails;
import com.cmcni.sales_management_system_backend.domain.user.entity.RoleType;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static com.cmcni.sales_management_system_backend.common.security.jwt.JwtConst.USER_ID;

@Log4j2
@Component
public class JwtProvider {
    private Key SECRET_KEY;

    public JwtProvider(@Value("${jwt.secret}") String secretKeyString) {
        createKey(secretKeyString);
    }

    private void createKey(String secretKeyString) {
        byte[] keyBytes = secretKeyString.getBytes(StandardCharsets.UTF_8);
        this.SECRET_KEY = Keys.hmacShaKeyFor(keyBytes);
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(JwtConst.AUTHORIZATION_HEADER);

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(JwtConst.AUTHORIZATION_TYPE)) {
            return bearerToken.substring(7);
        }

        return null;
    }

    public String generateAccessToken(CustomUserDetails userDetails) {
        Claims claims = createClaims(userDetails);
        claims.put(JwtConst.TOKEN_TYPE, JwtConst.TOKEN_TYPE_ACCESS);
        return doGenerateToken(claims, JwtConst.ACCESS_TOKEN_EXPIRE_TIME);
    }

    public String generateRefreshToken(CustomUserDetails userDetails) {
        Claims claims = createClaims(userDetails);
        claims.put(JwtConst.TOKEN_TYPE, JwtConst.TOKEN_TYPE_REFRESH);
        return doGenerateToken(claims, JwtConst.REFRESH_TOKEN_EXPIRE_TIME);
    }

    private String doGenerateToken(Claims claims, long expiredTime) {
        return Jwts.builder()
                .setHeader(createHeader())
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiredTime))
                .signWith(SECRET_KEY)
                .compact();
    }

    public Map<String, Object> createHeader() {
        Map<String, Object> header = new HashMap<>();
        header.put("typ", "JWT");
        header.put("alg", SignatureAlgorithm.HS256.getValue()); // 해시 256 암호화
        header.put("regDate", System.currentTimeMillis());
        return header;
    }

    public Claims createClaims(CustomUserDetails userDetails) {
        Claims claims = Jwts.claims();
        claims.put(Claims.ID, userDetails.getRememberToken());
        claims.put(USER_ID, String.valueOf(userDetails.getUser().getId()));
        claims.put(Claims.SUBJECT, userDetails.getUser().getEmailAddress());
        claims.put(JwtConst.ROLE_TYPE, userDetails.getUser().getUserRoleType().getRoleType().name());
        return claims;
    }

    public JwtData createJwtData(Claims claims) {
        String userId = claims.get(USER_ID, String.class);
        String tokenType = claims.get(JwtConst.TOKEN_TYPE, String.class);
        String tokenId = claims.getId();
        String email = claims.getSubject();
        RoleType roleType = RoleType.valueOf(claims.get(JwtConst.ROLE_TYPE, String.class));
        return JwtData.create(userId, tokenType, tokenId, email, roleType);
    }

    public static String createRememberToken() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public JwtData verifyToken(String token) throws RuntimeException {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            return createJwtData(claims);
        } catch (NullPointerException e) {
            log.info("JWT 토큰이 존재하지 않습니다.");
            throw e;
        } catch (io.jsonwebtoken.security.SignatureException e) {
            log.error("Invalid JWT signature: {}", e.getMessage());
            throw e;
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired: {}", e.getMessage());
            log.info("유효기간이 초과된  JWT 토큰입니다.");
            throw e;
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", e.getMessage());
            log.info("JWT 토큰이 잘못되었습니다.");
            throw e;
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {}", e.getMessage());
            log.info("지원되지 않는 JWT 토큰입니다.");
            throw e;
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
            log.info("잘못된 JWT 서명입니다.");
            throw e;
        } catch (SecurityException e) {
            log.info("잘못된 JWT 서명입니다.");
            throw e;
        } catch (Exception e) {
            log.info(e.getMessage());
            throw e;
        }
    }
}
