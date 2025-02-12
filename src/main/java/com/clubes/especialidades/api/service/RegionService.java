package com.clubes.especialidades.api.service;

import com.clubes.especialidades.api.dao.Region;

import java.util.List;
import java.util.UUID;

public interface RegionService {
	Region createRegion(Region region);

	boolean updateRegion(UUID id, Region region);

	boolean deleteRegion(UUID id);

	Region getRegion(UUID id);

	List<Region> getRegions();

	Region getRegionByName(String name);

	Region getRegionByAbbreviation(String abbreviation);
}
