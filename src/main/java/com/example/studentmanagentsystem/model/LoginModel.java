package com.example.studentmanagentsystem.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Email;

public class LoginModel {
	
    @NotEmpty(message = "Email is required")
    @Email(message = "Invalid email address")
	private String email;
	
    @NotEmpty(message = "Password is required")
	private String password;
    
	@NotEmpty(message = "Role is required")
	private String role;
	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
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
}
