package com.clubes.especialidades.api.controller;

import com.clubes.especialidades.api.dao.sec.LoginRequest;
import com.clubes.especialidades.api.service.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class AuthControllerImpl implements AuthController {
	@Autowired
	private AuthenticationManager authManager;
	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Override
	public ResponseEntity<Map<String, Object>> login(LoginRequest request) {
		Authentication authentication = authManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						request.getUsername(),
						request.getPassword()
				)
		);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream()
				.map(Object::toString)
				.toList();
		String token = jwtTokenProvider.generateToken(request.getUsername(), roles);
		return ResponseEntity.ok(Map.of("Token", token));
	}
}
