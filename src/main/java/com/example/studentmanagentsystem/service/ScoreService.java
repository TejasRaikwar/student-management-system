package com.example.studentmanagentsystem.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.studentmanagentsystem.dto.ScoreDisplayDTO;
import com.example.studentmanagentsystem.entity.Course;
import com.example.studentmanagentsystem.entity.Score;
import com.example.studentmanagentsystem.entity.Student;
import com.example.studentmanagentsystem.entity.repository.CourseRepository;
import com.example.studentmanagentsystem.entity.repository.ScoreRepository;
import com.example.studentmanagentsystem.entity.repository.StudentRepository;

@Service
public class ScoreService {
	
	@Autowired
	private ScoreRepository scoreRepo;
	
	@Autowired
	private StudentRepository studentRepo;
	
	@Autowired
	private CourseRepository courseRepo;
	
	public List<ScoreDisplayDTO> getAllScores(){
		List<Score> scores = scoreRepo.findAll();
		List<ScoreDisplayDTO> scoreDTOs = new ArrayList<>();
		
		for(Score score : scores) {
			ScoreDisplayDTO scoreDTO = new ScoreDisplayDTO();
			
			Student student = studentRepo.findById(score.getStudent());
			Course course = courseRepo.findById(score.getCourse()).orElse(null);
			
			if(student != null) {
				scoreDTO.setStudentName(student.getFirstName()+" "+student.getLastName());
			}
			if(course != null) {
				scoreDTO.setCourse(course.getCourseTitle());
			}
			scoreDTOs.add(scoreDTO);
		}
		return scoreDTOs;
	}
	
	/*
	 public List<EnrollmentDisplayDTO> getAllEnrollmentsWithCourseAndInstructorDetails() {
		List<Enrollment> enrollments = enrollrepo.findAll();
		List<EnrollmentDisplayDTO> enrollmentDTOs = new ArrayList<>();

		for (Enrollment enrollment : enrollments) {
			
			EnrollmentDisplayDTO enrollmentDTO = new EnrollmentDisplayDTO();
			
			Course course = courseRepository.findById(enrollment.getCourse()).orElse(null);
			Instructor instructor = instructorRepository.findById(enrollment.getInstructor()).orElse(null);
			Student student = studentRepository.findById(enrollment.getStudent()).orElse(null);;
			if (course != null) {
				enrollmentDTO.setCourseId(enrollment.getCourse());
				enrollmentDTO.setCourseTitle(course.getCourseTitle());
			}

			if (instructor != null) {
				enrollmentDTO.setInstructorName(instructor.getFirstName() + " " + instructor.getLastName());
			}
			
			if(student != null) {
				enrollmentDTO.setStudentId(enrollment.getStudent());
				enrollmentDTO.setStudentName(student.getFirstName() + " " + student.getLastName());
				enrollmentDTO.setStudentMobile(student.getPhone());
			}

			enrollmentDTO.setEnrollmentDate(enrollment.getEnrollmentDate());
			enrollmentDTOs.add(enrollmentDTO);
		}

		return enrollmentDTOs;

	}
	 */
	
	
	
}
