package com.clubes.especialidades.api.service;

import com.clubes.especialidades.api.dao.Speciality;

import java.util.List;
import java.util.UUID;

public interface SpecialityService {
	Speciality createSpeciality(Speciality speciality);

	Boolean updateSpeciality(UUID id, Speciality speciality);

	Boolean deleteSpeciality(UUID id);

	List<Speciality> getSpecialities();

	Speciality getSpecialityById(UUID id);

	Speciality getSpecialityByName(String name);

	List<Speciality> getSpecialitiesByArea(String area);

	List<Speciality> getSpecialitiesByRegion(String region);
}
