package com.example.studentmanagentsystem.entity;
import java.util.Date;
import jakarta.persistence.*;

@Entity
@Table(name = "Enrollment")
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EnrollmentID")
    private int enrollmentID;
    
//    @ManyToOne
//    @JoinColumn(name = "InstructorID")
//    private Instructor instructor;
    private int instructor;
    
//    @Column(name = "EnrollmentDate")
//    @Temporal(TemporalType.DATE)
    private String enrollmentDate;
    
//    @ManyToOne
//    @JoinColumn(name = "StudentID")
    private int student;
    
//    @ManyToOne
//    @JoinColumn(name = "CourseID")
    private int course;

	public int getEnrollmentID() {
		return enrollmentID;
	}

	public void setEnrollmentID(int enrollmentID) {
		this.enrollmentID = enrollmentID;
	}

	public int getInstructor() {
		return instructor;
	}

	public void setInstructor(int instructor) {
		this.instructor = instructor;
	}

	public String getEnrollmentDate() {
		return enrollmentDate;
	}

	public void setEnrollmentDate(String enrollmentDate) {
		this.enrollmentDate = enrollmentDate;
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

	@Override
	public String toString() {
		return "Enrollment [enrollmentID=" + enrollmentID + ", instructor=" + instructor + ", enrollmentDate="
				+ enrollmentDate + ", student=" + student + ", course=" + course + "]";
	}
}
