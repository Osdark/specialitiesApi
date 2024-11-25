package com.clubes.especialidades.api.controller;

import com.clubes.especialidades.api.dao.Requirement;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/requirement")
public interface RequirementController {
	@PostMapping("/new")
	ResponseEntity<Requirement> createRequirement(@RequestBody @Valid Requirement requirement, BindingResult bindingResult);

	@PutMapping("/update/{id}")
	ResponseEntity<Boolean> updateRequirement(@PathVariable UUID id, @RequestBody @Valid Requirement requirement, BindingResult bindingResult);

	@DeleteMapping("/delete/{id}")
	ResponseEntity<Boolean> deleteRequirementById(UUID id);

	@GetMapping("/all")
	ResponseEntity<List<Requirement>> getRequirements();

	@GetMapping("/{id}")
	ResponseEntity<Requirement> getRequirementById(@PathVariable UUID id);

	@GetMapping("/order/{orderNumber}")
	ResponseEntity<Requirement> getRequirementByOrderNumber(@PathVariable int orderNumber);
}
