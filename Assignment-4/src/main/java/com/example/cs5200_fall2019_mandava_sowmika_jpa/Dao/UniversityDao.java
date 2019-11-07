package com.example.cs5200_fall2019_mandava_sowmika_jpa.Dao;

import com.example.cs5200_fall2019_mandava_sowmika_jpa.models.*;
import com.example.cs5200_fall2019_mandava_sowmika_jpa.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UniversityDao {
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
	 
	public void truncateDatabase(){ 
		enrollmentrepository.deleteAll();
		sectionrepository.deleteAll(); 
		courserepository.deleteAll();
		personrepository.deleteAll(); 
	}
  
	public Faculty createFaculty(Faculty faculty) { 
		return facultyrepository.save(faculty); 
	}
  
	public Student createStudent(Student student) { 
		return studentrepository.save(student); 
	}
  
	public Course createCourse(Course course) { 
		return courserepository.save(course); 
	}
  
	public Section createSection(Section section) { 
		return sectionrepository.save(section); 
	}
  
	public Section addSectionToCourse(Section section, Course course) {
		section.setCourse(course); 
		return sectionrepository.save(section); 
	}
  
	public Course setAuthorForCourse(Faculty faculty, Course course) {
		course.setAuthor(faculty); 
		return courserepository.save(course); 
	}
  
	public Boolean enrollStudentInSection(Student student, Section section) { 
		if(section.getSeats() == 0) { 
			return false; 
			} 
		else { 
			Enrollment enrollment = new Enrollment(student, section); 
			student.sectionEnrollments(enrollment);
			section.studentEnrollments(enrollment); 
			section.setSeats(section.getSeats() - 1);
			enrollmentrepository.save(enrollment); 
			sectionrepository.save(section);
			return true; 
			} 
		}
  
	public List<Person> findAllUsers() { 
		return (List<Person>)personrepository.findAll(); 
	}
  
	public List<Faculty> findAllFaculty() { 
		return (List<Faculty>)facultyrepository.findAll(); 
	}
  
	public List<Student> findAllStudents() { 
		return (List<Student>)studentrepository.findAll(); 
	}
  
	public List<Course> findAllCourses() { 
		return (List<Course>)courserepository.findAll(); 
	}
  
	public List<Section> findAllSections() { 
		return (List<Section>)sectionrepository.findAll(); 
	}
  
	public List<Course> findCourseForAuthor(Faculty faculty) { 
		return faculty.getAuthoredCourses(); 
	}
  
	public List<Section> findSectionForCourse(Course course) { 
		return course.getSections(); 
	}
  
	public List<Student> findStudentsInSection(Section section) { 
		List<Student>students = new ArrayList<>(); 
		List<Enrollment> enrollments =section.getEnrollments(); 
		for(Enrollment enroll : enrollments) {
			students.add(enroll.getStudent()); 
		} 
		return students; 
	}
  
	public List<Section> findSectionsForStudent(Student student) { 
		List<Section>sections = new ArrayList<>(); 
		List<Enrollment> enrollments = enrollmentrepository.findEnrollmentByStudent(student.getId()); 
		for(Enrollment enroll : enrollments) { 
			sections.add(enroll.getSection());
		}
		return sections; 
	} 
}
  
 