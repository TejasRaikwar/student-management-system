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
import com.example.studentmanagentsystem.dto.EnrollmentDisplayDTO;
import com.example.studentmanagentsystem.entity.Course;
import com.example.studentmanagentsystem.entity.Enrollment;
import com.example.studentmanagentsystem.entity.Feedback;
import com.example.studentmanagentsystem.entity.Instructor;
import com.example.studentmanagentsystem.entity.Student;
import com.example.studentmanagentsystem.entity.repository.CourseRepository;
import com.example.studentmanagentsystem.entity.repository.FeedbackRepository;
import com.example.studentmanagentsystem.entity.repository.InstructorRepository;
import com.example.studentmanagentsystem.entity.repository.StudentRepository;
import com.example.studentmanagentsystem.model.LoginModel;
import com.example.studentmanagentsystem.service.CourseService;
import com.example.studentmanagentsystem.service.EnrollmetService;
import com.example.studentmanagentsystem.service.InstructorService;
import jakarta.validation.Valid;


@Controller
public class projectControllers {
	
	public int id = -1;
	
	public static int instructorId;
	
	private String role;
	
	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private InstructorRepository instructorRepository;
	
	@Autowired
	private CourseRepository courseRepository;
		
    @Autowired
    CourseService courseService;
    
    @Autowired
    EnrollmetService enrollService;
    
	@Autowired
	private EnrollmetService enrollmentService;
	
	@Autowired
	private FeedbackRepository feedbackRepo;
	
	@Autowired
	private InstructorService instructorService;

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
			instructorId = dbUser.getInstructorID();
			instructorService.setInstructorName(dbUser.getFirstName()+" "+dbUser.getLastName());
			instructorService.setInstructorId(dbUser.getInstructorID());
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
	public String addCourseProcess(@Valid @ModelAttribute("course") Course course, BindingResult bindingResult) {
	    // Check if a course with the same title already exists
	    Course existingCourse = courseRepository.findByCourseTitle(course.getCourseTitle());

	    if (existingCourse != null) {
	        // A course with the same title already exists, add a custom error to the BindingResult
	        bindingResult.rejectValue("courseTitle", "duplicate", "Course with the same title already exists.");
	        return "addcourse"; // Return to the addcourse page with the error message
	    }

	    // If no duplicate course title is found, proceed with saving the course
	    if (!bindingResult.hasErrors()) {
	        courseService.save(course);
	        return "adminpage"; // Redirect to the success page
	    }

	    return "addcourse"; // Return to the addcourse page if there are other validation errors
	}

	@GetMapping("/allcourses")
	public ModelAndView getAllCourse() {
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
    public String processEnrollCourse(@ModelAttribute("enrollment") Enrollment enrollment, BindingResult bindingResult) {
    	enrollment.setStudent(id);
        
    	if (bindingResult.hasErrors()) {
            // Validation failed, return to the form with error messages
            return "enrollcourse";
        }

        // Handle the enrollment process (save to the database) here
        enrollmentService.save(enrollment);

        return "userpage"; // Redirect to a success page
    }	

	
	@GetMapping("/enrollments")
	public ModelAndView enrollments(Model model) {
		List<EnrollmentDisplayDTO> enrollmentDTOs = enrollmentService.getAllEnrollmentsWithCourseAndInstructorDetails();
		model.addAttribute("enrollments", enrollmentDTOs);
		return new ModelAndView ("enrollments");
	}
	
	
    @GetMapping("/feedbacks")
    public ModelAndView studentFeedback(Model model) {
    	List<Feedback> studentFeedback = feedbackRepo.findByStudentid(id);
    	List<Instructor> instructors = instructorRepository.findAll();
        model.addAttribute("instructors", instructors);
        model.addAttribute("studentFeedbacks", studentFeedback);
        model.addAttribute("feedback", new Feedback());
        return new ModelAndView("feedback");
    }
    
    @PostMapping("/processfeedback")
    public String processFeedback(@ModelAttribute("feedback") Feedback feedback, BindingResult bindingResult) {
        System.out.println("entered");
    	try {
            if (bindingResult.hasErrors()) {
            	System.out.println("error");
                // Validation failed
                return "feedback";
            }
            feedback.setStudentid(id);
            // Save the feedback to the database
            feedbackRepo.save(feedback);
            return "feedback"; // Redirect to a success page
        } catch (Exception e) {
            // Log the exception for debugging
            e.printStackTrace();
            return "error"; // Redirect to an error page
        }
    }

	@GetMapping("/logout")
	public String handleLogout() {
		return "home";
	}
	
	
    @GetMapping("/studentprofile")
    public String showProfile(Model model) {
        // Fetch the logged-in user's details based on their ID (you might need to get the ID from the session or login details)
        Student student = studentRepository.findById(id);
        model.addAttribute("student", student);
        return "student-profile";
    }

	     @PostMapping("/updateProfile")
		public String updateProfile(@ModelAttribute("student") Student updatedUser) {
		    Student originalUser = studentRepository.findById(updatedUser.getStudentID());
		
		    if (originalUser != null) {
		        // Update the user's profile information
		        originalUser.updateProfile(
		            updatedUser.getFirstName(), 
		            updatedUser.getLastName(), 
		            updatedUser.getDob(),
		            updatedUser.getGender(), 
		            updatedUser.getPhone()
		        );
		
		        // Save the updated information to the database
		        studentRepository.save(originalUser);
		        return "redirect:/student-profile"; // Redirect back to the profile page
		    } else {
		        // Handle the case where the user is not found
		        // You might want to redirect to an error page or handle it appropriately
		        return "redirect:/errorPage";
		    }
	}


  /*  
    @PostMapping("/updateProfile")
    public String updateProfile(@ModelAttribute("student") Student updatedUser) {
        // Fetch the original user from the database
        Student originalUser = studentRepository.findById(updatedUser.getStudentID());

        // Update the user's profile information
        originalUser.updateProfile(updatedUser.getFirstName(), updatedUser.getLastName(), updatedUser.getDob(),
                updatedUser.getGender(), updatedUser.getPhone());

        // Save the updated information to the database
        studentRepository.save(originalUser);
        return "redirect:/studentprofile"; // Redirect back to the profile page
    }
   */
    
    @PostMapping("/deleteAccount")
    public String deleteAccount() {
        // Fetch the user based on their ID
        Student user = studentRepository.findById(id);

        // Delete the user account from the database
        studentRepository.delete(user);
        return "home"; // Redirect to the home page after account deletion
    }
	
}
