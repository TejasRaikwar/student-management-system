package com.example.studentmanagentsystem.entity.repository;

import com.example.studentmanagentsystem.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Integer> {
	List<Enrollment> findByStudent(int student);
	List<Enrollment> findByInstructor(int instructor);
}

