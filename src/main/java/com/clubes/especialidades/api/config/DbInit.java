package com.clubes.especialidades.api.config;

import com.clubes.especialidades.api.dao.Area;
import com.clubes.especialidades.api.dao.Region;
import com.clubes.especialidades.api.dao.sec.User;
import com.clubes.especialidades.api.repository.AreaRepo;
import com.clubes.especialidades.api.repository.RegionRepo;
import com.clubes.especialidades.api.repository.UserRepo;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class DbInit {
	@Autowired
	private AreaRepo areaRepo;
	@Autowired
	private RegionRepo regionRepo;
	@Autowired
	private UserRepo userRepo;

	@PostConstruct
	@Transactional
	private void populateDB() {
		log.info("Populating DB with initial data regions and areas");
		populateAreaTable();
		populateRegionTable();
		populateUsersTable();
	}

	private void populateUsersTable() {
		log.info("Populating users table");
		List<User> users = List.of(
				new User("admin", "$2a$10$F.09JxUompOXkbMGrAshWeSYs90YppZcG8IALnKwTjlgSiaomrawC", "ADMIN"),
				new User("user", "$2a$10$F.09JxUompOXkbMGrAshWeSYs90YppZcG8IALnKwTjlgSiaomrawC", "USER")
		);
		users.stream()
				.map(user -> userRepo.findByUsername(user.getUsername()).orElse(user))
				.forEach(userRepo::save);
	}

	private void populateRegionTable() {
		log.info("Populating region table");
		List<Region> regions = List.of(
				new Region("Norte", "DNA"),
				new Region("Inter", "DIA"),
				new Region("Sur", "DSA")
		);
		regions.stream()
				.map(region -> regionRepo.findByAbbreviationIgnoreCase(region.getAbbreviation()).orElse(region))
				.forEach(regionRepo::save);
	}

	private void populateAreaTable() {
		log.info("Populating area table");
		List<Area> areas = List.of(
				new Area("Doméstica", "#f29005, #e5d500"),
				new Area("Manual", "#649fc5"),
				new Area("Profesional", "#ed1d24"),
				new Area("Misionera", "#3a3279, #3a3279"),
				new Area("Naturaleza", "#ffffff"),
				new Area("Agrícola", "#662d13, #170f08"),
				new Area("Ciencia y Salud", "#602369"),
				new Area("Recreación", "#119b48"),
				new Area("ADRA", "#4b3c81")
		);
		areas.stream()
				.map(area -> areaRepo.findByNameIgnoreCase(area.getName()).orElse(area))
				.forEach(areaRepo::save);
	}
}

