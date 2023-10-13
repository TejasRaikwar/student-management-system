//package com.example.studentmanagentsystem.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.dao.DataIntegrityViolationException;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import com.example.studentmanagentsystem.entity.Student;
//import com.example.studentmanagentsystem.entity.repository.StudentRepository;
//import org.springframework.validation.BindingResult;
//import jakarta.validation.Valid;
//
//@Controller
//public class projectControllers {
//	@Autowired
//	private StudentRepository studentrepository;
//	
//	
//	// Route for sign up page
//	@GetMapping("/signup")
//	public String signupPage(Student student) {
//		return "signup";
//	}
//	
////	// handler for sign up	
////	@PostMapping("/signupprocess")
////	public String signup(@Valid @ModelAttribute("student") Student student, BindingResult bindingResult) {
////		if (bindingResult.hasErrors()) {
////			// Validation failed, return to the sign up form with error messages
////			return "signup";
////		}
////		try {
////			studentrepository.save(student);
////			return "signup";
////		} catch (DataIntegrityViolationException e) {
////			bindingResult.reject(null, null);
////			return "signup";
////		}
////
////	}
//	@PostMapping("/signupprocess")
//	public String signup(@Valid @ModelAttribute("student") Student student, BindingResult bindingResult) {
//	    if (bindingResult.hasErrors()) {
//	        // Validation failed, return to the sign-up form with error messages
//	        return "signup";
//	    }
//	    try {
//	        studentrepository.save(student);
//	        return "redirect:/"; // Redirect to the home page after successful signup
//	    } catch (DataIntegrityViolationException e) {
//	        bindingResult.rejectValue("email", "error.student", "Email already exists");
//	        return "signup";
//	    }
//	}
//
//	
//	
//	// Route for home page
//	@GetMapping("/")
//	public String homePage() {
//		return "home";
//	}
//	
//
//}
package com.example.studentmanagentsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.studentmanagentsystem.entity.Student;
import com.example.studentmanagentsystem.entity.repository.StudentRepository;
import jakarta.validation.Valid;

@Controller
public class projectControllers {

    @Autowired
    private StudentRepository studentRepository;

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
    public String loginPage(Student student) {
        return "login";
    }
    
//    @PostMapping("/login")
//    public String login(@RequestParam("email") String email, @RequestParam("password") String password) 
//    {
//    	Student user = studentRepository.findByEmail(email);
//    	if (user != null && password.equals(user.getPassword())) {
//			if ("ADMIN".equals(user.getRole())) {
//				return "adminpage";
//			} else {
//				return "userpage";
//			}
//		} else {
//			return "error";
//		}	
//    }
	// handler for login process
	@PostMapping("/login")
	public String login(@RequestParam("email") String email, @RequestParam("password") String password) {
		Student dbUser = studentRepository.findByEmail(email);
		if (dbUser != null && password.equals(dbUser.getPassword())) {
			if ("ADMIN".equals(dbUser.getRole())) {
				return "adminpage";
			} else {
				return "userpage";
			}
		} else {
			return "error";
		}
	}
    
    // Route for home page
    @GetMapping("/")
    public String homePage() {
        return "home";
    }
}
