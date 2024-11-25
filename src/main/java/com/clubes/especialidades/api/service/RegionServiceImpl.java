package com.clubes.especialidades.api.service;

import com.clubes.especialidades.api.dao.Region;
import com.clubes.especialidades.api.repository.RegionRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RegionServiceImpl implements RegionService{
	@Autowired
	private RegionRepo repo;

	@Override
	public Region createRegion(Region region) {
		return repo.save(region);
	}

	@Override
	public boolean updateRegion(UUID id, Region region) {
		repo.findById(id).ifPresentOrElse(
				existing -> {
					existing.setName(region.getName());
					existing.setAbbreviation(region.getAbbreviation());
					repo.save(region);
				},
				() -> {
					throw new EntityNotFoundException("Region not found");
				}
		);
		return true;
	}

	@Override
	public boolean deleteRegion(UUID id) {
		repo.deleteById(id);
		return true;
	}

	@Override
	public Region getRegion(UUID id) {
		return repo.findById(id).orElseThrow(EntityNotFoundException::new);
	}

	@Override
	public List<Region> getRegions() {
		return repo.findAll();
	}

	@Override
	public Region getRegionByName(String name) {
		return repo.findByNameIgnoreCase(name).orElseThrow(EntityNotFoundException::new);
	}

	@Override
	public Region getRegionByAbbreviation(String abbreviation) {
		return repo.findByAbbreviationIgnoreCase(abbreviation).orElseThrow(EntityNotFoundException::new);
	}
}
