package com.shop.admin.user;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shop.common.entity.Role;
import com.shop.common.entity.User;


@Service
@Transactional
public class UserServices {
	public static final int users_Page = 4;
	@Autowired 
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public List<User> listAll(){
		return (List<User>) userRepository.findAll();
	}
	
	public List<Role> listRoles(){
		return (List<Role>) roleRepository.findAll();
	}
	
	public User saveUser(User user) {		
		encodePassword(user);
		return userRepository.save(user);
	}
	
	private void encodePassword(User user) {
		System.out.println("password to encode" + user.getPassword());
		String encodePassword = passwordEncoder.encode(user.getPassword());
		System.out.println("password encode" + encodePassword);
		user.setPassword(encodePassword);
	}
	
	public boolean isEmailUnique(Integer Id, String email) {
		System.out.println(email);
		User usercheck = userRepository.findUserByEmail(email);
		
		if(usercheck == null) {
			return true;
		}
		
		boolean isCreatenew = (Id==null);
		
		if(isCreatenew) {
			if (usercheck != null) {
				return false;
			}
		}else {
			if(usercheck.getId() != Id) {
				return false;
			}
		}
		return true;
	}
	
	public User getUserId(Integer id) throws UserNotFoundException {
		try {
		return userRepository.findById(id).get();
		}catch (NoSuchElementException ex) {
			throw new UserNotFoundException("User not found");
		}
	}
	
	public void deleteUser(Integer Id) throws UserNotFoundException {
		Long countId = userRepository.countById(Id);
		if(countId == null || countId == 0) {
			throw new UserNotFoundException("Could not find any user");
		}
		userRepository.deleteById(Id);
	}

	public Page<User> listByPage(int pageNum, String sortField, String sortDirection, String keyword) {
		Sort sort = Sort.by(sortField);
		sort = sortDirection.equals("asc") ? sort.ascending() : sort.descending();

		
		Pageable pageable = PageRequest.of(pageNum -1, users_Page, sort);
		
		if(keyword != null) {
			return userRepository.findAll(keyword, pageable);
		}
		

		return userRepository.findAll(pageable);
	}

    public User getUserByEmail(String email) {
		return userRepository.findUserByEmail(email);
    }

    public User updateAccount(User userform){
		User user = userRepository.findById(userform.getId()).get();

		if(!userform.getPassword().isEmpty()) {
			user.setPassword(userform.getPassword());
			encodePassword(user);
		}
			if(userform.getPhotos() != null){
				user.setPhotos(user.getPhotos());
			}

			user.setFirstName(userform.getFirstName());
			user.setLastName(userform.getLastName());

			return userRepository.save(user);

	}
}
