package com.example.studentmanagentsystem.entity;
import java.util.Date;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "Feedback")
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FeedbackID")
    private int feedbackID;
    
    @Column(name = "StudentID")
    private int studentid;
    
    @Column(name = "InstructorName")
    private String instructorName;
    
    @Column(name = "FeedbackContent")
    @NotEmpty
    private String feedback;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "FeedbackDate")
    private Date date;

	public int getFeedbackID() {
		return feedbackID;
	}

	public void setFeedbackID(int feedbackID) {
		this.feedbackID = feedbackID;
	}

	public int getStudentid() {
		return studentid;
	}

	public void setStudentid(int studentid) {
		this.studentid = studentid;
	}

	public String getInstructorName() {
		return instructorName;
	}

	public void setInstructorName(String instructorName) {
		this.instructorName = instructorName;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Feedback [feedbackID=" + feedbackID + ", studentid=" + studentid + ", instructorName=" + instructorName
				+ ", feedback=" + feedback + ", date=" + date + "]";
	}

	
}