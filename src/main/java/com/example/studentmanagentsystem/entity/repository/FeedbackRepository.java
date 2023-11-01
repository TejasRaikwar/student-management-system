package com.example.studentmanagentsystem.entity.repository;

import com.example.studentmanagentsystem.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Integer>
{
	List<Feedback> findByStudentid(int studentId);
	
	List<Feedback> findByInstructorName(String instructorName);
}

