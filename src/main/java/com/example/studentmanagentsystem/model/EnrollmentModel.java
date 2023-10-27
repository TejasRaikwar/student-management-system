package com.example.studentmanagentsystem.model;

public class EnrollmentModel {
	private int enrollid;
	private String enrilldate;
	private int courseid;
	private int instructorid;
	private int studentid;
	public int getEnrollid() {
		return enrollid;
	}
	public void setEnrollid(int enrollid) {
		this.enrollid = enrollid;
	}
	public String getEnrilldate() {
		return enrilldate;
	}
	public void setEnrilldate(String enrilldate) {
		this.enrilldate = enrilldate;
	}
	public int getCourseid() {
		return courseid;
	}
	public void setCourseid(int courseid) {
		this.courseid = courseid;
	}
	public int getInstructorid() {
		return instructorid;
	}
	public void setInstructorid(int instructorid) {
		this.instructorid = instructorid;
	}
	public int getStudentid() {
		return studentid;
	}
	public void setStudentid(int studentid) {
		this.studentid = studentid;
	}
}
