package com.clubes.especialidades.api.controller;

import com.clubes.especialidades.api.dao.Speciality;
import com.clubes.especialidades.api.service.SpecialityService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@Tag(name = "Honor controller", description = "API to manage honors")
public class SpecialityControllerImpl implements SpecialityController {
	@Autowired
	private SpecialityService service;

	@Override
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<String> batchLoadSpecialities(MultipartFile file) throws IOException {
		return ResponseEntity.ok(service.batchLoadSpecialities(file));
	}

	@Override
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Speciality> createSpeciality(Speciality speciality, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			throw new IllegalArgumentException("Validation failed for request body: " + bindingResult.getFieldError());
		}
		return ResponseEntity.ok(service.createSpeciality(speciality));
	}

	@Override
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Boolean> updateSpeciality(UUID id, Speciality speciality, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			throw new IllegalArgumentException("Validation failed for request body: " + bindingResult.getFieldError());
		}
		return ResponseEntity.ok(service.updateSpeciality(id, speciality));
	}

	@Override
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Boolean> deleteSpeciality(UUID id) {
		return ResponseEntity.ok(service.deleteSpeciality(id));
	}

	@Override
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
	public ResponseEntity<List<Speciality>> getSpecialities() {
		return ResponseEntity.ok(service.getSpecialities());
	}

	@Override
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
	public ResponseEntity<Speciality> getSpecialityById(UUID id) {
		return ResponseEntity.ok(service.getSpecialityById(id));
	}

	@Override
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
	public ResponseEntity<Speciality> getSpecialityByName(String name) {
		return ResponseEntity.ok(service.getSpecialityByName(name));
	}

	@Override
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
	public ResponseEntity<List<Speciality>> getSpecialitiesByArea(String area) {
		return ResponseEntity.ok(service.getSpecialitiesByArea(area));
	}

	@Override
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
	public ResponseEntity<List<Speciality>> getSpecialitiesByRegion(String region) {
		return ResponseEntity.ok(service.getSpecialitiesByRegion(region));
	}
}
