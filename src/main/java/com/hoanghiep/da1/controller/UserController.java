package com.hoanghiep.da1.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hoanghiep.da1.entity.Order;
import com.hoanghiep.da1.entity.Product;
import com.hoanghiep.da1.entity.User;
import com.hoanghiep.da1.entity.UserDetailsImpl;
import com.hoanghiep.da1.exception.InputFieldException;
import com.hoanghiep.da1.mapper.OrderMapper;
import com.hoanghiep.da1.payload.OrderRequest;
import com.hoanghiep.da1.service.OrderService;
import com.hoanghiep.da1.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Slf4j
public class UserController {
	
	@Autowired
	private final UserService userService;
	
	@Autowired
	private final OrderService orderService;
	
	@Autowired
	private final OrderMapper orderMapper;
	
	// signup in JwtAuthenticationController
//	@PostMapping(path = "/signup")	
//	public ResponseEntity<?> saveUser(@RequestBody User user) {
//		if(userRepository.existsByName(user.getName())) {
//			return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken"));
//		}
//		if(userRepository.existsByEmail(user.getEmail())) {
//			return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use"));
//		}
//		log.info("Add user to database "+ user.getName());
//		userService.addUserToDB(user);
//		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
//	}
	
	@GetMapping(path = "/test/findByUsername")
	public User Test(){
		log.info("Testing out normal api");
		return userService.findUserByName("Hiep");
	}
	
	@GetMapping("/test/all")
	public String allAccess() {
		log.info("Testing out api without any authentication");
		return "publicContent";
	}
	
	@GetMapping("/test/user")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public String userAccess() {
		log.info("Testing out api for all authorization");
		return "confirmedUserRole";
	}

	@GetMapping("/test/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String adminAccess() {
		log.info("Testing out api for role admin");
		return "confirmedAdminRole";
	}
	
	
	@PostMapping("/users/cart")
	@PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<Product>> getUserCart(@RequestBody List<Integer> productsIds) {	//JSON: [1,2,3]
        return ResponseEntity.ok(userService.getCart(productsIds));
    }

    @GetMapping("/users/orders")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<Order>> getUserOrders(@AuthenticationPrincipal UserDetailsImpl userPrincipal ) {
    	log.info("Get all user's orders by email");
        return ResponseEntity.ok(orderService.findOrderByEmail(userPrincipal.getEmail()));
    }

    @PostMapping("/users/orders")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Order> postOrder(@Valid @RequestBody OrderRequest orderRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InputFieldException(bindingResult);
        } else {
        	//System.out.println("Dat hang ok");
            return ResponseEntity.ok(orderMapper.postOrder(orderRequest));
        }
    }
}
