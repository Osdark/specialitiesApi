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
import java.util.UUID;

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
		User user = new User(1L, "user", "$2a$10$F.09JxUompOXkbMGrAshWeSYs90YppZcG8IALnKwTjlgSiaomrawC", true, "ROLE_ADMIN");
		userRepo.save(user);
	}

	private void populateRegionTable() {
		log.info("Populating region table");
		List<Region> regions = List.of(
				new Region(UUID.fromString("90f529a8-ae57-40e2-9af5-7e45ebe7d653"), "Norte", "DNA"),
				new Region(UUID.fromString("6dcf8726-f24f-47a9-abb9-e352589d8b02"), "Inter", "DIA"),
				new Region(UUID.fromString("ba5752ac-0098-4095-b632-9be730ef3580"), "Sur", "DSA")
		);
		regionRepo.saveAll(regions);
	}

	private void populateAreaTable() {
		log.info("Populating area table");
		List<Area> areas = List.of(
				new Area(UUID.fromString("2ccb3298-f181-41af-a05a-b34a0703766f"), "Doméstica", "#f29005, #e5d500"),
				new Area(UUID.fromString("12752192-7ad4-4bf9-916a-e7030ff7a23a"), "Manual", "#649fc5"),
				new Area(UUID.fromString("0f41bc21-c26a-4e61-af93-270c4235166c"), "Profesional", "#ed1d24"),
				new Area(UUID.fromString("1ee3e6ab-2983-4dc7-a9a3-21104a817aa8"), "Misionera", "#3a3279, #3a3279"),
				new Area(UUID.fromString("b76b6ed7-ed8e-4d24-bbeb-5f2a55f4086f"), "Naturaleza", "#ffffff"),
				new Area(UUID.fromString("6313c045-c787-4a48-b0df-58159fdf75fe"), "Agrícola", "#662d13, #170f08"),
				new Area(UUID.fromString("b96b3925-5a6f-4c4d-812d-851a8b502d65"), "Ciencia y Salud", "#602369"),
				new Area(UUID.fromString("4e58407f-0cd0-4654-a12b-0d4899a6e1bd"), "Recreación", "#119b48"),
				new Area(UUID.fromString("06080a80-6eba-44c6-ba8a-689b0b299f83"), "ADRA", "#4b3c81")
		);
		areaRepo.saveAll(areas);
	}
}

