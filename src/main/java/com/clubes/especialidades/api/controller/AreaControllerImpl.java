package com.clubes.especialidades.api.controller;

import com.clubes.especialidades.api.dao.Area;
import com.clubes.especialidades.api.service.AreaService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@Tag(name = "Area controller", description = "API to manage areas")
public class AreaControllerImpl implements AreaController {
	@Autowired
	private AreaService service;

	@Override
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Area> createArea(Area area, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			throw new IllegalArgumentException("Validation failed for request body: " + bindingResult.getFieldError());
		}
		return ResponseEntity.ok(service.createArea(area));
	}

	@Override
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Boolean> updateArea(UUID id, Area area, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			throw new IllegalArgumentException("Validation failed for request body: " + bindingResult.getFieldError());
		}
		return ResponseEntity.ok(service.updateArea(id, area));
	}

	@Override
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Boolean> deleteArea(UUID id) {
		return ResponseEntity.ok(service.deleteArea(id));
	}

	@Override
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
	public ResponseEntity<List<Area>> getAreas() {
		return ResponseEntity.ok(service.getAreas());
	}

	@Override
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
	public ResponseEntity<Area> getAreaById(UUID id) {
		return ResponseEntity.ok(service.getArea(id));
	}

	@Override
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
	public ResponseEntity<Area> getAreaByName(String name) {
		return ResponseEntity.ok(service.getAreaByName(name));
	}
}
