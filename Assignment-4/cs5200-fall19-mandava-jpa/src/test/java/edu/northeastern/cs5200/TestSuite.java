package edu.northeastern.cs5200;

import edu.northeastern.cs5200.Dao.*;
import edu.northeastern.cs5200.Entities.*;
import edu.northeastern.cs5200.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

public class TestSuite {
	@Autowired
    private UniversityDao universitydao;
	private Faculty alan, ada;
	private Course cs1234, cs2345, cs3456, cs4567;
	private Student alice, bob, charlie, dan, edward, frank, gregory;
	private Section s4321, s5432, s6543, s7654;
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

	@Test
	public void testTruncate() {
		universitydao.truncateDatabase();
	}

	@Test
	public void createFaculty() {
		Faculty alan = new Faculty("alan", "password", "Alan", "Turin", "123A", true);
        Faculty ada = new Faculty("ada", "password", "Ada", "Lovelace", "123B", true);
        universitydao.createFaculty(alan);
        universitydao.createFaculty(ada);
	}
	
	@Test
	public void createStudents() {
		Student alice = new Student("alice","password","Alice","Wonderland",2020,12000);
		Student bob = new Student("bob","password","Bob","Hope",2021,23000);
		Student charlie = new Student("charlie","password","Charlie","Brown",2019,21000);
		Student dan = new Student("dan","password","Dan","Craig",2019,0);
		Student edward = new Student("edward","password","Edward","Scissorhands",2022,11000);
		Student frank = new Student("frank","password","Frank","Herbert",2018,0);
		Student gregory = new Student("gregory","password","Gregory","Peck",2023,10000);
		universitydao.createStudent(alice);
		universitydao.createStudent(bob);
		universitydao.createStudent(charlie);
		universitydao.createStudent(dan);
		universitydao.createStudent(edward);
		universitydao.createStudent(frank);
		universitydao.createStudent(gregory);
	}
	
	@Test
	public void createCourses() {
		Course cs1234 = new Course("CS1234");
		Course cs2345 = new Course("CS2345");
		Course cs3456 = new Course("CS3456");
		Course cs4567 = new Course("CS4567");
		universitydao.createCourse(cs1234);
		universitydao.createCourse(cs2345);
		universitydao.createCourse(cs3456);
		universitydao.createCourse(cs4567);
		Faculty alan = facultyrepository.findPersonByUsername("alan");
		Faculty ada = facultyrepository.findPersonByUsername("ada");
		universitydao.setAuthorForCourse(alan, cs1234);
		universitydao.setAuthorForCourse(alan, cs2345);
		universitydao.setAuthorForCourse(ada, cs3456);
		universitydao.setAuthorForCourse(ada, cs4567);
	}

	@Test
	public void createSections() {
		Course cs1234 = courserepository.findCourseByLabel("CS1234");
        Course cs2345 = courserepository.findCourseByLabel("CS2345");
        Course cs3456 = courserepository.findCourseByLabel("CS3456");
        Section s4321 = new Section("SEC4321", 50);
        Section s5432 = new Section("SEC5432", 50);
        Section s6543 = new Section("SEC6543", 50);
        Section s7654 = new Section("SEC7654", 50);
        universitydao.createSection(s4321);
        universitydao.createSection(s5432);
        universitydao.createSection(s6543);
        universitydao.createSection(s4321);
        universitydao.addSectionToCourse(s4321, cs1234);
        universitydao.addSectionToCourse(s5432, cs2345);
        universitydao.addSectionToCourse(s6543, cs3456);
		universitydao.addSectionToCourse(s7654, cs3456);
	}
	
	@Test
	public void createEnrollment() {
		Student alice = studentrepository.findPersonByUsername("alice");
		Student bob = studentrepository.findPersonByUsername("bob");
		Student charlie = studentrepository.findPersonByUsername("charlie");
		Section s1 = sectionrepository.findSectionByTitle("SEC4321");
		Section s2 = sectionrepository.findSectionByTitle("SEC5432");
		Section s3 = sectionrepository.findSectionByTitle("SEC6543");
		universitydao.enrollStudentInSection(alice, s1);
		universitydao.enrollStudentInSection(alice, s2);
		universitydao.enrollStudentInSection(bob, s2);
		universitydao.enrollStudentInSection(charlie, s3);
	}	
	
	@Test
	public void testNumbers() {
		assertEquals(9, universitydao.findAllUsers().size());
		assertEquals(2, universitydao.findAllFaculty().size());
		assertEquals(universitydao.findAllStudents().size(), 7);
		assertEquals(universitydao.findAllCourses().size(), 4);
		assertEquals(universitydao.findAllSections().size(), 4);
		assertEquals(2, universitydao.findCourseCorrespondingAuthor(alan).size());
		assertEquals(2, universitydao.findCourseCorrespondingAuthor(ada).size());
		assertEquals(2, universitydao.findSectionForCourse(cs1234).size());
		assertEquals(1, universitydao.findSectionForCourse(cs2345).size());
		assertEquals(1, universitydao.findSectionForCourse(cs3456).size());
		assertEquals(0, universitydao.findSectionForCourse(cs4567).size());
		assertEquals(1, universitydao.findStudentsInSection(s4321).size());
		assertEquals(2, universitydao.findStudentsInSection(s5432).size());
		assertEquals(1, universitydao.findStudentsInSection(s6543).size());
		assertEquals(0, universitydao.findStudentsInSection(s7654).size());
		assertEquals(2, universitydao.findSectionsForStudent(alice).size());
		assertEquals(1, universitydao.findSectionsForStudent(bob).size());
		assertEquals(1, universitydao.findSectionsForStudent(charlie).size());
		assertEquals(0, universitydao.findSectionsForStudent(dan).size());
		assertEquals(0, universitydao.findSectionsForStudent(edward).size());
		assertEquals(0, universitydao.findSectionsForStudent(frank).size());
		assertEquals(0, universitydao.findSectionsForStudent(gregory).size());
		assertEquals(49, universitydao.findSectionByTitle(s4321.getTitle()).getSeats());
		assertEquals(48, universitydao.findSectionByTitle(s5432.getTitle()).getSeats());
		assertEquals(49, universitydao.findSectionByTitle(s6543.getTitle()).getSeats());
		assertEquals(50, universitydao.findSectionByTitle(s7654.getTitle()).getSeats());
	}
}