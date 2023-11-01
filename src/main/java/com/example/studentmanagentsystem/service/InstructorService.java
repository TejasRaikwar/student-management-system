 package com.example.studentmanagentsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.studentmanagentsystem.entity.Instructor;
import com.example.studentmanagentsystem.entity.repository.InstructorRepository;
import java.util.List;
@Service
public class InstructorService {
	
	private String InstructorName;
	
	private int InstructorId;

    public int getInstructorId() {
		return InstructorId;
	}

	public void setInstructorId(int instructorId) {
		InstructorId = instructorId;
	}

	public String getInstructorName() {
		return InstructorName;
	}

	public void setInstructorName(String instructorName) {
		InstructorName = instructorName;
	}

	@Autowired
    private InstructorRepository instructorRepository;

    public List<Instructor> getInstructorsByCourse(int courseId) {
        return instructorRepository.findInstructorsByCourse(courseId);
    }
}

