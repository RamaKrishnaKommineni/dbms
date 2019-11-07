package com.example.cs5200_fall2019_mandava_sowmika_jpa;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.cs5200_fall2019_mandava_sowmika_jpa.Dao.*;
import com.example.cs5200_fall2019_mandava_sowmika_jpa.models.*;
import com.example.cs5200_fall2019_mandava_sowmika_jpa.repositories.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestSuite {
	@Autowired 
	UniversityDao universitydao;
	@Autowired 
	FacultyRepository facultyrepository;
	@Autowired 
	CourseRepository courserepository;
	@Autowired 
	StudentRepository studentrepository;
	@Autowired 
	SectionRepository sectionrepository;
	
	private static boolean setUpComplete = false;
	
	@Before
	public void setUp() {
		if(!setUpComplete) {
			universitydao.truncateDatabase();
			universitydao.createFaculty(alan);
			universitydao.createFaculty(ada);
			universitydao.createStudent(alice);
			universitydao.createStudent(bob);
			universitydao.createStudent(charlie);
			universitydao.createStudent(dan);
			universitydao.createStudent(edward);
			universitydao.createStudent(frank);
			universitydao.createStudent(gregory);
			universitydao.createCourse(cs1234);
			universitydao.createCourse(cs2345);
			universitydao.createCourse(cs3456);
			universitydao.createCourse(cs4567);
			universitydao.createSection(sec4321);
			universitydao.createSection(sec5432);
			universitydao.createSection(sec6543);
			universitydao.createSection(sec7654);
			universitydao.setAuthorForCourse(alan, cs1234);
			universitydao.setAuthorForCourse(alan, cs2345);
			universitydao.setAuthorForCourse(ada, cs3456);
			universitydao.setAuthorForCourse(ada, cs4567);
			universitydao.addSectionToCourse(sec4321, cs1234);
			universitydao.addSectionToCourse(sec5432, cs1234);
			universitydao.addSectionToCourse(sec6543, cs2345);
			universitydao.addSectionToCourse(sec7654, cs3456);
			universitydao.enrollStudentInSection(alice, sec4321);
			universitydao.enrollStudentInSection(alice, sec5432);
			universitydao.enrollStudentInSection(bob, sec5432);
			universitydao.enrollStudentInSection(charlie, sec6543);
			setUpComplete = true;
		}
	}
    
    static Faculty alan = new Faculty("alan", "password", "Alan", "Turin", "123A", true);
    static Faculty ada = new Faculty("ada", "password", "Ada", "Lovelace", "123B", true);
    static Student alice = new Student("alice", "password", "Alice", "Wonderland", 2020, 12000);
    static Student bob = new Student("bob", "password", "Bob", "Hope", 2021, 23000);
    static Student charlie = new Student("charlie", "password", "Charlie", "Brown", 2019, 21000);
    static Student dan = new Student("dan", "password", "Dan", "Craig", 2019, 0);
    static Student edward = new Student("edward", "password", "Edward", "Scissorhands", 2022, 11000);
    static Student frank = new Student("frank", "password", "Fran", "Herbert", 2018, 0);
    static Student gregory = new Student("gregory", "password", "Gregory", "Peck", 2023, 10000);
    static Course cs1234 = new Course("CS1234");
    static Course cs2345 = new Course("CS2345");
    static Course cs3456 = new Course("CS3456");
    static Course cs4567 = new Course("CS4567");
    static Section sec4321 = new Section("SEC4321", 50);
    static Section sec5432 = new Section("SEC5432", 50);
    static Section sec6543 = new Section("SEC6543", 50);
    static Section sec7654 = new Section("SEC7654", 50);
    
	@Test
	public void testtotalNumberOfUsers() {
		List<Person> person = universitydao.findAllUsers();
		assertEquals(9,person.size());
	}
	
	@Test
	public void testtotalNumberOfFaculty() {
		List<Faculty> faculty = universitydao.findAllFaculty();
		assertEquals(2,faculty.size());
	}
	
	@Test
	public void testtotalNumberOfStudents() {
		List<Student> student = universitydao.findAllStudents();
		assertEquals(7,student.size());
	}
	
	@Test
	public void testtotalNumberOfCourses() {
		List<Course> courses = universitydao.findAllCourses();
		assertEquals(4,courses.size());
	}
	
	@Test
	public void testtotalNumberOfSections() {
		List<Section> sections = universitydao.findAllSections();
		assertEquals(4,sections.size());
	}
	
	@Test
	public void testtotalNumberOfCoursesAuthoredByAlan() {
		List<Course> alanCourses = universitydao.findCourseForAuthor(alan);
		assertEquals(2,alanCourses.size());
	}
	
	@Test
	public void testtotalNumberOfCoursesAuthoredByAda() {
		List<Course> adaCourses = universitydao.findCourseForAuthor(ada);
		assertEquals(2,adaCourses.size());
	}
	
	@Test
	public void testtotalNumberOfSectionsForCourseCS1234() {
		List<Section> cs1234Sections = universitydao.findSectionForCourse(cs1234);
		assertEquals(2,cs1234Sections.size());
	}
	
	@Test
	public void testtotalNumberOfSectionsForCourseCS2345() {
		List<Section> cs2345Sections = universitydao.findSectionForCourse(cs2345);
		assertEquals(1,cs2345Sections.size());
	}
	
	@Test
	public void testtotalNumberOfSectionsForCourseCS3456() {
		List<Section> cs3456Sections = universitydao.findSectionForCourse(cs3456);
		assertEquals(1,cs3456Sections.size());
	}
	
	@Test
	public void testtotalNumberOfSectionsForCourseCS4567() {
		List<Section> cs4567Sections = universitydao.findSectionForCourse(cs4567);
		assertEquals(0,cs4567Sections.size());
	}
	
	@Test
	public void testtotalNumberOfStudentsInSection4321() {
		List<Student> studentsInSec4321 = universitydao.findStudentsInSection(sec4321);
		assertEquals(1,studentsInSec4321.size());
	}
	
	@Test
	public void testtotalNumberOfStudentsInSection5432() {
		List<Student> studentsInSec5432 = universitydao.findStudentsInSection(sec5432);
		assertEquals(2,studentsInSec5432.size());
	}
	
	@Test
	public void testtotalNumberOfStudentsInSection6543() {
		List<Student> studentsInSec6543 = universitydao.findStudentsInSection(sec6543);
		assertEquals(1,studentsInSec6543.size());
	}
	
	@Test
	public void testtotalNumberOfStudentsInSection7654() {
		List<Student> studentsInSec7654 = universitydao.findStudentsInSection(sec7654);
		assertEquals(0,studentsInSec7654.size());
	}
	
	@Test
	public void testtotalNumberOfSectionsForStudentAlice() {
		List<Section> sectionsForAlice = universitydao.findSectionsForStudent(alice);
		assertEquals(2,sectionsForAlice.size());
	}
	
	@Test
	public void testtotalNumberOfSectionsForStudentBob() {
		List<Section> sectionsForBob = universitydao.findSectionsForStudent(bob);
		assertEquals(1,sectionsForBob.size());
	}
	
	@Test
	public void testtotalNumberOfSectionsForStudentCharlie() {
		List<Section> sectionsForCharlie = universitydao.findSectionsForStudent(charlie);
		assertEquals(1,sectionsForCharlie.size());
	}
	
	@Test
	public void testtotalNumberOfSectionsForStudentDan() {
		List<Section> sectionsForDan = universitydao.findSectionsForStudent(dan);
		assertEquals(0,sectionsForDan.size());
	}
	
	@Test
	public void testtotalNumberOfSectionsForStudentEdward() {
		List<Section> sectionsForEdward = universitydao.findSectionsForStudent(edward);
		assertEquals(0,sectionsForEdward.size());
	}
	@Test
	public void testtotalNumberOfSectionsForStudentFrank() {
		List<Section> sectionsForFrank = universitydao.findSectionsForStudent(frank);
		assertEquals(0,sectionsForFrank.size());
	}
	
	@Test
	public void testtotalNumberOfSectionsForStudentGregory() {
		List<Section> sectionsForGregory = universitydao.findSectionsForStudent(gregory);
		assertEquals(0,sectionsForGregory.size());
	}
	@Test
	public void testtotalNumberOfSeatsInSection4321() {
		Student alice = new Student("alice", "password", "Alice", "Wonderland", 2020, 12000); 
		Section sec4321 = new Section("SEC4321", 50);
		universitydao.enrollStudentInSection(alice, sec4321);
		assertEquals(49,sec4321.getSeats());
	}
	
	@Test
	public void testtotalNumberOfSeatsInSection5432() {
		Student alice = new Student("alice", "password", "Alice", "Wonderland", 2020,12000); 
		Student bob = new Student("bob", "password", "Bob", "Hope", 2021,23000);
		Section sec5432 = new Section("SEC5432", 50); 
	    universitydao.enrollStudentInSection(alice, sec5432);
		universitydao.enrollStudentInSection(bob, sec5432);
		assertEquals(48,sec5432.getSeats());
	}
	
	@Test
	public void testtotalNumberOfSeatsInSection6543() {
		Student charlie = new Student("charlie", "password", "Charlie", "Brown",2019, 21000); 
		Section sec6543 = new Section("SEC6543", 50);
		universitydao.enrollStudentInSection(charlie, sec6543);
		assertEquals(49,sec6543.getSeats());
	}
	
	@Test
	public void testtotalNumberOfSeatsInSection7654() {
		assertEquals(50,sec7654.getSeats());
	}

}
