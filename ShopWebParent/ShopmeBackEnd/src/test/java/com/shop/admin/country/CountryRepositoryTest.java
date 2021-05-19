package com.shop.admin.country;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.shop.common.entity.Country;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class CountryRepositoryTest {
	
	@Autowired
	private CountryRepository countryRepository;

	@Test
	public void testCreateCountry() {
		Country country = countryRepository.save(new Country("Nigeria", "Ng"));
		assertThat(country).isNotNull();
		assertThat(country.getCountryId()).isGreaterThan(0);
	}
	
	@Test
	public void testCreateMultipleCountry() {
		Country country = countryRepository.save(new Country("Canada", "CA"));
		Country country2 = countryRepository.save(new Country("USA", "US"));
		Country country3 = countryRepository.save(new Country("China", "CN"));
		Country country4 = countryRepository.save(new Country("United Kingdom", "UK"));


	}
}
