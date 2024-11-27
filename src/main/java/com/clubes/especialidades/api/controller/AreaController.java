package com.clubes.especialidades.api.controller;

import com.clubes.especialidades.api.dao.Area;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/area")
public interface AreaController {
	@PostMapping("/new")
	ResponseEntity<Area> createArea(@RequestBody @Valid Area area, BindingResult bindingResult);

	@PutMapping("/update/{id}")
	ResponseEntity<Boolean> updateArea(@PathVariable UUID id, @RequestBody @Valid Area area, BindingResult bindingResult);

	@DeleteMapping("/delete/{id}")
	ResponseEntity<Boolean> deleteArea(UUID id);

	@GetMapping("/all")
	ResponseEntity<List<Area>> getAreas();

	@GetMapping("/{id}")
	ResponseEntity<Area> getAreaById(@PathVariable UUID id);

	@GetMapping("/name/{name}")
	ResponseEntity<Area> getAreaByName(@PathVariable String name);
}
