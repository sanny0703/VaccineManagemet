package com.vaccinemanagement.vm.jwt;

import com.vaccinemanagement.vm.model.User;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenUtil {

    private final long EXPIRATION_TIME = 24 * 60 * 60 *1000; // 24 hrs
    @Value("${app.jwt.secretKey}")
    private  String SECRET_KEY ;
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenUtil.class);

    public String generateAccessToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, user.getUserId()+","+user.getUserEmail());
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims)
                .setSubject(subject)
                .setIssuer("Vaccine Management")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }
    public boolean  validateAccessToken(String token){
        try{
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return  true;
        }
        catch (ExpiredJwtException ex){
            LOGGER.error("Token expired ",ex);
        }
        catch (IllegalArgumentException ex){
            LOGGER.error("Token is null or empty or has whitespaces ",ex);
        }
        catch (MalformedJwtException ex){
            LOGGER.error("Token is invalid ",ex);
        }
        catch (UnsupportedJwtException ex){
            LOGGER.error("Token is not supported ",ex);
        }
        catch (SignatureException ex){
            LOGGER.error("Signature validation failed",ex);
        }
        return false;
    }
    public String getSubject(String token){
        return parseClaims(token).getSubject();
    }
    private Claims parseClaims(String token){
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }
}
