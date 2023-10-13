package com.example.studentmanagentsystem.entity;
import java.math.BigDecimal;
import java.util.Date;
import jakarta.persistence.*;

@Entity
@Table(name = "Score")
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ScoreID")
    private int scoreID;
    
    @ManyToOne
    @JoinColumn(name = "StudentID")
    private Student student;
    
    @ManyToOne
    @JoinColumn(name = "CourseID")
    private Course course;
    
    @Column(name = "DateOfExam")
    @Temporal(TemporalType.DATE)
    private Date dateOfExam;
    
    @Column(name = "CreditObtained", precision = 5, scale = 2)
    private BigDecimal creditObtained;
    
    // Getters and setters
	public int getScoreID() {
		return scoreID;
	}

	public void setScoreID(int scoreID) {
		this.scoreID = scoreID;
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

	public Date getDateOfExam() {
		return dateOfExam;
	}

	public void setDateOfExam(Date dateOfExam) {
		this.dateOfExam = dateOfExam;
	}

	public BigDecimal getCreditObtained() {
		return creditObtained;
	}

	public void setCreditObtained(BigDecimal creditObtained) {
		this.creditObtained = creditObtained;
	}

}