package com.shop.admin.currency;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.shop.common.entity.Currency;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class CurrencyTest {
	
	@Autowired
	CurrencyRepository currencyRepository;
	@Test
	public void createCurrency() {
		Currency usd = new Currency("United States Dollar", "$", "USD");
		Currency pound = new Currency("British Pound", "£", "GBP");
		Currency yen = new Currency("Japanese Yen", "¥", "JPY");
		Currency euro = new Currency("Euro", "€", "EUR");
		
		currencyRepository.save(usd);
		currencyRepository.save(pound);
		currencyRepository.save(yen);
		currencyRepository.save(euro);
	}
	
	@Test
	public void testListAllByOrderByName() {
		List<Currency> currencies = currencyRepository.findAllByOrderByCurrencyNameAsc();
		currencies.forEach(item -> {
			System.out.println(item.getCurrencyName());
		});
		assertThat(currencies.size()).isGreaterThan(0);
	}

}
