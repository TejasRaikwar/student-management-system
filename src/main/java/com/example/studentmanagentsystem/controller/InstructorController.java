package com.example.studentmanagentsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestParam;
import com.example.studentmanagentsystem.entity.Instructor;
import com.example.studentmanagentsystem.entity.repository.InstructorRepository;
import jakarta.validation.Valid;

@Controller
public class InstructorController {
    @Autowired
    private final InstructorRepository instructorRepository;

    public InstructorController(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    @GetMapping("/instructor")
    public String instructorSignup(Model model) {
        model.addAttribute("instructor", new Instructor());
        return "instructor-signup";
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
