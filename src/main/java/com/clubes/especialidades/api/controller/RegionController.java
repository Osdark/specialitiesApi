package com.clubes.especialidades.api.controller;

import com.clubes.especialidades.api.dao.Region;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/region")
public interface RegionController {
	@PostMapping("/new")
	ResponseEntity<Region> createRegion(@RequestBody @Valid Region region, BindingResult bindingResult);

	@PutMapping("/update/{id}")
	ResponseEntity<Boolean> updateRegion(@PathVariable UUID id, @RequestBody @Valid Region region, BindingResult bindingResult);

	@DeleteMapping("/delete/{id}")
	ResponseEntity<Boolean> deleteRegion(UUID id);

	@GetMapping("/all")
	ResponseEntity<List<Region>> getRegions();

	@GetMapping("/{id}")
	ResponseEntity<Region> getRegionById(@PathVariable UUID id);

	@GetMapping("/name/{name}")
	ResponseEntity<Region> getRegionByName(@PathVariable String name);

	@GetMapping("/abbreviation/{abbreviation}")
	ResponseEntity<Region> getRegionByAbbreviation(@PathVariable String abbreviation);
}
