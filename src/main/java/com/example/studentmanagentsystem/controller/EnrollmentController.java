//package com.example.studentmanagentsystem.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.servlet.ModelAndView;
//import com.example.studentmanagentsystem.entity.Course;
//import com.example.studentmanagentsystem.entity.Enrollment;
//import com.example.studentmanagentsystem.entity.Instructor;
//import com.example.studentmanagentsystem.entity.repository.InstructorRepository;
//import com.example.studentmanagentsystem.service.CourseService;
//import com.example.studentmanagentsystem.service.EnrollmetService;
//import java.util.List;
//@Controller
//public class EnrollmentController {
//
//    @Autowired
//    private EnrollmetService enrollmentService;
//    
//    @Autowired
//    private CourseService courseService;
//    
//    @Autowired
//    private Instructor instructor;
//    
//    @Autowired
//    private InstructorRepository instructorRepository;
//
//    @GetMapping("/enrollcourse")
//    public ModelAndView enrollCourse(Model model) {
//        List<Course> courses = courseService.getAllCourse();
//        List<Instructor> instructor = instructorRepository.findAll();
//        model.addAttribute("course", courses);
//        model.addAttribute("instructor",instructor);
//        model.addAttribute("enrollment", new Enrollment());
//        return new ModelAndView("enrollcourse");
//    }
//
//    @PostMapping("/processenrollcourse")
//    public String processEnrollCourse(@ModelAttribute("enrollment") Enrollment enrollment, BindingResult bindingResult) {
//        System.out.println("Hi");
//    	if (bindingResult.hasErrors()) {
//            // Validation failed, return to the form with error messages
//            return "enrollcourse";
//        }
//
//        // Handle the enrollment process (save to the database) here
//        enrollmentService.save(enrollment);
//
//        return "redirect:/userpage"; // Redirect to a success page
//    }
//}
//
