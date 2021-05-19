package com.shop.admin.user;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;

import com.shop.common.entity.Role;
import com.shop.common.entity.User;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UsersRepositoryTest {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void TestCreateUserWithOneRole() {
		Role roleAdmin = entityManager.find(Role.class, 1);
		User user = new User();
		user.setUsername("firstuser");
		user.setEmail("a@example.com");
		user.setFirstName("Alex");
		user.setLastName("Benjamin");
		user.setPassword("hello");
		user.addRole(roleAdmin);
		
		User createdUser = userRepository.save(user);
		assertThat(createdUser.getId() > 0);
		
	}
	
	@Test
	public void TestCreateUserWithTwoRoles() {
		User user = new User();
		user.setUsername("seconduser");
		user.setEmail("b@example.com");
		user.setFirstName("Blackey");
		user.setLastName("Loans");
		user.setPassword("hello");
		
		Role addAsEditor = new Role(3);
		Role addAsAssistant = new Role(5);

		user.addRole(addAsEditor);
		user.addRole(addAsAssistant);
		User createdUser = userRepository.save(user);
		assertThat(createdUser.getId()).isGreaterThan(0);
		
	}
	
	@Test
	public void testListAllUsers() {
		Iterable<User> users = userRepository.findAll();
		assertThat(users.toString());
	}
	
	@Test
	public void testUpdateUser() {
		User user = new User();
		user = userRepository.findById(1).get();
		user.setEnabled(true);
		user.setEmail("blackey10");
	}
	
	@Test
	public void updateUserRole() {
		User user = new User();
		user = userRepository.findById(15).get();
		user.getRoles().remove(new Role(3));
		user.addRole(new Role(5));
		user.setEnabled(true);
	}
	
	@Test void testPassword () {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		Role roleAdmin = entityManager.find(Role.class, 1);

		User user = new User();
		user.setUsername("y@example.com");
		user.setEmail("y@example.com");
		user.setFirstName("Alex");
		user.setLastName("Benjamin");
		user.setPassword(passwordEncoder.encode("hellohello"));
		user.addRole(roleAdmin);
		user.setEnabled(true);

		User created = userRepository.save(user);
		created = userRepository.findById(user.getId()).get();
		assertThat(created.getPassword().equals("hellohello"));
	}

	@Test
	public void getUser() {
		User user = new User();
		user = userRepository.findById(14).get();
		assertThat(user.getPassword().equals("hellohello"));
	}
}
