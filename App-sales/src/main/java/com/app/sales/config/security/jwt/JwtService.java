package com.app.sales.config.security.jwt;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

import com.app.sales.AppSalesApplication;
import com.app.sales.domain.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtService {

	@Value("${security.jwt.expiration}")
	private String expireted;
	
	@Value("${security.jwt.signature-key}")
	private String key;
	
	
	public String generateToken(User user) {
		
		long expString = Long.valueOf(expireted);
        LocalDateTime dateTimeExpiracao = LocalDateTime.now().plusMinutes(expString);
        Instant instant = dateTimeExpiracao.atZone(ZoneId.systemDefault()).toInstant();
        Date date = Date.from(instant);

        return Jwts
                .builder()
                .setSubject(user.getUsername())
                .setExpiration(date)
                .signWith( SignatureAlgorithm.HS512, key )
                .compact();
	}
	
	
	
	private Claims getClaims( String token) throws ExpiredJwtException  {
        return Jwts
                .parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();
    }
	
	public boolean isValidateToken( String token)  {
		try {
            Claims claims = getClaims(token);
            Date dateExpiration = claims.getExpiration();
            LocalDateTime date = 
            		dateExpiration.toInstant()
            		              .atZone(ZoneId.systemDefault()).toLocalDateTime();
            return !LocalDateTime.now().isAfter(date);
        } catch (Exception e){
            return false;
        }
		
	}
	
	public String getUsernameUser( String token)throws ExpiredJwtException {
		return (String) getClaims(token).getSubject();
	}
	
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(AppSalesApplication.class);
		JwtService service = context.getBean(JwtService.class);	
		User user = User.builder().username("ALAN TURING").build();
		String token = service.generateToken(user);
		System.out.println();
		System.out.println(token);
		
		boolean isValidateToken = service.isValidateToken(token);
		System.out.println();
		System.out.println("O token está válido?" +"  "+ isValidateToken);
		System.out.println();
		System.out.println(service.getUsernameUser(token));
	}
}
