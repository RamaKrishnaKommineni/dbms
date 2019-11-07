package edu.northeastern.cs5200.Repositories;

import edu.northeastern.cs5200.Entities.Course;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.*;
import org.springframework.data.repository.query.Param;

public interface CourseRepository extends CrudRepository<Course, Integer>{
	@Query
	("selct * from Course where Course.label=:label")
    public Course findCourseByLabel(@Param("label") String label);
}
