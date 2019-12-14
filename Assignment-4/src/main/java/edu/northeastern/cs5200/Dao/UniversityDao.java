package edu.northeastern.cs5200.Dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.northeastern.cs5200.Models.*;
import edu.northeastern.cs5200.Repositories.*;

@Component
public class UniversityDao {
	public UniversityDao() {}
	@Autowired
	PersonRepository personrepository;
	@Autowired
	FacultyRepository facultyrepository;
	@Autowired 
	StudentRepository studentrepository;
	@Autowired
	CourseRepository courserepository;
	@Autowired
	SectionRepository sectionrepository;
	@Autowired
	EnrollmentRepository enrollmentrepository;

	public void truncateDatabase() {
		enrollmentrepository.deleteAll();
		sectionrepository.deleteAll();
		courserepository.deleteAll();
		studentrepository.deleteAll();
		facultyrepository.deleteAll();
		personrepository.deleteAll();
	}
	
	public Faculty createFaculties(Faculty faculty) {
		return facultyrepository.save(faculty);
	}
	
	public Student createStudents(Student student) {
		return studentrepository.save(student);
	}
	
	public Course createCourses(Course course) {
		return courserepository.save(course);
	}
	
	public Section createSections(Section section) {
		return sectionrepository.save(section);
	}
	
	public Course addSectionToCourse(Section section, Course course) {	
		section.setCourse(course);
		sectionrepository.save(section);	
		course.setSection(section);
		return courserepository.save(course);
	}
	
	public Course setAuthorForCourse(Faculty faculty, Course course) {	
		faculty.authoredCourse(course);
		facultyrepository.save(faculty);
		course.setFaculty(faculty);
		return courserepository.save(course);
	}
	
	public boolean enrollStudentInSection(Student student, Section section) {
		int seats = section.getSeats();
		if(seats > 0) {
		  Enrollment enrollment = new Enrollment(student, section);	
		  enrollmentrepository.save(enrollment);
		  section.setSeats(seats - 1);
		  sectionrepository.save(section);
		  return true;
		}
		return false;
	}
}