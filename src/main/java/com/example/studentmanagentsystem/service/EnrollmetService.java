package com.example.studentmanagentsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.studentmanagentsystem.entity.Enrollment;
import com.example.studentmanagentsystem.entity.repository.EnrollmentRepository;
import java.util.List;
@Service
public class EnrollmetService {
	@Autowired
	private EnrollmentRepository enrollrepo;
	
	public void save(Enrollment e) {
		enrollrepo.save(e);		
	}
	
	public List<Enrollment> getAllEnrollment(){
		return enrollrepo.findAll();
	}
	
	public Enrollment getEnrollById(int id) {
		return enrollrepo.findById(id).get();
	}
	
	public void deleteById(int id) {
		enrollrepo.deleteById(id);
	}

}
