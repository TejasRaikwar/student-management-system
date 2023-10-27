package com.example.studentmanagentsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.studentmanagentsystem.entity.Instructor;
import com.example.studentmanagentsystem.entity.repository.InstructorRepository;
import java.util.List;
@Service
public class InstructorService {

    @Autowired
    private InstructorRepository instructorRepository;

    public List<Instructor> getInstructorsByCourse(int courseId) {
        return instructorRepository.findInstructorsByCourse(courseId);
    }
}

