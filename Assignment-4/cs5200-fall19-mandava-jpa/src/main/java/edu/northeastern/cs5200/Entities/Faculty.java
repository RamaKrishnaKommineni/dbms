package edu.northeastern.cs5200.Entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
public class Faculty extends Person{
	private String office;
	private Boolean tenured;
	
	@OneToMany(mappedBy="prof",fetch=FetchType.EAGER)
	private List<Course> profCourses;
	
	public List<Course> getProfCourses() {
		if(profCourses==null) {
			profCourses=new ArrayList<>();
		}
		return profCourses;
	}

	public void setProfCourses(List<Course> profCourses) {
		this.profCourses = profCourses;
	}
	
	public void addprofCourses(Course course) {
		profCourses.add(course);
		if(course.getProf()!=this) {
			course.setProf(this);
		}
	}

	public Faculty() {
	}
	
	public Faculty(String user_name, String password, String first_name, String last_name) {
        super(user_name, password, first_name, last_name);
    }
	public Faculty(String user_name, String password, String first_name, String last_name,String office, Boolean tenured) {
		super(user_name,password,first_name,last_name);
		this.office = office;
		this.tenured = tenured;
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
}
