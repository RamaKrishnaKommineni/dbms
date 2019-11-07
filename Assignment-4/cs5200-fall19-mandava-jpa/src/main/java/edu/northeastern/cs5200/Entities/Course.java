package edu.northeastern.cs5200.Entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
public class Course {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String label;
	
	@ManyToOne
	private Faculty prof;
	public Faculty getProf() {
		return prof;
	}

	public void setProf(Faculty prof) {
		this.prof = prof;
		if(!prof.getProfCourses().contains(this)) {
			prof.getProfCourses().add(this);
		}
	}

	@OneToMany(mappedBy="course",fetch=FetchType.EAGER)
	private List<Section> sections;
	public List<Section> getSections(){
		if(sections==null) {
			sections=new ArrayList<>();
		}
		return sections;
	}
	
	public Course() {
	}
	
	public Course(String label) {
		this.label = label;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
}
