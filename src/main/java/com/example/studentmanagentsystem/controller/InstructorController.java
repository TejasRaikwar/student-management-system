package com.example.studentmanagentsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.studentmanagentsystem.entity.Course;
import com.example.studentmanagentsystem.entity.Instructor;
import com.example.studentmanagentsystem.entity.repository.InstructorRepository;
import com.example.studentmanagentsystem.service.CourseService;

import jakarta.validation.Valid;

@Controller
public class InstructorController {
    @Autowired
    private final InstructorRepository instructorRepository;

    @Autowired
    CourseService courseService;
    public InstructorController(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }
    
    @GetMapping("/instructor")
    public ModelAndView instructorSignup(Model model) {
		List<Course> list = courseService.getAllCourse();
		model.addAttribute("instructor", new Instructor());
		return new ModelAndView("instructor-signup", "course", list);
    }

    @PostMapping("/instructorprocess")
    public String instructorSignupProcess(@Valid @ModelAttribute("instructor") Instructor instructor, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // Validation failed, return to the sign-up form with error messages
            return "instructor-signup";
        }
        try {
            instructor.setRole("INSTRUCTOR");
            instructorRepository.save(instructor);
            return "redirect:/"; // Redirect to the home page after successful signup
        } catch (DataIntegrityViolationException e) {
            bindingResult.rejectValue("email", "error.student", "Email already exists");
            return "instructor-signup";
        }
    }
}
