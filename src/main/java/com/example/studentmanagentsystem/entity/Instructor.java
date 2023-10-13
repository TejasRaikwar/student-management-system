package com.example.studentmanagentsystem.entity;

import jakarta.persistence.*;

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
    
    
    public Instructor(int instructorID, String firstName, String lastName, String email) {
		super();
		this.instructorID = instructorID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
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
      
}