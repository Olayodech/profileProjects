package com.shop.admin.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.shop.common.entity.Role;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class RoleRepositoryTest {
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Test
	public void testCreateFirstRole() {
		Role role = new Role("Admin", "Manage everything");
		Role savedrole = roleRepository.save(role);
		assertThat(savedrole.getId() > 0);
	
	}
	
	@Test
	public void testCreateAdditionalRole() {
		Role sales = new Role("SalesPerson", "Manage Prdocuct price, customers and shipping");
		Role roleEditor = new Role("Editor", "Manage categories, brands,products and articles");
		Role roleShipper = new Role("Shipper", "view product, view order, update and order status");
		Role roleAssistant = new Role("Assistant", "Manage questions and review");

		roleRepository.saveAll(List.of(sales, roleEditor, roleShipper, roleAssistant));
	
	}
	
}
