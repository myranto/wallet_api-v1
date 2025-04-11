package mg.projects.wallet.services.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.security.core.userdetails.UserDetails;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

public class JWTUtility {
   public static SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    public String extractUsername(String token) throws SignatureException {
        return extractClaim(token, Claims::getSubject);
    }
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) throws SignatureException {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token)throws SignatureException {
        return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
    }
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
    public static String generateToken(String username) {
        // Génère une clé secrète sécurisée pour HS256
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + (3600000*24*3))) // Expire après 3 jours
                .signWith(key) // Utilisez la clé secrète générée
                .compact();
    }
}
