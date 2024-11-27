package com.clubes.especialidades.api.service;

import com.clubes.especialidades.api.dao.Region;
import com.clubes.especialidades.api.dao.Speciality;
import com.clubes.especialidades.api.dao.SpecialityCSV;
import com.clubes.especialidades.api.repository.SpecialityRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SpecialityServiceImpl implements SpecialityService {
	@Autowired
	private SpecialityRepo repo;
	@Autowired
	private CsvReaderService csvReaderService;
	@Autowired
	private AreaService areaService;
	@Autowired
	private RegionService regionService;

	@Override
	@Transactional
	public String batchLoadSpecialities(MultipartFile file) throws IOException {
		List<SpecialityCSV> specialities = csvReaderService.readSpecialities(file);
		specialities.stream()
				.map(item -> {
					List<String> itemRegions = Arrays.stream(item.getRegions().split(";")).toList();
					Set<Region> regions = itemRegions.stream().map(regionService::getRegionByAbbreviation).collect(Collectors.toSet());
					Speciality speciality = new Speciality();
					if (repo.existsByName(item.getName())) {
						speciality = repo.findByNameIgnoreCase(item.getName()).orElseThrow(EntityNotFoundException::new);
					}
					speciality.setName(item.getName());
					speciality.setImage(item.getImagePath());
					speciality.setArea(areaService.getAreaByName(item.getArea()));
					speciality.setRegions(regions);
					speciality.setPdf(item.getPdfPath());
					return speciality;
				})
				.forEach(repo::save);
		return "Info loaded successfully";
	}

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
