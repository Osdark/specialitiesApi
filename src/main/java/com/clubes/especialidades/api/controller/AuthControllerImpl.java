package com.clubes.especialidades.api.controller;

import com.clubes.especialidades.api.dao.sec.LoginRequest;
import com.clubes.especialidades.api.dao.sec.User;
import com.clubes.especialidades.api.service.ApiKeyService;
import com.clubes.especialidades.api.service.JwtTokenProvider;
import com.clubes.especialidades.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
	@Autowired
	private ApiKeyService apiKeyService;
	@Autowired
	private UserService userService;

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

	@Override
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Map<String, Object>> generateApiKey(String username) {
		User user = userService.getByUsername(username);
		if (user != null) {
			String apiKey = apiKeyService.generateApiKey(user);
			return ResponseEntity.ok(Map.of("ApiKey", apiKey));
		}
		return ResponseEntity.badRequest().body(Map.of("Error", "User not found"));
	}
}
