package com.clubes.especialidades.api.service;

import com.clubes.especialidades.api.dao.Area;

import java.util.List;
import java.util.UUID;

public interface AreaService {
	Area createArea(Area area);

	boolean updateArea(UUID id, Area area);

	boolean deleteArea(UUID id);

	Area getArea(UUID id);

	List<Area> getAreas();

	Area getAreaByName(String name);
}
