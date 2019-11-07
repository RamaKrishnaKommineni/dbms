package com.example.cs5200_fall2019_mandava_sowmika_jpa.repositories;

import com.example.cs5200_fall2019_mandava_sowmika_jpa.models.*;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.*;
import org.springframework.data.repository.query.Param;

public interface StudentRepository extends CrudRepository<Student, Integer> {
	  @Query("SELECT person FROM Person person WHERE person.username=:username")
	  public Student findPersonByUsername(@Param("username") String username);
	 
}
