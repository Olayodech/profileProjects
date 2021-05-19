package com.shop.admin.states;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.shop.common.entity.Country;
import com.shop.common.entity.States;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class StateRepoTest {
	
	@Autowired
	TestEntityManager testEntityManager;
	
	@Autowired
	StatesRepository statesRepository;

	@Test
	public void createStates() {
		
		Integer countryId = 1;
		Country country = testEntityManager.find(Country.class, countryId);
		
		States states = statesRepository.save(new States("Abuja", country));
		States states2 = statesRepository.save(new States("Lagos", country));
		States states3 = statesRepository.save(new States("OSun", country));
		


		
	}
}
