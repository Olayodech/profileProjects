package com.shop.admin.states;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.shop.common.entity.Country;
import com.shop.common.entity.States;

@Repository
public interface StatesRepository extends CrudRepository<States, Integer> {
//	List<States> findAllByOrderByStateNameAsc(Country country);
	List<States> findByCountryOrderByStateNameAsc(Country country);
	
//	List<State> findByCountryOrderByNameAsc(Country country);



	
}
