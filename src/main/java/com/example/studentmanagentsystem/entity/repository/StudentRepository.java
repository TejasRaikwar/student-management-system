package com.example.studentmanagentsystem.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.studentmanagentsystem.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer>
{
	Student findByEmail(String email);
	
	Student findById(int id);
}
