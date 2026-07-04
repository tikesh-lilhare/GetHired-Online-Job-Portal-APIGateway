package com.example.ApiGateway.Util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {
    private final String SECRET =
            "mysecretkeymysecretkeymysecretkeymysecretkeymysecretkey";

    public String extractUsername(
            String token) {

        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public String extractRole(
            String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();

        return claims.get("role", String.class);
    }

    public boolean validateToken(
            String token) {

        try {

            Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token);

            return true;

        } catch (Exception e) {

            return false;
        }
    }

}
