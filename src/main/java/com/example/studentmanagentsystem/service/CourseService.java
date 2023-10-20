package com.example.studentmanagentsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import com.example.studentmanagentsystem.entity.Course;
import com.example.studentmanagentsystem.entity.repository.CourseRepository;

@Service
public class CourseService {
	@Autowired
	private CourseRepository courserepo;
	
	public void save(Course c) {
		courserepo.save(c);
	}
	
	public List<Course> getAllCourse(){
		return courserepo.findAll();
	}
	
	public Course getBookById(int id) {
		return courserepo.findById(id).get();
	}

	public void deleteById(int id) {
		courserepo.deleteById(id);
	}
	
	

}
