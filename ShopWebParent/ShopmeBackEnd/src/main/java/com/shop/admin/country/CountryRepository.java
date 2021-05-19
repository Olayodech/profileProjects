package com.shop.admin.country;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.shop.common.entity.Country;

@Repository
public interface CountryRepository extends CrudRepository<Country, Integer>{

	List<Country> findAllByOrderByCountryNameAsc(); //custom JPA
}
