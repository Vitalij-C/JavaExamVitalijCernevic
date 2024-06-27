package lt.java.exam.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {
    private final String secretKey = "EVqg84eCeHfOLNMB2BgqNFwcaaUXf4trcNHp80pemk0JlI8MPwp3WdnfZeLMPsARe5A4gfr8K7irS4sU3eTFOOtVa+9pTqQn8R7P6wkpEE2NyLKeJC9gXyPzQWmcj46We/jkaw8n5SRwFhbGyDI2qYtesNXedJ54TrWN0CE23CUQtxIKCcD8Ocj2fd9fUz1Y2Rq9wBm8wGGQX46CPRnSwlheK8d7J+BP6ADhSQk1xhjgu3zLrXoleTX4eBX4imGgQl5/rFnU70jMEFcZSbU+OuZbWXOOZcNm4bgdUaIUBfdx/k58D3XVfNdsgVL3jsfypnIWb7U9EyA6trdALNRhig==";
    private final long jwtExpiration = 1000 * 60 * 60 * 24;

    public String generateToken(UserDetails userDetails) {
        return Jwts
                .builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Claims extractClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractClaims(token);

        return claimsResolver.apply(claims);
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public boolean isTokenValid(String token) {
        return !isTokenExpired(token);
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);

        return Keys.hmacShaKeyFor(keyBytes);
    }
}
