package com.example.studentmanagentsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.studentmanagentsystem.entity.Instructor;
import com.example.studentmanagentsystem.entity.repository.InstructorRepository;
import java.util.List;
@Service
public class InstructorService {
	@Autowired
	private InstructorRepository instructorRepo;
	
	public void save(Instructor i) {
		instructorRepo.save(i);
	}
	public List<Instructor> getAllInstructor(){
		return instructorRepo.findAll();
	}
	
	public Instructor getBookById(int id) {
		return instructorRepo.findById(id).get();
	}

	public void deleteById(int id) {
		instructorRepo.deleteById(id);
	}

}
