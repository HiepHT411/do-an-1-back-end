package com.hoanghiep.da1.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hoanghiep.da1.entity.Product;
import com.hoanghiep.da1.entity.User;
import com.hoanghiep.da1.repository.ProductRepository;
import com.hoanghiep.da1.repository.UserRepository;
import com.hoanghiep.da1.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	public User addUserToDB(User user) {
		return userRepository.save(user);
	}
	
	@Override
	public User findUserByName(String name) throws UsernameNotFoundException{
		User user =  userRepository.findByUsername(name)
				.orElseThrow(() ->new UsernameNotFoundException("User not found with username: "+ name));
		return user;
	}

	@Override
	public User findUserById(int userId) {

		return userRepository.findById(userId).get();
	}

	@Override
	public User findUserByEmail(String email) {

		return userRepository.findByEmail(email);
	}

	@Override
	public List<User> findAllUsers() {

		return userRepository.findAllByOrderByIdAsc();
	}

	@Override
	public List<Product> getCart(List<Integer> productsIds) {

		return productRepository.findByIdIn(productsIds);
	}

	@Override
	public User updateProfile(String email, User user) {
		
		User userFromDb = userRepository.findByEmail(email);
		
        userFromDb.setUsername(user.getUsername());
        userFromDb.setAddress(user.getAddress());
        userFromDb.setPhoneNumber(user.getPhoneNumber());
        
        userRepository.save(userFromDb);
        
        return userFromDb;
	}
}
