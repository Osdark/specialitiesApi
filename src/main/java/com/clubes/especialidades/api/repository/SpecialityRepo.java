package com.clubes.especialidades.api.repository;

import com.clubes.especialidades.api.dao.Speciality;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpecialityRepo extends JpaRepository<Speciality, UUID> {
}
