package com.example.studentmanagentsystem.controller;

import java.util.List;
import java.util.Optional;

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
import com.example.studentmanagentsystem.entity.Enrollment;
import com.example.studentmanagentsystem.entity.Instructor;
import com.example.studentmanagentsystem.entity.Student;
import com.example.studentmanagentsystem.entity.repository.CourseRepository;
import com.example.studentmanagentsystem.entity.repository.EnrollmentRepository;
import com.example.studentmanagentsystem.entity.repository.InstructorRepository;
import com.example.studentmanagentsystem.entity.repository.StudentRepository;
import com.example.studentmanagentsystem.model.LoginModel;
import com.example.studentmanagentsystem.service.CourseService;
import com.example.studentmanagentsystem.service.EnrollmetService;

import jakarta.validation.Valid;


@Controller
public class projectControllers {
	
	int id = -1;

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private InstructorRepository instructorRepository;
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private EnrollmentRepository enrollmentRepository;
	
    @Autowired
    CourseService courseService;
    
    @Autowired
    EnrollmetService enrollService;

	// Route for home page
	@GetMapping("/")
	public String homePage() {
		return "home";
	}

	// Route for sign-up page
	@GetMapping("/signup")
	public String signupPage(Model model) {
		model.addAttribute("student", new Student());
		return "signup";
	}

	// Handler for sign-up
	@PostMapping("/signupprocess")
	public String signup(@Valid @ModelAttribute("student") Student student, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			// Validation failed, return to the sign-up form with error messages
			return "signup";
		}

		try {
			student.setRole("USER");
			studentRepository.save(student);
			return "redirect:/"; // Redirect to the home page after successful signup
		} catch (DataIntegrityViolationException e) {
			bindingResult.rejectValue("email", "error.student", "Email already exists");
			return "signup";
		}
	}

	@GetMapping("/login")
	public String loginPage(Model model) {
		model.addAttribute("loginModel", new LoginModel());
		return "login";
	}

	// handler for login process
	@PostMapping("/login")
	public String login(@Valid @ModelAttribute("loginModel") LoginModel loginModel, BindingResult bindingResult) {
	    if (bindingResult.hasErrors()) {
	        return "login"; // Return to the login page with validation errors
	    }
	    
		if ("student,".equals(loginModel.getRole())) {
			Student dbUser = studentRepository.findByEmail(loginModel.getEmail());
			id = dbUser.getStudentID();
			if (dbUser != null && loginModel.getPassword().equals(dbUser.getPassword())) {
				return "userpage";

			} else {
				return "error";
			}
		} else {
			Instructor dbUser = instructorRepository.findByEmail(loginModel.getEmail());
			if (dbUser != null && loginModel.getPassword().equals(dbUser.getPassword())) {
				if ("ADMIN".equals(dbUser.getRole())) {
					return "adminpage";
				} else {
					return "instructorpage";
				}
			} else {
				return "error";
			}
		}
	}
	
	
	@GetMapping("/addcourse")
	public String addCourse(Model model) {
		model.addAttribute("course", new Course());
		return "addcourse";
	}
	
	@PostMapping("/addcourseprocess")
	public String addCourseProcess(Course course) {
		courseRepository.save(course);
		return "adminpage";
	}
	
	@GetMapping("/allcourses")
	public ModelAndView getAllBook() {
		List<Course> list = courseService.getAllCourse();
		return new ModelAndView("courseslist", "course", list);
	}
	
	
	@GetMapping("/enrollcourse")
	public ModelAndView enrollCourse(Model model) {
		List<Course> list = courseService.getAllCourse();
		List<Instructor> instructor = instructorRepository.findAll();
		model.addAttribute("course",list);
		model.addAttribute("instructor",instructor);
		model.addAttribute("enrollment", new Enrollment());
		return new ModelAndView("enrollcourse");
	}
	
	
	@PostMapping("/processenrollcourse")
	public String processEnrollCourse(@ModelAttribute("enrollment") Enrollment enrollment,
			BindingResult bindingResult) {
      if (bindingResult.hasErrors()) {
      // Validation failed, return to the sign-up form with error messages
    	  return "enrollcourse";
      }
      try {
    	// Retrieve the Student entity using the id
          Optional<Student> studentOptional = studentRepository.findById(id);
          if (studentOptional.isPresent()) {
              Student student = studentOptional.get();
              enrollment.setStudent(student);
              enrollService.save(enrollment);
//              enrollmentRepository.save(enrollment);
              return "redirect:/userpage";
          } else {
              // Handle the case where the student with the given id is not found
              // You might want to display an error message or redirect accordingly
              return "enrollcourse"; // or some other error handling logic
          }
      }
      catch (DataIntegrityViolationException e) {
    	    bindingResult.rejectValue("studentID", "error.enrollment", "Enrollment failed due to data integrity violation.");
    	    return "enrollcourse";
    	}
	}
	
	@GetMapping("/enrollments")
	public String enrollments() {
		return "enrollments";
	}
	
	@GetMapping("/feedbacks")
	public String feedbacks() {
		return "feedback";
	}
	
	@GetMapping("/logout")
	public String handleLogout() {
		return "home";
	}

}
