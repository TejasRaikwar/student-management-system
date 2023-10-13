package com.example.studentmanagentsystem.entity;
import java.util.Date;
import jakarta.persistence.*;

@Entity
@Table(name = "Feedback")
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FeedbackID")
    private int feedbackID;
    
    @ManyToOne
    @JoinColumn(name = "StudentID")
    private Student student;
    
    @Column(name = "InstructorName", length = 100)
    private String instructorName;
    
    @Column(name = "Feedback", columnDefinition = "TEXT")
    private String feedback;
    
    @Column(name = "Date")
    @Temporal(TemporalType.DATE)
    private Date date;

    //    Getters and Setters
	public int getFeedbackID() {
		return feedbackID;
	}

	public void setFeedbackID(int feedbackID) {
		this.feedbackID = feedbackID;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
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

}