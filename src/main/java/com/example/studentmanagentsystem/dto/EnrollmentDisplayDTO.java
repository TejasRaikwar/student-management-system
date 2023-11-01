package com.example.studentmanagentsystem.dto;

public class EnrollmentDisplayDTO {
    
	private String courseTitle;
    private int courseId;
    private String instructorName;
    private String enrollmentDate;
    private String StudentName;
    private String StudentMobile;
    private int studentId;
    
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public String getStudentName() {
		return StudentName;
	}
	public void setStudentName(String studentName) {
		StudentName = studentName;
	}
	public String getCourseTitle() {
		return courseTitle;
	}
	public String getStudentMobile() {
		return StudentMobile;
	}
	public void setStudentMobile(String studentMobile) {
		StudentMobile = studentMobile;
	}
	public void setCourseTitle(String courseTitle) {
		this.courseTitle = courseTitle;
	}
	public String getInstructorName() {
		return instructorName;
	}
	public void setInstructorName(String instructorName) {
		this.instructorName = instructorName;
	}
	public String getEnrollmentDate() {
		return enrollmentDate;
	}
	public void setEnrollmentDate(String enrollmentDate) {
		this.enrollmentDate = enrollmentDate;
	}
    
}
