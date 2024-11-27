package com.clubes.especialidades.api.service;

import com.clubes.especialidades.api.dao.SpecialityCSV;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

@Service
public class CsvReaderService {
	public List<SpecialityCSV> readSpecialities(MultipartFile file) throws IOException {
		List<SpecialityCSV> specialities;
		try (Reader reader = new InputStreamReader(file.getInputStream())) {
			CsvToBean<SpecialityCSV> csvToBean = new CsvToBeanBuilder<SpecialityCSV>(reader).withType(SpecialityCSV.class).withIgnoreLeadingWhiteSpace(true).build();
			specialities = csvToBean.parse();
		}
		return specialities;
	}
}
