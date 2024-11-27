package com.clubes.especialidades.api.controller;

import com.clubes.especialidades.api.dao.sec.LoginRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@RequestMapping("/auth")
@Tag(name = "Auth controller", description = "API to manage authentication")
public interface AuthController {
	@PostMapping("/login")
	@Operation(summary = "Login to the system", description = "Login to the system and get a JWT token", security = {})
	ResponseEntity<Map<String, Object>> login(@RequestBody LoginRequest request);

	@PostMapping("/generate_api_key")
	ResponseEntity<Map<String, Object>> generateApiKey(@RequestParam String username);
}
