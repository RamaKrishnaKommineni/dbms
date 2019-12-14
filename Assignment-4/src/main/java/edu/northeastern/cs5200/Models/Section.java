package edu.northeastern.cs5200.Models;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
public class Section {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int seats;
	private String title;
	
	public Section() {}
	
	public Section(int seats, String title, Course course) {
		super();
		this.seats = seats;
		this.title = title;
		this.course = course;
		this.enrollment = new ArrayList<Enrollment>();
	}

	@ManyToOne()
	private Course course;
	
	@OneToMany(mappedBy = "section", fetch = FetchType.LAZY) 
	private List<Enrollment> enrollment;
	  
	public void setEnrollment(Enrollment enrollment) {
		if(!this.enrollment.contains(enrollment)) { 
			this.enrollment.add(enrollment);
		} 
		enrollment.setSection(this); 
	}
	
	public List<Enrollment> getEnrollment() { 
		return enrollment;
	}
	  
	public void setEnrollment(List<Enrollment> enrollment) { 
		this.enrollment = enrollment;
	}
	
	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;	
		if(!course.getSection().contains(this)) {
			course.getSection().add(this);
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}	
}