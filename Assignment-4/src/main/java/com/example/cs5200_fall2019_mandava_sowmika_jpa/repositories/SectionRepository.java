package com.example.cs5200_fall2019_mandava_sowmika_jpa.repositories;

import com.example.cs5200_fall2019_mandava_sowmika_jpa.models.*;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.*;
import org.springframework.data.repository.query.Param;

public interface SectionRepository extends CrudRepository<Section, Integer> {
	  @Query("SELECT section FROM Section section WHERE section.title=:title")
	  public Section findSectionByTitle(@Param("title") String title);
	 
}
