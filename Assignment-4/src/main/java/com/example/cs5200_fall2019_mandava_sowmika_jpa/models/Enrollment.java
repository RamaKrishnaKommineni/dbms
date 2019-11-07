package com.example.cs5200_fall2019_mandava_sowmika_jpa.models;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Enrollment {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private int grade; 
	private String feedback;
	
	public Enrollment() {
	}
	
	public Enrollment(int grade, String feedback, Student student, Section section) {
		this.grade = grade;
		this.feedback = feedback;
		this.student = student;
		this.section = section;
	}
	
	public Enrollment(Student student, Section section) {
		this.student = student;
		this.section = section;
	}
	
	@ManyToOne()
	@JoinColumn(name="student_id")
	@JsonIgnore
	private Student student;
	
	@ManyToOne()
	@JoinColumn(name="section_id")
	@JsonIgnore
	private Section section;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public String getFeedback() {
		return feedback;
	}
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
		if(!student.getEnrollments().contains(this)) {
		        student.getEnrollments().add(this);
		}
	}
	public Section getSection() {
		return section;
	}
	public void setSection(Section section) {
		this.section = section;
		if(!section.getEnrollments().contains(this)) {
		        section.getEnrollments().add(this);
		}
	}
}