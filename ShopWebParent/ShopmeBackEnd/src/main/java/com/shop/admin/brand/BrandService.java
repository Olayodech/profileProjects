package com.shop.admin.brand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.admin.brand.BrandRepository;
import com.shop.common.entity.Brands;

import antlr.collections.List;

@Service
public class BrandService {

	@Autowired
	private BrandRepository brandRepository;

	public Iterable<Brands> listBrand() {
		Iterable<Brands> brands = brandRepository.findAll();
		return brands;
	}
	
	
	public Brands saveBrand(Brands brands) {
		Brands saved = brandRepository.save(brands);
		return saved;
	}
	
	public Boolean checkDuplicate(String name) {
		Brands checked = brandRepository.findBrandByName(name);
		
		if(checked != null) {
			return true;
		}return false;
	}


	public java.util.List<Brands> listAllBrands() {
		return brandRepository.findByAll();
	}
	
	public Brands getCategoryBrand(Long id) {
		
		return brandRepository.findById(id).get();
	}
	
	
}
