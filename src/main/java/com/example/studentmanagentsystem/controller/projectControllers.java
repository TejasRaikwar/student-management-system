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
import com.example.studentmanagentsystem.entity.Enrollment;
import com.example.studentmanagentsystem.entity.Instructor;
import com.example.studentmanagentsystem.entity.Student;
import com.example.studentmanagentsystem.entity.repository.CourseRepository;
import com.example.studentmanagentsystem.entity.repository.EnrollmentRepository;
import com.example.studentmanagentsystem.entity.repository.InstructorRepository;
import com.example.studentmanagentsystem.entity.repository.StudentRepository;
import com.example.studentmanagentsystem.model.LoginModel;
import com.example.studentmanagentsystem.service.CourseService;
import jakarta.validation.Valid;


@Controller
public class projectControllers {

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
	
	
//    @GetMapping("/instructor")
//    public ModelAndView instructorSignup(Model model) {
//		List<Course> list = courseService.getAllCourse();
//		model.addAttribute("instructor", new Instructor());
//		return new ModelAndView("instructor-signup", "course", list);
//    }
	
	@GetMapping("/enrollcourse")
	public ModelAndView enrollCourse(Model model) {
		List<Course> list = courseService.getAllCourse();
		return new ModelAndView("enrollcourse", "course", list);
	}
	
//    @PostMapping("/instructorprocess")
//    public String instructorSignupProcess(@Valid @ModelAttribute("instructor") Instructor instructor, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            // Validation failed, return to the sign-up form with error messages
//            return "instructor-signup";
//        }
//        try {
//            instructor.setRole("INSTRUCTOR");
//            instructorRepository.save(instructor);
//            return "redirect:/"; // Redirect to the home page after successful signup
//        } catch (DataIntegrityViolationException e) {
//            bindingResult.rejectValue("email", "error.student", "Email already exists");
//            return "instructor-signup";
//        }
//    }
	
	@PostMapping("/processenrollcourse")
	public String processEnrollCourse(@Valid @ModelAttribute("enrollment") Enrollment enrollment,BindingResult bindingResult) {
      if (bindingResult.hasErrors()) {
      // Validation failed, return to the sign-up form with error messages
      return "enrollcourse";
      }
      try {
    	  enrollmentRepository.save(enrollment);
    	  return "redirect:/";
      }
      catch(DataIntegrityViolationException e) {
    	  bindingResult.rejectValue("email", "error.student", "Email already exists");
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
