package com.example.cs5200_fall2019_mandava_sowmika_jpa.models;

import java.util.List;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@DiscriminatorValue("FACULTY")
public class Faculty extends Person {
	private String office;
	private Boolean tenured;
	
	public Faculty() {
	}
	/*
	 * public Faculty(String username, String password, String firstName, String
	 * lastName) { super(username, password, firstName, lastName); }
	 */
	
	public Faculty(String username, String password, String firstName, String lastName, String office,
			Boolean tenured) {
		super(username, password, firstName, lastName);
		this.office = office;
		this.tenured = tenured;
	}


	@OneToMany(mappedBy="author")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Course> authoredCourses;
	
	public void authoredCourse(Course course) {
		this.authoredCourses.add(course);
	    if(course.getAuthor() != this)
	    course.setAuthor(this);
	}
	
	public String getOffice() {
		return office;
	}
	public void setOffice(String office) {
		this.office = office;
	}
	public Boolean getTenured() {
		return tenured;
	}
	public void setTenured(Boolean tenured) {
		this.tenured = tenured;
	}
	public List<Course> getAuthoredCourses() {
		return authoredCourses;
	}
	public void setAuthoredCourses(List<Course> authoredCourses) {
		this.authoredCourses = authoredCourses;
	}
}