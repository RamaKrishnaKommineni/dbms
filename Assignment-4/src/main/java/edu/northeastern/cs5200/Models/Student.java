package edu.northeastern.cs5200.Models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Student extends Person {
	private int gradYear;
	private long scholarship;
	
	@OneToMany(mappedBy = "student", fetch = FetchType.LAZY) 
	private List<Enrollment> enrollments;
	
	public Student() {}
	
	public Student(String username, String password, String firstName, String lastName) {
		super(username, password, firstName, lastName);
	}
	
	public Student(String username, String password, String firstName, String lastName, int gradYear,
			long scholarship) {
		super(username, password, firstName, lastName);
		this.gradYear = gradYear;
		this.scholarship = scholarship;
		this.enrollments = new ArrayList<Enrollment>();
	}
	
	public List<Enrollment> getEnrollments() { 
		return enrollments; 
	}
	  
	public void setEnrollments(Enrollment enrollment) {
		if(!this.enrollments.contains(enrollment)) {
			this.enrollments.add(enrollment); 
		}
		enrollment.setStudent(this);
	}
	  
	public void setEnrollments(List<Enrollment> enrollments){
		this.enrollments = enrollments; 
	}
	 
	public int getGradYear() {
		return gradYear;
	}
	
	public void setGradYear(int gradYear) {
		this.gradYear = gradYear;
	}
	
	public long getScholarship() {
		return scholarship;
	}
	
	public void setScholarship(long scholarship) {
		this.scholarship = scholarship;
	}
}