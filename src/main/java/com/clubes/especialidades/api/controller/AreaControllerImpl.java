package com.clubes.especialidades.api.controller;

import com.clubes.especialidades.api.dao.Area;
import com.clubes.especialidades.api.dao.Requirement;
import com.clubes.especialidades.api.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class AreaControllerImpl implements AreaController {
	@Autowired
	private AreaService service;

	@Override
	public ResponseEntity<Area> createArea(Area area, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			throw new IllegalArgumentException("Validation failed for request body: " + bindingResult.getAllErrors());
		}
		return ResponseEntity.ok(service.createArea(area));
	}

	@Override
	public ResponseEntity<Boolean> updateArea(UUID id, Area area, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			throw new IllegalArgumentException("Validation failed for request body: " + bindingResult.getAllErrors());
		}
		return ResponseEntity.ok(service.updateArea(id, area));
	}

	@Override
	public ResponseEntity<Boolean> deleteArea(UUID id) {
		return ResponseEntity.ok(service.deleteArea(id));
	}

	@Override
	public ResponseEntity<List<Area>> getAreas() {
		return ResponseEntity.ok(service.getAreas());
	}

	@Override
	public ResponseEntity<Area> getAreaById(UUID id) {
		return ResponseEntity.ok(service.getArea(id));
	}

	@Override
	public ResponseEntity<Area> getAreaByName(String name) {
		return ResponseEntity.ok(service.getAreaByName(name));
	}
}
