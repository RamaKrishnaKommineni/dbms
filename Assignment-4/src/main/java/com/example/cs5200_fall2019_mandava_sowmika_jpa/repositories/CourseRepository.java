package com.example.cs5200_fall2019_mandava_sowmika_jpa.repositories;

import com.example.cs5200_fall2019_mandava_sowmika_jpa.models.*;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.*;
import org.springframework.data.repository.query.Param;

public interface CourseRepository extends CrudRepository<Course, Integer> {
	  @Query("SELECT course FROM Course course WHERE course.label=:label") public
	  Course findCourseByLabel(@Param("label") String label);
	  
	  @Query("SELECT course FROM Course course WHERE course.author=:author") public
	  List<Course> findCourseForAuthor (@Param("author") Faculty faculty);
	 
}
