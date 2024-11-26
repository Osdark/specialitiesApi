package com.clubes.especialidades.api.controller;

import com.clubes.especialidades.api.dao.Speciality;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/honor")
public interface SpecialityController {
	@PostMapping("/new")
	ResponseEntity<Speciality> createSpeciality(@RequestBody @Valid Speciality speciality, BindingResult bindingResult);

	@PutMapping("/update/{id}")
	ResponseEntity<Boolean> updateSpeciality(@PathVariable UUID id, @RequestBody @Valid Speciality speciality, BindingResult bindingResult);

	@DeleteMapping("/delete/{id}")
	ResponseEntity<Boolean> deleteSpeciality(UUID id);

	@GetMapping("/all")
	ResponseEntity<List<Speciality>> getSpecialities();

	@GetMapping("/{id}")
	ResponseEntity<Speciality> getSpecialityById(@PathVariable UUID id);

	@GetMapping("/name/{name}")
	ResponseEntity<Speciality> getSpecialityByName(@PathVariable String name);

	@GetMapping("/area/{area}")
	ResponseEntity<List<Speciality>> getSpecialitiesByArea(@PathVariable String area);

	@GetMapping("/region/{region}")
	ResponseEntity<List<Speciality>> getSpecialitiesByRegion(@PathVariable String region);
}
