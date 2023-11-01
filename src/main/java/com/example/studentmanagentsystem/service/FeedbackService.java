package com.example.studentmanagentsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import com.example.studentmanagentsystem.entity.Feedback;
import com.example.studentmanagentsystem.entity.repository.FeedbackRepository;
import java.util.List;

public class FeedbackService {
	@Autowired
	private FeedbackRepository feedbackRepository;

	// Method to retrieve feedbacks by instructor name
	public List<Feedback> getFeedbacksByInstructorName(String instructorName) {
		return feedbackRepository.findByInstructorName(instructorName);
	}
}
