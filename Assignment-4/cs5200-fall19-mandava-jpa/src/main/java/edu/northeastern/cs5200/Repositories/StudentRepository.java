package edu.northeastern.cs5200.Repositories;

import edu.northeastern.cs5200.Entities.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.*;
import org.springframework.data.repository.query.Param;

public interface StudentRepository extends CrudRepository<Student, Integer>{
	@Query
	("select * from Person where Person.username=:username")
    public Student findPersonByUsername(@Param("username") String username);
	
}

