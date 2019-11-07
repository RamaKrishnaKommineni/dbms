package com.example.cs5200_fall2019_mandava_sowmika_jpa.models;

import java.util.List;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@DiscriminatorValue("STUDENT")
public class Student extends Person {
	private int gradYear;
	private long scholarship;
	
	public Student() {
	}
	/*
	 * public Student(String username, String password, String firstName, String
	 * lastName) { super(username, password, firstName, lastName); }
	 */

	public Student(String username, String password, String firstName, String lastName, int gradYear,
			long scholarship) {
		super(username, password, firstName, lastName);
		this.gradYear = gradYear;
		this.scholarship = scholarship;
	}

	@OneToMany(mappedBy="student")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Enrollment> Enrollments;
	
	public void sectionEnrollments(Enrollment enrollment) {
		this.Enrollments.add(enrollment);
	    if(enrollment.getStudent() != this)
	    	enrollment.setStudent(this);
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

	public List<Enrollment> getEnrollments() {
		return Enrollments;
	}

	public void Enrollments(List<Enrollment> Enrollments) {
		this.Enrollments = Enrollments;
	}

}
