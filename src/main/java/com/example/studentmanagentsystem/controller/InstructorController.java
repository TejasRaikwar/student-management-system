package com.example.studentmanagentsystem.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.studentmanagentsystem.dto.EnrollmentDisplayDTO;
import com.example.studentmanagentsystem.dto.ScoreDisplayDTO;
import com.example.studentmanagentsystem.entity.Course;
import com.example.studentmanagentsystem.entity.Enrollment;
import com.example.studentmanagentsystem.entity.Feedback;
import com.example.studentmanagentsystem.entity.Instructor;
import com.example.studentmanagentsystem.entity.Score;
import com.example.studentmanagentsystem.entity.Student;
import com.example.studentmanagentsystem.entity.repository.CourseRepository;
import com.example.studentmanagentsystem.entity.repository.EnrollmentRepository;
import com.example.studentmanagentsystem.entity.repository.FeedbackRepository;
import com.example.studentmanagentsystem.entity.repository.InstructorRepository;
import com.example.studentmanagentsystem.entity.repository.ScoreRepository;
import com.example.studentmanagentsystem.entity.repository.StudentRepository;
import com.example.studentmanagentsystem.service.CourseService;
import com.example.studentmanagentsystem.service.InstructorService;

import jakarta.validation.Valid;

@Controller
public class InstructorController {
	@Autowired
	private final InstructorRepository instructorRepository;

	@Autowired
	CourseService courseService;

	@Autowired
	private InstructorService is;

	@Autowired
	private FeedbackRepository feedbackRepo;

	@Autowired
	private StudentRepository studentRepo;

	@Autowired
	private EnrollmentRepository enrollmentRepo;
	
	@Autowired
	private final CourseRepository courseRepo;

	@Autowired
	private ScoreRepository scoreRepository;

	public InstructorController(InstructorRepository instructorRepository, CourseRepository courseRepo) {
		this.instructorRepository = instructorRepository;
        this.courseRepo = courseRepo;
	}

	@GetMapping("/instructor")
	public ModelAndView instructorSignup(Model model) {
		List<Course> list = courseService.getAllCourse();
		model.addAttribute("instructor", new Instructor());
		return new ModelAndView("instructor-signup", "course", list);
	}

