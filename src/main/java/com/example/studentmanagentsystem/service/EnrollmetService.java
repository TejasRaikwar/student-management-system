package com.example.studentmanagentsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.studentmanagentsystem.dto.EnrollmentDisplayDTO;
import com.example.studentmanagentsystem.entity.Course;
import com.example.studentmanagentsystem.entity.Enrollment;
import com.example.studentmanagentsystem.entity.Instructor;
import com.example.studentmanagentsystem.entity.repository.CourseRepository;
import com.example.studentmanagentsystem.entity.repository.EnrollmentRepository;
import com.example.studentmanagentsystem.entity.repository.InstructorRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class EnrollmetService {

	@Autowired
	private EnrollmentRepository enrollrepo;

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private InstructorRepository instructorRepository;

	public void save(Enrollment e) {
		enrollrepo.save(e);
	}

	public List<Enrollment> getAllEnrollment() {
		return enrollrepo.findAll();
	}

	public Enrollment getEnrollById(int id) {
		return enrollrepo.findById(id).get();
	}

	public void deleteById(int id) {
		enrollrepo.deleteById(id);
	}

	public List<Enrollment> getEnrollmentsByStudent(int studentId) {
		return enrollrepo.findByStudent(studentId);
	}

	public List<EnrollmentDisplayDTO> getAllEnrollmentsWithCourseAndInstructorDetails() {
		List<Enrollment> enrollments = enrollrepo.findAll();
		List<EnrollmentDisplayDTO> enrollmentDTOs = new ArrayList<>();

		for (Enrollment enrollment : enrollments) {
			EnrollmentDisplayDTO enrollmentDTO = new EnrollmentDisplayDTO();
			Course course = courseRepository.findById(enrollment.getCourse()).orElse(null);
			Instructor instructor = instructorRepository.findById(enrollment.getInstructor()).orElse(null);
			if (course != null) {
				enrollmentDTO.setCourseTitle(course.getCourseTitle());
			}

			if (instructor != null) {
				enrollmentDTO.setInstructorName(instructor.getFirstName() + " " + instructor.getLastName());
			}

			enrollmentDTO.setEnrollmentDate(enrollment.getEnrollmentDate());
			enrollmentDTOs.add(enrollmentDTO);
		}

		return enrollmentDTOs;

	}

}
