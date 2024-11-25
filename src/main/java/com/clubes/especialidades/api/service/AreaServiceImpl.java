package com.clubes.especialidades.api.service;

import com.clubes.especialidades.api.dao.Area;
import com.clubes.especialidades.api.repository.AreaRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AreaServiceImpl implements AreaService {
	@Autowired
	private AreaRepo repo;

	@Override
	public Area createArea(Area area) {
		return repo.save(area);
	}

	@Override
	public boolean updateArea(UUID id, Area area) {
		repo.findById(id).ifPresentOrElse(
				existing -> {
					existing.setName(area.getName());
					existing.setColor(area.getColor());
					repo.save(existing);
				},
				() -> {
					throw new IllegalArgumentException("Area with id " + id + " not found");
				}
		);
		return true;
	}

	@Override
	public boolean deleteArea(UUID id) {
		repo.deleteById(id);
		return true;
	}

	@Override
	public Area getArea(UUID id) {
		return repo.findById(id).orElseThrow(EntityNotFoundException::new);
	}

	@Override
	public List<Area> getAreas() {
		return repo.findAll();
	}

	@Override
	public Area getAreaByName(String name) {
		return repo.findByNameIgnoreCase(name).orElseThrow(EntityNotFoundException::new);
	}
}
