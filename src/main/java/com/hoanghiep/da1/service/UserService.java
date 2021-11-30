package com.hoanghiep.da1.service;

import java.util.List;

import com.hoanghiep.da1.entity.Product;
import com.hoanghiep.da1.entity.User;

public interface UserService {
	
	
	User findUserById(int userId);

	User findUserByName(String username);
	
    User findUserByEmail(String email);

    List<User> findAllUsers();

    List<Product> getCart(List<Integer> productsIds);

    User updateProfile(String email, User user);
	
}
