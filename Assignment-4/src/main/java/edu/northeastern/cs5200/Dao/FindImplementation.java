package edu.northeastern.cs5200.Dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.northeastern.cs5200.Models.*;
import edu.northeastern.cs5200.Repositories.*;

@Component
public class FindImplementation implements FindDao{
	@Autowired
	private PersonRepository personrepository;
	@Autowired
	private FacultyRepository facultyrepository;
	@Autowired
	private StudentRepository studentrepository;
	@Autowired
	private CourseRepository courserepository;
	@Autowired
	private SectionRepository sectionrepository;
	@Autowired
	private EnrollmentRepository enrollmentrepository;

	@Override
	public List<Person> findAllUsers() {
		return (List<Person>) personrepository.findAll();
	}

	@Override
	public List<Faculty> findAllFaculties() {
		return (List<Faculty>) facultyrepository.findAll();
	}

	@Override
	public List<Student> findAllStudents() {
		return (List<Student>) studentrepository.findAll();
	}

	@Override
	public List<Course> findAllCourses() {
		return (List<Course>) courserepository.findAll();
	}

	@Override
	public List<Section> findAllSections() {
		return (List<Section>) sectionrepository.findAll();
	}

	@Override
	public List<Course> findCoursesForAuthor(Faculty faculty) {
		return courserepository.findCourseByFaculty(faculty);
	}

	@Override
	public List<Section> findSectionForCourse(Course course) {
		return sectionrepository.findSectionByCourse(course);
	}

	@Override
	public List<Student> findStudentsInSection(Section section) {
		List<Enrollment> enrollmentList = enrollmentrepository.findStudentsInSection(section);
		List<Student> studentList = new ArrayList<Student>();
		for(Enrollment e : enrollmentList ) {
			studentList.add(e.getStudent());
		}
	    return studentList;
	}

	@Override
	public List<Section> findSectionsForStudent(Student student) {
		List<Enrollment> enrollmentList = enrollmentrepository.findSectionsForStudent(student);
		List<Section> sectionList = new ArrayList<Section>();
		for(Enrollment e: enrollmentList) {
			sectionList.add(e.getSection());
		}
		return sectionList;
	}

}