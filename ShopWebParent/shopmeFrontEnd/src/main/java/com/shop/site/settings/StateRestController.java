package com.shop.site.settings;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shop.common.entity.Country;
import com.shop.common.entity.States;
import com.shop.common.entity.StatesDTO;

@RestController
public class StateRestController {

	@Autowired
	private StatesRepository statesRepository;
	
	@GetMapping("/states/listbycountry/{countryId}")
	public List<StatesDTO> statebyCountry(@PathVariable("countryId") Integer countryId){
//		List<States> listStates = statesRepository.findAllByOrderByStateNameAsc(new Country(countryId));
		List<States> listStates = statesRepository.findByCountryOrderByStateNameAsc(new Country(countryId));
		List<StatesDTO> result = new ArrayList<>();
		
		for (States states : listStates) {
			result.add(new StatesDTO(states.getStateId(), states.getStateName()));
		}
		
		return result;
	}
}
