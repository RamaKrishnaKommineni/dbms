package edu.northeastern.cs5200.Entities;

import java.util.List;

import javax.persistence.*;

@Entity
public class Student extends Person{
	private int gradYear;
	private Long scholarship;
	
	@OneToMany(mappedBy="student",fetch=FetchType.EAGER)
	private List<Enrollment> enrollments;
	
	public List<Enrollment> getEnrollments() {
		return enrollments;
	}

	public void setEnrollments(List<Enrollment> enrollments) {
		this.enrollments = enrollments;
	}

	public void addEnrollments(Enrollment enrollment) {
		this.enrollments.add(enrollment);
		if(enrollment.getStudent()!=this) {
			enrollment.setStudent(this);
		}
	}
	public Student() {
	}
	
	public Student(String user_name, String password, String first_name, String last_name) {
		super(user_name,password,first_name,last_name);
	}
		
	public Student(String user_name, String password, String first_name, String last_name, int gradYear, long scholarship) {
        super(user_name, password, first_name, last_name);
        this.gradYear = gradYear;
        this.scholarship = scholarship;
    }
    

	public int getGradYear() {
		return gradYear;
	}

	public void setGradYear(int gradYear) {
		this.gradYear = gradYear;
	}

	public Long getScholarship() {
		return scholarship;
	}

	public void setScholarship(Long scholarship) {
		this.scholarship = scholarship;
	}
}