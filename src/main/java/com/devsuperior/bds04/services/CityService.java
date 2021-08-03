package com.devsuperior.bds04.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.devsuperior.bds04.dto.CityDTO;
import com.devsuperior.bds04.entities.City;
import com.devsuperior.bds04.repositories.CityRepository;

@Service
public class CityService {

	@Autowired
	private CityRepository repository;
	 
	@Transactional(readOnly = true)
	public List<CityDTO> findAll() {
		List<City> list = repository.findAll(Sort.by("name"));
		List<CityDTO> dto = list.stream().map(city -> new CityDTO(city)).collect(Collectors.toList());
		return dto;
	}
	
	@Transactional
	public CityDTO insert(CityDTO cityDTO) {
		City city = new City();
		city.setName(cityDTO.getName());
		city = repository.save(city);
		CityDTO dto = new CityDTO(city);
		return dto;
	}
	
}
