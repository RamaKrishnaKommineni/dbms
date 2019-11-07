package com.example.cs5200_fall2019_mandava_sowmika_jpa.models;

import java.util.List;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String label;
	
	public Course(String label) {
		this.label = label;
	}

	public Course() {
	}
	
	public Course(String label, Faculty author, List<Section> Sections) {
		this.label = label;
		this.author = author;
		this.Sections = Sections;
	}

	@ManyToOne()
	@JsonIgnore
	private Faculty author;
	
	@OneToMany(mappedBy="course")
	@LazyCollection(LazyCollectionOption.FALSE)

	private List<Section> Sections;
	
	public void Sections(Section section) {
		this.Sections.add(section);
	    if(section.getCourse() != this) {
	    section.setCourse(this);
	    }
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
	public Faculty getAuthor() {
		return author;
	}
	public void setAuthor(Faculty author) {
		this.author = author;
		if(!author.getAuthoredCourses().contains(this)) {
		        author.getAuthoredCourses().add(this);
		}
	}
	public List<Section> getSections() {
		return Sections;
	}

	public void setSections(List<Section> Sections) {
		this.Sections = Sections;
	}
}
