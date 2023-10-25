package com.example.studentmanagentsystem.entity;
import java.util.Date;
import jakarta.persistence.*;

@Entity
@Table(name = "Enrollment")
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EnrollmentID")
    private int enrollmentID;
    
    @ManyToOne
    @JoinColumn(name = "InstructorID")
    private Instructor instructor;
    
    @Column(name = "EnrollmentDate")
    @Temporal(TemporalType.DATE)
    private Date enrollmentDate;
    
    @ManyToOne
    @JoinColumn(name = "StudentID")
    private Student student;
    
    @ManyToOne
    @JoinColumn(name = "CourseID")
    private Course course;
    
    
    // Getters and setters
    
	public int getEnrollmentID() {
		return enrollmentID;
	}

	public void setEnrollmentID(int enrollmentID) {
		this.enrollmentID = enrollmentID;
	}

	public Instructor getInstructor() {
		return instructor;
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}

	public Date getEnrollmentDate() {
		return enrollmentDate;
	}

	public void setEnrollmentDate(Date enrollmentDate) {
		this.enrollmentDate = enrollmentDate;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	} 
}
