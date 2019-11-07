package edu.northeastern.cs5200.Entities;

import java.util.List;
import javax.persistence.*;

@Entity
public class Section {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private int seats;
	private String title;

	@OneToMany(mappedBy="section", fetch=FetchType.EAGER)
	private List<Enrollment> enrollments;
	public List<Enrollment> getEnrollments(){
		return enrollments;
	}
	
	public void setEnrollments(List<Enrollment> enrollments) {
		this.enrollments = enrollments;
	}

	public void addEnrollments(Enrollment enrollment) {
		this.enrollments.add(enrollment);
		if(enrollment.getSection()!=this) {
			enrollment.setSection(this);
		}
	}
	
	@ManyToOne()
	private Course course;
	
	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
		if(!course.getSections().contains(this)) {
			course.getSections().add(this);
		}
	}

	public Section() {
	}
	
	public Section(String title,int seats) {
		this.title = title;
		this.seats = seats;
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
