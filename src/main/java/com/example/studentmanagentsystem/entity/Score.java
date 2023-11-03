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
	    
	    private int student;
	    
	    private int course;
	    
	    @Column(name = "DateOfExam")
	    @Temporal(TemporalType.DATE)
	    private String dateOfExam;
	    
	    @Column(name = "CreditObtained", precision = 5, scale = 2)
	    private BigDecimal creditObtained;

		public int getScoreID() {
			return scoreID;
		}

		public void setScoreID(int scoreID) {
			this.scoreID = scoreID;
		}

		public int getStudent() {
			return student;
		}

		public void setStudent(int student) {
			this.student = student;
		}

		public int getCourse() {
			return course;
		}

		public void setCourse(int course) {
			this.course = course;
		}

		public String getDateOfExam() {
			return dateOfExam;
		}

		public void setDateOfExam(String dateOfExam) {
			this.dateOfExam = dateOfExam;
		}

		public BigDecimal getCreditObtained() {
			return creditObtained;
		}

		public void setCreditObtained(BigDecimal creditObtained) {
			this.creditObtained = creditObtained;
		}
	    
	    
}