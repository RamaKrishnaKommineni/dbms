package edu.northeastern.cs5200;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import edu.northeastern.cs5200.Dao.*;
import edu.northeastern.cs5200.Models.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestSuite {
	@Autowired
	UniversityDao universitydao;
	
	@Autowired
	FindImplementation findimplementation;
	
	@Before
	public void setUp() {
		universitydao.truncateDatabase();
		Faculty alan = new Faculty("alan", "password", "Alna", "Turin", "123A",true);
		Faculty ada = new Faculty("ada", "password", "Ada", "Lovelace","123B", true);
		universitydao.createFaculties(alan); 
		universitydao.createFaculties(ada);
		Student alice = new Student("alice", "password", "Alice", "Wonderland",2020,12000);
		Student return_alice = universitydao.createStudents(alice);
		Student bob = new Student("bob", "password", "Bob", "Hope", 2021,23000);
		Student return_bob = universitydao.createStudents(bob);
		Student charlie = new Student("charlie", "password", "Charlie", "Brown", 2019,21000);
		Student return_charlie = universitydao.createStudents(charlie);
		Student dan = new Student("dan", "password", "Dan", "Craig", 2019,0); 
		Student return_dan = universitydao.createStudents(dan);
		Student edward = new Student("edward", "password", "Edward", "Scissorhands",2022,11000); 
		Student return_edward = universitydao.createStudents(edward);
		Student frank = new Student("frank", "password", "Frank", "Herbert", 2018,0);
		Student return_frank = universitydao.createStudents(frank);
		Student gregory = new Student("gregory", "password", "Gregory", "Peck", 2023,10000); 
		Student return_gregory = universitydao.createStudents(gregory);
		Course cs1234 = new Course("CS1234", alan);
		universitydao.createCourses(cs1234);
		Course cs2345 = new Course("CS2345", alan);
		universitydao.createCourses(cs2345);
		Course cs3456 = new Course("CS3456", ada);
		universitydao.createCourses(cs3456);
		Course csS4567 = new Course("CS4567", ada);
		universitydao.createCourses(csS4567);
		Section SEC4321 = new Section(50, "SEC4321", cs1234);
		universitydao.createSections(SEC4321);
		Section SEC5432 = new Section(50, "SEC5432", cs1234);
		universitydao.createSections(SEC5432);
		Section SEC6543 = new Section(50, "SEC6543", cs2345);
		universitydao.createSections(SEC6543);
		Section SEC7654 = new Section(50, "SEC7654", cs3456);
		universitydao.createSections(SEC7654);
		universitydao.enrollStudentInSection(alice,SEC4321);
		universitydao.enrollStudentInSection(alice,SEC5432);
		universitydao.enrollStudentInSection(bob,SEC5432);
		universitydao.enrollStudentInSection(charlie,SEC6543);		
	}	
	
	@Test
	public void validateUsers() {
		List<Person> personList = findimplementation.findAllUsers();
		assertEquals(personList.size(), 9);
	}
	
	@Test
	public void validateFaculties() {
		List<Faculty> facultyList = findimplementation.findAllFaculties();
		assertEquals(facultyList.size(), 2);
	}

	@Test
	public void validateStudents() {
		List<Student> studentList = findimplementation.findAllStudents();
		assertEquals(studentList.size(), 7);
	}
	
	@Test
	public void validateCourses() {
		List<Course> courseList = findimplementation.findAllCourses();
		assertEquals(courseList.size(), 4);
	}
	
	@Test
	public void validateSections() {
		List<Section> sectionList = findimplementation.findAllSections();
		assertEquals(sectionList.size(), 4);
	}
	
	@Test
	public void validateCourseAuthorship() {
		List<Faculty> facultyList = findimplementation.findAllFaculties();
		for(Faculty faculty : facultyList) {
			List<Course> courseList = findimplementation.findCoursesForAuthor(faculty);
			assertEquals(courseList.size(), 2);
		}  
	}
	
	@Test
	public void validateSectionsPerCourse() {
		List<Course> courseList = findimplementation.findAllCourses();
		for(Course course: courseList) {
			List<Section> sectionList = findimplementation.findSectionForCourse(course);
			if(course.getLabel().equalsIgnoreCase("cs1234")) {
				assertEquals(2, sectionList.size());
			}
			else if (course.getLabel().equalsIgnoreCase("cs2345") || course.getLabel().equalsIgnoreCase("cs3456")) {
				assertEquals(1, sectionList.size());
			}
			else {
				assertEquals(0, sectionList.size());
			}
		}
	}

	@Test
	public void validateSectionEnrollments() {
		List<Section> sectionList = findimplementation.findAllSections();
		for(Section student : sectionList) {
			List<Student> studentList = findimplementation.findStudentsInSection(student);
			if(student.getTitle().equalsIgnoreCase("SEC5432")) {
				assertEquals(studentList.size(), 2);
			}
			else if (student.getTitle().equalsIgnoreCase("SEC6543") || student.getTitle().equalsIgnoreCase("SEC4321")) {
				assertEquals(studentList.size(), 1);
			}
			else {
				assertEquals(studentList.size(), 0);
			}
		}
		
	}
	
    @Test
    public void validateStudentEnrollments() {
    	List<Student> studentList = findimplementation.findAllStudents();
    	for(Student student: studentList) {
    		List<Section> sectionList = findimplementation.findSectionsForStudent(student);
    		if (student.getUsername().equalsIgnoreCase("alice")) {
    			assertEquals(sectionList.size(), 2);
    		}
    		else if(student.getUsername().equalsIgnoreCase("bob")) {
    			assertEquals(sectionList.size(), 1);
    		}
    		else if(student.getUsername().equalsIgnoreCase("charlie")) {
    			assertEquals(sectionList.size(), 1);
    		}
    		else {
    			assertEquals(sectionList.size(), 0);
    		}
    	}
    }
    
    @Test
    public void validateSectionSeats() {
    	List<Section> sectionList = findimplementation.findAllSections();
    	for(Section section : sectionList) {
    		if(section.getTitle().equalsIgnoreCase("SEC5432")) {
    			assertEquals(section.getSeats(), 48);
    		}
    		else if(section.getTitle().equalsIgnoreCase("SEC6543") || section.getTitle().equalsIgnoreCase("SEC4321") ) {
    			assertEquals(section.getSeats(), 49);
    		}
    		else {
    			assertEquals(section.getSeats(), 50);
    		}
    	}
    }
}