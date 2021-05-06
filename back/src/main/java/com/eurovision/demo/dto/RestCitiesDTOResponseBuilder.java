package com.eurovision.demo.dto;

import com.eurovision.demo.domain.Cities;

import java.util.ArrayList;
import java.util.List;

public class RestCitiesDTOResponseBuilder {

	private RestCitiesDTOResponseBuilder() {}

	public static List<CitiesDTO> createCitiesDTOResponseList(List<Cities> entities) {
		List<CitiesDTO> dtoList = new ArrayList<>();
		for(Cities instance : entities) {
			dtoList.add(RestCitiesDTOResponseBuilder.createCitiesDTOResponse(instance));
		}
		return dtoList;
	}
	
	public static CitiesDTO createCitiesDTOResponse(Cities entity) {
		return CitiesDTO
				.builder()
				.withCities(entity)
				.build();
	}
	
}