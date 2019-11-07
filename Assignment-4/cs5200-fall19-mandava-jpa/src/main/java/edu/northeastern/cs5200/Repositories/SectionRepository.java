package edu.northeastern.cs5200.Repositories;

import edu.northeastern.cs5200.Entities.Section;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.*;
import org.springframework.data.repository.query.Param;

public interface SectionRepository extends CrudRepository<Section, Integer>{
	 @Query
	 ("select * from Section where Section.title=:title")
	 public Section findSectionByTitle(@Param("title") String title);
}