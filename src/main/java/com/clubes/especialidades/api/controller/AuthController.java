package com.clubes.especialidades.api.controller;

import com.clubes.especialidades.api.dao.sec.LoginRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@RequestMapping("/auth")
public interface AuthController {
	@PostMapping("/login")
	ResponseEntity<Map<String, Object>> login(@RequestBody LoginRequest request);
}
