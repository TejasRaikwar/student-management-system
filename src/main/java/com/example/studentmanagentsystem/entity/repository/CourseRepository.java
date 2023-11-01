package com.example.studentmanagentsystem.entity.repository;

import com.example.studentmanagentsystem.entity.Course;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Integer> {
	// You can add custom query methods here if needed
	List<Course> findAll();

	Course findByCourseTitle(String courseTitle);

}