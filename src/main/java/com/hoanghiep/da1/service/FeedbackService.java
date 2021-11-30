package com.hoanghiep.da1.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hoanghiep.da1.entity.Feedback;
import com.hoanghiep.da1.entity.User;
import com.hoanghiep.da1.exception.UserNotFoundException;
import com.hoanghiep.da1.repository.FeedbackRepository;
import com.hoanghiep.da1.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FeedbackService {

	@Autowired
	private final FeedbackRepository feedbackRepository;
	
	@Autowired 
	private UserRepository userRepository;
	
	public List<Feedback> getAllFeedbacksOfSpecificUser(int id){
		User user = userRepository.findById(id)
							.orElseThrow(()-> new UserNotFoundException("User not found by id-"+id));
		
		return user.getFeedbacks();
	}
	
	public Feedback saveFeedback(int userId, Feedback feedback) {

		User user = userRepository.findById(userId)
								.orElseThrow(()-> new UserNotFoundException("User not found id-"+userId));
		
		feedback.setUser(user);
		
		DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss dd-MM-yyyy");
		Date date = Calendar.getInstance().getTime();
		String feedbackDate = dateFormat.format(date);
		feedback.setDate(feedbackDate);
		
		Feedback savedFeedback = feedbackRepository.save(feedback);
		
		return savedFeedback;
	}
	
	public List<Feedback> getAllFeedbacksFromDatabase(){
		
		return feedbackRepository.findAll();
	}
	
}
