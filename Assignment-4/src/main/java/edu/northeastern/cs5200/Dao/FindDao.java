package edu.northeastern.cs5200.Dao;

import java.util.List;

import edu.northeastern.cs5200.Models.*;

public interface FindDao {
	public List<Person> findAllUsers();
	public List<Faculty> findAllFaculties();
	public List<Student> findAllStudents();
	public List<Course> findAllCourses();
	public List<Section> findAllSections();
	public List<Course> findCoursesForAuthor(Faculty faculty);
	public List<Section> findSectionForCourse(Course course);
	public List<Student> findStudentsInSection(Section section);
	public List<Section> findSectionsForStudent(Student student);
}