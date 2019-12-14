package edu.northeastern.cs5200.Models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Faculty extends Person{	
	private String office;
	private boolean tenured;

	@OneToMany(mappedBy = "faculty", fetch = FetchType.EAGER)
	private List<Course> facultyCourses;

	public Faculty() {
		super();
	}
	
	public Faculty(String username, String password, String firstName, String lastName, String office,
		boolean tenured) {
		super(username, password, firstName, lastName);
		this.office = office;
		this.tenured = tenured;
		this.facultyCourses = new ArrayList<Course>();
	}

	public String getOffice() {
		return office;
	}

	public void setOffice(String office) {
		this.office = office;
	}

	public boolean isTenured() {
		return tenured;
	}

	public void setTenured(boolean tenured) {
		this.tenured = tenured;
	}

	public void authoredCourse(Course course) {
		if(!facultyCourses.contains(course)) {
			facultyCourses.add(course);
		}
		if(course.getFaculty() != this) {
			course.setFaculty(this);
		}
	}

	public List<Course> getFacultyCourses() {
		return facultyCourses;
	}

	public void setFacultyCourses(List<Course> facultyCourses) {
		this.facultyCourses = facultyCourses;
	}
}