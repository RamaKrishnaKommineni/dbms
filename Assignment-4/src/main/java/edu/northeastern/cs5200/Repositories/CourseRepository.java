package edu.northeastern.cs5200.Repositories;

import java.util.List;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import edu.northeastern.cs5200.Models.Course;
import edu.northeastern.cs5200.Models.Faculty;

public interface CourseRepository extends CrudRepository<Course, Integer>{
	 @Query("select course from Course course where course.faculty = :faculty")
	public List<Course> findCourseByFaculty(@Param("faculty") Faculty faculty);
	
}