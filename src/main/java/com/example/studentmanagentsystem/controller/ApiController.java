package com.example.studentmanagentsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import org.springframework.http.ResponseEntity;
import com.example.studentmanagentsystem.entity.Instructor;
import com.example.studentmanagentsystem.service.InstructorService;


@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private InstructorService instructorService;

    @GetMapping("/instructors/{courseId}")
    public ResponseEntity<List<Instructor>> getInstructorsByCourse(@PathVariable("courseId") int courseId) {
        List<Instructor> instructors = instructorService.getInstructorsByCourse(courseId);
        return ResponseEntity.ok(instructors); // Return a 200 OK response with the list of instructors
    }
}


