package edu.northeastern.cs5200.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import edu.northeastern.cs5200.Models.Enrollment;
import edu.northeastern.cs5200.Models.Section;
import edu.northeastern.cs5200.Models.Student;

public interface EnrollmentRepository extends CrudRepository<Enrollment, Integer>{
	@Query("SELECT enrollment from Enrollment enrollment where enrollment.section =:section")
	public List<Enrollment> findStudentsInSection(@Param("section") Section section);
	
	@Query("SELECT enrollment from Enrollment enrollment where enrollment.student =:student")
	public List<Enrollment> findSectionsForStudent(@Param("student") Student student);
}