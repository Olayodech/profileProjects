package com.shop.admin.currency;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.shop.common.entity.Currency;

@Repository
public interface CurrencyRepository extends CrudRepository<Currency, Integer> {

	List<Currency> findAllByOrderByCurrencyNameAsc();
}