	@PostMapping("/instructorprocess")
	public String instructorSignupProcess(@Valid @ModelAttribute("instructor") Instructor instructor,
			BindingResult bindingResult) {
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

	@GetMapping("/instructor-feedbacks")
	public ModelAndView instructorFeedbacks(Model model) {
		List<Feedback> studentFeedback = feedbackRepo.findByInstructorName(is.getInstructorName());
		List<String> studentNames = new ArrayList<>();

		for (Feedback feedback : studentFeedback) {
			Student student = studentRepo.findById(1);
			if (student != null) {
				String studentName = student.getFirstName() + " " + student.getLastName();
				studentNames.add(studentName);
			}
		}

		model.addAttribute("studentNames", studentNames);
		model.addAttribute("studentFeedbacks", studentFeedback);
		model.addAttribute("feedback", new Feedback());
		return new ModelAndView("instructor-feedbacks");
	}

	@GetMapping("/studentlist")
	public ModelAndView listStudents(Model model) {

		List<Enrollment> instructorEnrollments = enrollmentRepo.findByInstructor(is.getInstructorId());

		List<EnrollmentDisplayDTO> enrollmentDisplayDTOList = new ArrayList<>();

		for (Enrollment enrollment : instructorEnrollments) {
			Optional<Student> studentOptional = Optional.of(studentRepo.findById(enrollment.getStudent()));
			Optional<Course> courseOptional = courseRepo.findById(enrollment.getCourse());

			if (studentOptional.isPresent() && courseOptional.isPresent()) {
				Student student = studentOptional.get();
				Course course = courseOptional.get();

				EnrollmentDisplayDTO enrollmentDisplayDTO = new EnrollmentDisplayDTO();
				enrollmentDisplayDTO.setStudentName(student.getFirstName() + " " + student.getLastName());
				enrollmentDisplayDTO.setStudentMobile(student.getPhone());
				enrollmentDisplayDTO.setCourseTitle(course.getCourseTitle());
				enrollmentDisplayDTO.setEnrollmentDate(enrollment.getEnrollmentDate());
				enrollmentDisplayDTO.setStudentId(student.getStudentID());
				enrollmentDisplayDTO.setCourseId(course.getCourseID());
				enrollmentDisplayDTOList.add(enrollmentDisplayDTO);
			}
		}

		model.addAttribute("enrollments", enrollmentDisplayDTOList);
		return new ModelAndView("instructor-studentlist");
	}

	@GetMapping("/addScore/{studentId}/{courseId}")
	public String addScorePage(@PathVariable("studentId") int studentId, @PathVariable("courseId") int courseId,
			Model model) {

		Student student = studentRepo.findById(studentId);
		Course course = courseService.getBookById(courseId);

		model.addAttribute("studentId", studentId);
		model.addAttribute("courseId", courseId);
		model.addAttribute("student", student); 
		model.addAttribute("course", course); 
		model.addAttribute("score", new Score()); 
		return "add-score-form";
	}

	@PostMapping("/addScore/{studentId}/{courseId}")
    public String addScore(
            @PathVariable("studentId") int studentId,
            @PathVariable("courseId") int courseId,
            @ModelAttribute("score") Score score,
            @RequestParam("dateOfExam")  
            String dateOfExam 
    ) {
        score.setStudent(studentId);
        score.setCourse(courseId);
        score.setDateOfExam(dateOfExam);

        scoreRepository.save(score);

        return "redirect:/listscores";
    }
	/*
	@GetMapping("/listscores")
	public ModelAndView scoresList(Model model) {
		List<Score> list = scoreRepository.findAll();
		List<ScoreDisplayDTO> scoreList = new ArrayList<>();
		
		for (Score score : list) {
			ScoreDisplayDTO scoreDisplay = new ScoreDisplayDTO();
			scoreDisplay.setScoreId(score.getScoreID());
			Student student = studentRepo.findById(score.getStudent());
			
			if (student != null) {
	            String studentName = student.getFirstName() + " " + student.getLastName();
	            scoreDisplay.setStudentName(studentName);
	        }
			
//			Course course = courseRepo.findById(score.getCourse());
			Optional<Course> course = courseRepo.findById(score.getCourse());
	        
			if (course != null) {
	            scoreDisplay.setCourse(course.getCourseTitle());
	            scoreDisplay.setCredits(course.getCredits());
	        }
			
	        scoreDisplay.setDate(score.getDateOfExam());
	        scoreList.add(scoreDisplay);
		}
		
		model.addAttribute("scores",scoreList);
		return new ModelAndView("instructor-scorelist");
	}
	*/
	@GetMapping("/listscores")
	public ModelAndView scoresList(Model model) {
	    List<Score> list = scoreRepository.findAll();
	    List<ScoreDisplayDTO> scoreList = new ArrayList<>();

	    for (Score score : list) {
	        ScoreDisplayDTO scoreDisplay = new ScoreDisplayDTO();
	        scoreDisplay.setScoreId(score.getScoreID());
	        Student student = studentRepo.findById(score.getStudent());

	        if (student != null) {
	            String studentName = student.getFirstName() + " " + student.getLastName();
	            scoreDisplay.setStudentName(studentName);
	        }

	        Optional<Course> courseOptional = courseRepo.findById(score.getCourse());

	        if (courseOptional.isPresent()) {
	            Course course = courseOptional.get();
	            scoreDisplay.setCourse(course.getCourseTitle());
	            scoreDisplay.setCredits(course.getCredits());
	        } else {
	            // Handle the situation where the course is not present
	            // For example:
	            System.out.println("Course not found for the score.");
	        }

	        scoreDisplay.setDate(score.getDateOfExam());
	        scoreList.add(scoreDisplay);
	    }

	    model.addAttribute("scores", scoreList);
	    return new ModelAndView("instructor-scorelist");
	}

}
