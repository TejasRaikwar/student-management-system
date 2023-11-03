package com.example.studentmanagentsystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Instructor")
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "InstructorID")
    private int instructorID;
    
    @Column(name = "FirstName", length = 50)
    private String firstName;
    
    @Column(name = "LastName", length = 50)
    private String lastName;
        

	@Column(name = "Email", length = 100)
    private String email;
    
	@Size(min = 8, message = "Password must be at least 8 characters long")
    @Pattern(
        regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&!])[A-Za-z\\d@#$%^&!]{8,}$",
        message = "Password must contain at least one uppercase letter, one lowercase letter, one digit, and one special character (@#$%^&!)"
    )
    private String password;

	private String role;
	
	@Override
	public String toString() {
		return "Instructor [instructorID=" + instructorID + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", email=" + email + ", password=" + password + ", role=" + role + ", course=" + course + "]";
	}

	private int course;

	public int getCourse() {
		return course;
	}

	public void setCourse(int course) {
		this.course = course;
	}

	public Instructor() {
		// TODO Auto-generated constructor stub
	}

	public Instructor(int instructorID, String firstName, String lastName, String email,
			@Size(min = 8, message = "Password must be at least 8 characters long") @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&!])[A-Za-z\\d@#$%^&!]{8,}$", message = "Password must contain at least one uppercase letter, one lowercase letter, one digit, and one special character (@#$%^&!)") String password,
			String role, int course) {
		super();
		this.instructorID = instructorID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.role = role;
		this.course = course;
	}

	// Getters and setters
	public int getInstructorID() {
		return instructorID;
	}

	public void setInstructorID(int instructorID) {
		this.instructorID = instructorID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}   
}