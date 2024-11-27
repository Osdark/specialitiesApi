package com.clubes.especialidades.api.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtTokenProvider {

	@Value("${jwt.secret}")
	private String SECRET_KEY;

	private SecretKey getSecretKey() {
		return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
	}

	public String generateToken(String username, List<String> roles) {
		return Jwts.builder()
				.setIssuedAt(new Date())
				.setClaims(Map.of("roles", roles, "username", username))
				.setExpiration(new Date(System.currentTimeMillis() + 86400000))
				.signWith(getSecretKey())
				.compact();
	}

	public boolean validateToken(String token, UserDetails userDetails) {
		final String username = extractUsername(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

	private boolean isTokenExpired(String token) {
		return extractClaim(token, Claims::getExpiration).before(new Date());
	}

	public String extractUsername(String jwtToken) {
		return extractClaim(jwtToken, claims -> claims.get("username", String.class));
	}

	private <T> T extractClaim(String jwtToken, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(jwtToken);
		return claimsResolver.apply(claims);
	}

	private Claims extractAllClaims(String jwtToken) {
		return Jwts.parserBuilder().setSigningKey(getSecretKey()).build().parseClaimsJws(jwtToken).getBody();
	}

	@SuppressWarnings("unchecked")
	public List<GrantedAuthority> extractRolesFromToken(String jwtToken) {
		List<String> roles = extractClaim(jwtToken, claims -> claims.get("roles", List.class));
		return roles.stream()
				.map(SimpleGrantedAuthority::new)
				.map(GrantedAuthority.class::cast)
				.toList();
	}
}
