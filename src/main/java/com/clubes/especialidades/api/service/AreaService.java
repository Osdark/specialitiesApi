package com.clubes.especialidades.api.service;

import com.clubes.especialidades.api.dao.Area;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface AreaService {
	Area createArea(Area area);

	boolean updateArea(UUID id, Area area);

	boolean deleteArea(UUID id);

	Area getArea(UUID id);

	List<Area> getAreas();

	Area getAreaByName(String name);
}
