package com.shop.admin.country;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shop.common.entity.Country;

@RestController
public class CountryRestController {

	@Autowired
	private CountryRepository countryRepository;
	
	@GetMapping("/countries/list")
	public List<Country> listAll(){
		return countryRepository.findAllByOrderByCountryNameAsc();
	}
	
	@PostMapping("/countries/save")
	public String saveCountry(@RequestBody Country country) {
	Country savedCountry = countryRepository.save(country);
	return String.valueOf(savedCountry.getCountryId());
	}
	
	@GetMapping("/countries/delete/{id}")
	public void delete(@PathVariable("id") Integer id) {
		countryRepository.deleteById(id);
	}
	
}
