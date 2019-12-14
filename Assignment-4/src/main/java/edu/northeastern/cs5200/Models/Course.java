package edu.northeastern.cs5200.Models;

import java.util.List;
import javax.persistence.*;

@Entity
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String label;
	
	@ManyToOne()
	private Faculty faculty;
	
	@OneToMany(mappedBy = "course", fetch = FetchType.EAGER)
	private List<Section> section;
	
	public List<Section> getSection() {
		return section;
	}
	
	public void setSection(List<Section> section) {
		this.section = section;
	}
	
	public void setSection(Section section) {
		this.section.add(section);
		if(section.getCourse() != this) {
			section.setCourse(this);
		}
	}
	
	public Faculty getFaculty() {
		return faculty;
	}
	
	public Course() {}
	
	public Course(String label, Faculty faculty) {
		super();
		this.label = label;
		this.faculty = faculty;
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
	
	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
		if(!faculty.getFacultyCourses().contains(this)) {
			faculty.getFacultyCourses().add(this);
		}
	}
}