package com.example.studentmanagentsystem.entity.repository;

import com.example.studentmanagentsystem.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository <Course, Integer> {
    // You can add custom query methods here if needed
}