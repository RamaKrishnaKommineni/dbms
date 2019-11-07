package com.example.cs5200_fall2019_mandava_sowmika_jpa.models;

import java.util.List;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Section {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String title; 
	private int seats;
	
	public Section() {
	}
	
	public Section(String title, int seats) {
		this.title = title;
		this.seats = seats;
	}

	/*
	 * public Section(String title, int seats, Course course) { this.title = title;
	 * this.seats = seats; this.course = course; }
	 */
	@OneToMany(mappedBy="section")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Enrollment> Enrollments;
	
	public void studentEnrollments(Enrollment enrollment) {
		this.Enrollments.add(enrollment);
	    if(enrollment.getSection() != this)
	    	enrollment.setSection(this);
	}
	
	@ManyToOne()
	@JoinColumn(name = "course_id")
	@JsonIgnore
	private Course course;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getSeats() {
		return seats;
	}
	public void setSeats(int seats) {
		this.seats = seats;
	}

	public List<Enrollment> getEnrollments() {
		return Enrollments;
	}

	public void setEnrollments(List<Enrollment> Enrollments) {
		this.Enrollments = Enrollments;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
		if(!course.getSections().contains(this)) {
		        course.getSections().add(this);
		}
	}
}
