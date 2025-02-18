package com.SpringBoot.FoundIt_BackEnd.Security;

import com.SpringBoot.FoundIt_BackEnd.Model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class JwtUtils {

    @Value("${spring.jwt.token.key}")
    private String secretKey;


    public String generateToken (Map<String,Object> claims, User user) {
        claims.put("userName",user.getUsername());
        claims.put("role",user.getAuthorities());
        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+ 1000 * 60 * 60 * 24))
                .signWith(key(), SignatureAlgorithm.HS384)
                .compact();
       return token;
    }

    public Key key () {
        byte[] KeyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(KeyBytes);
    }

    public Claims extractClaims (String token) {
        return Jwts.parser().setSigningKey(key()).build().parseClaimsJws(token).getBody();
    }

    public String extractEmail (String token) {
        return extractClaims(token).get("userName").toString();
    }

    public Collection<? extends GrantedAuthority> extractRole (String Token){
        Object roleClaims = extractClaims(Token).get("role");
        if(roleClaims instanceof List<?>){
            List<HashMap<String,String>> roles = (List<HashMap<String,String>>) roleClaims;
            return roles.stream().map(x-> new SimpleGrantedAuthority(x.get("authority"))).collect(Collectors.toSet());
        }else {
            return List.of( new SimpleGrantedAuthority(null));
        }
    }

    public Date extractExpiration (String token) {
        return extractClaims(token).getExpiration();
    }

    public Boolean validateExpiration (String token) {
        return extractExpiration(token).before(new Date());
    }

    public Boolean tokenValidation (String token, UserDetails userDetails) {
        final String username = extractEmail(token);
        return (username.equals(userDetails.getUsername())) && !validateExpiration(token);
    }

}

