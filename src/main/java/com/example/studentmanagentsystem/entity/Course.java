package com.example.studentmanagentsystem.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "Course")
public class Course {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "CourseID")
	    private int courseID;
	    
	    @Column(name = "Credits")
	    private int credits;
	    
	    @Column(name = "CourseTitle", length = 255)
	    private String courseTitle;
	    
	    public Course() {
//	    	default constructor
	    }
//         Parameterized Constructor
		public Course(int courseID, int credits, String courseTitle) {
			super();
			this.courseID = courseID;
			this.credits = credits;
			this.courseTitle = courseTitle;
		}
		
		
	    // Getters and setters
		public int getCourseID() {
			return courseID;
		}

		public void setCourseID(int courseID) {
			this.courseID = courseID;
		}

		public int getCredits() {
			return credits;
		}

		public void setCredits(int credits) {
			this.credits = credits;
		}

		public String getCourseTitle() {
			return courseTitle;
		}

		public void setCourseTitle(String courseTitle) {
			this.courseTitle = courseTitle;
		}
		@Override
		public String toString() {
			return "Course [courseID=" + courseID + ", credits=" + credits + ", courseTitle=" + courseTitle + "]";
		}
		
}
