package edu.northeastern.cs5200.Repositories;

import edu.northeastern.cs5200.Entities.Faculty;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.*;
import org.springframework.data.repository.query.Param;

public interface FacultyRepository extends CrudRepository<Faculty, Integer>{
	@Query
	("select * from Person where Person.username=:username")
    public Faculty findPersonByUsername(@Param("username") String username);
}


