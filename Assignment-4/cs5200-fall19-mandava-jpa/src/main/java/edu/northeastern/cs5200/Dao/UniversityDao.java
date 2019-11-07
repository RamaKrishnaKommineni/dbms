package edu.northeastern.cs5200.Dao;

import java.util.ArrayList;
import java.util.List;
import edu.northeastern.cs5200.Entities.*;
import edu.northeastern.cs5200.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;

public class UniversityDao {
	@Autowired
	private CourseRepository courserepository;
	@Autowired
	private EnrollmentRepository enrollmentrepository;
	@Autowired
	private FacultyRepository facultyrepository;
	@Autowired
	private PersonRepository personrepository;
	@Autowired
	private SectionRepository sectionrepository;
	@Autowired
	private StudentRepository studentrepository;
	
	
	public void truncateDatabase() {
		this.enrollmentrepository.deleteAll();
		this.sectionrepository.deleteAll();
		this.courserepository.deleteAll();
		this.studentrepository.deleteAll(); 
		this.facultyrepository.deleteAll(); 
		this.personrepository.deleteAll(); 
	}
	
	public Faculty createFaculty(Faculty faculty) {
		return this.facultyrepository.save(faculty);
	}
	
	public Student createStudent(Student student) {
		return this.studentrepository.save(student);
	}
	
	public Course createCourse(Course course) {
		return this.courserepository.save(course);
	}
	
	public Section createSection(Section section) {
		return this.sectionrepository.save(section);
	}
	
	public Section addSectionToCourse(Section section, Course course) {
		section.setCourse(course);
		sectionrepository.save(section);
		return section;
	}
	
	public Course setAuthorForCourse(Faculty faculty, Course course) {
		course.setProf(faculty);
		courserepository.save(course);
		return course;
	}
	
	public Boolean enrollStudentInSection(Student student, Section section) {
		if (section.getSeats() == 0) {
			return false;
		}
		else {
			Enrollment enrollment = new Enrollment(student, section);
			student.addEnrollments(enrollment);
			section.addEnrollments(enrollment);
			section.setSeats(section.getSeats()-1);
			enrollmentrepository.save(enrollment);
            sectionrepository.save(section);
            studentrepository.save(student);
            return true;
		}
	}
	
	public List<Person> findAllUsers(){
		return (List<Person>) personrepository.findAll();
	}

	public List<Faculty> findAllFaculty(){
		return (List<Faculty>) facultyrepository.findAll();
	}
	
	public List<Student> findAllStudents(){
		return (List<Student>) studentrepository.findAll();
	}
	
	public List<Course> findAllCourses(){
		return (List<Course>) courserepository.findAll();
	}
	
	public List<Section> findAllSections(){
		return (List<Section>) sectionrepository.findAll();
	}
	
	public List<Course> findCoursesForAuthor(Faculty faculty){
		return faculty.getProfCourses();
	}
	
	public List<Section> findSectionForCourse(Course course){
		return course.getSections();
	}
	
	public List<Student> findStudentsInSection(Section section){
		List<Student> students = new ArrayList<>();
		for (Enrollment enrollment : (List<Enrollment>) section.getEnrollments()) {
            students.add(enrollment.getStudent());
        }
		return students;
	}
	
	public List<Section> findSectionsForStudent(Student student){
		List<Section> sections = new ArrayList<>();
		for (Enrollment enrollment : (List<Enrollment>) student.getEnrollments()) {
            sections.add(enrollment.getSection());
        }
		return sections;
	}
	
	public List<Course> findCourseCorrespondingAuthor(Faculty faculty){
		return faculty.getProfCourses();
	}
	public List<Section> findSectionCorrespondingCourse(Course course){
		return course.getSections();
	}

	public Section findSectionByTitle(String title) {
		return sectionrepository.findSectionByTitle(title) ;
	}
}