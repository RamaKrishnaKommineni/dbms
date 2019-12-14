package edu.northeastern.cs5200.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import edu.northeastern.cs5200.Models.Course;
import edu.northeastern.cs5200.Models.Section;

public interface SectionRepository extends CrudRepository<Section, Integer> {
	@Query("SELECT section from Section section where section.course = :course")
	public List<Section> findSectionByCourse(@Param("course") Course course);
}