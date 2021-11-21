package com.hoanghiep.da1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hoanghiep.da1.entity.User;
import com.hoanghiep.da1.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@Slf4j
public class UserController {
	
	@Autowired
	private final UserService userService;
	
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
		return userService.findByUsername("Hiep");
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
}
