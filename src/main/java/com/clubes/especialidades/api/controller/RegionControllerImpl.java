package com.clubes.especialidades.api.controller;

import com.clubes.especialidades.api.dao.Region;
import com.clubes.especialidades.api.service.RegionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@Tag(name = "Region controller", description = "API to manage regions")
public class RegionControllerImpl implements RegionController {
	@Autowired
	private RegionService service;

	@Override
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Region> createRegion(Region region, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			throw new IllegalArgumentException("Validation failed for request body: " + bindingResult.getFieldError());
		}
		return ResponseEntity.ok(service.createRegion(region));
	}

	@Override
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Boolean> updateRegion(UUID id, Region region, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			throw new IllegalArgumentException("Validation failed for request body: " + bindingResult.getFieldError());
		}
		return ResponseEntity.ok(service.updateRegion(id, region));
	}

	@Override
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Boolean> deleteRegion(UUID id) {
		return ResponseEntity.ok(service.deleteRegion(id));
	}

	@Override
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
	public ResponseEntity<List<Region>> getRegions() {
		return ResponseEntity.ok(service.getRegions());
	}

	@Override
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
	public ResponseEntity<Region> getRegionById(UUID id) {
		return ResponseEntity.ok(service.getRegion(id));
	}

	@Override
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
	public ResponseEntity<Region> getRegionByName(String name) {
		return ResponseEntity.ok(service.getRegionByName(name));
	}

	@Override
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
	public ResponseEntity<Region> getRegionByAbbreviation(String abbreviation) {
		return ResponseEntity.ok(service.getRegionByAbbreviation(abbreviation));
	}
}
