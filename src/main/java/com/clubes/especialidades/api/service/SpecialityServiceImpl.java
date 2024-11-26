package com.clubes.especialidades.api.service;

import com.clubes.especialidades.api.dao.Speciality;
import com.clubes.especialidades.api.repository.SpecialityRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SpecialityServiceImpl implements SpecialityService {
	@Autowired
	private SpecialityRepo repo;

	@Override
	public Speciality createSpeciality(Speciality speciality) {
		return repo.save(speciality);
	}

	@Override
	public Boolean updateSpeciality(UUID id, Speciality speciality) {
		repo.findById(id).ifPresentOrElse(
				existing -> {
					existing.setName(speciality.getName());
					existing.setImage(speciality.getImage());
					existing.setPdf(speciality.getPdf());
					existing.setArea(speciality.getArea());
					existing.setRegions(speciality.getRegions());
					repo.save(speciality);
				},
				EntityNotFoundException::new
		);
		return true;
	}

	@Override
	public Boolean deleteSpeciality(UUID id) {
		repo.deleteById(id);
		return true;
	}

	@Override
	public List<Speciality> getSpecialities() {
		return repo.findAll();
	}

	@Override
	public Speciality getSpecialityById(UUID id) {
		return repo.findById(id).orElseThrow(EntityNotFoundException::new);
	}

	@Override
	public Speciality getSpecialityByName(String name) {
		return repo.findByNameIgnoreCase(name).orElseThrow(EntityNotFoundException::new);
	}

	@Override
	public List<Speciality> getSpecialitiesByArea(String area) {
		return repo.findByAreaNameIgnoreCase(area);
	}

	@Override
	public List<Speciality> getSpecialitiesByRegion(String region) {
		return repo.findByRegionsNameIgnoreCase(region);
	}
}
