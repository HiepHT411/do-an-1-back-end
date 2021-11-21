package com.hoanghiep.da1.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.hoanghiep.da1.entity.Feedback;
import com.hoanghiep.da1.service.FeedbackService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Slf4j
public class FeedbackController {
	
	private final FeedbackService feedbackService;
	
	@GetMapping("/admin/{adminId}/feedbacks")
	@PreAuthorize("hasRole('ADMIN')")
	public List<Feedback> retrieveAllFeedbacksOfSpecificUser(@PathVariable(value = "adminId") int adminId){
		log.info("Get all feedbacks received from user-"+adminId);
		
		return feedbackService.getAllFeedbacksOfSpecificUser(adminId);
	}
	
	@PostMapping(path = "/user/{userId}/feedbacks")											//ok
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<Feedback> saveFeedbackFromUser(@PathVariable int userId, @RequestBody Feedback feedback){
		log.info("Save user's feedback to database: {}");
		
		Feedback savedFeedback = feedbackService.saveFeedback(userId, feedback);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
							.buildAndExpand(savedFeedback.getId()).toUri();
		
		return ResponseEntity.created(location).build();
//		return ResponseEntity.ok(feedbackService.saveFeedback(userId, feedback));
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/all/feedbacks")
	public List<Feedback> retrieveAllFeedbacks(){
		log.info("retrieving all feedbacks from database");
		
		return feedbackService.getAllFeedbacksFromDatabase();
		
	}
	
}
