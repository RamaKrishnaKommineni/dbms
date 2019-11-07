package com.example.cs5200_fall2019_mandava_sowmika_jpa.repositories;

import com.example.cs5200_fall2019_mandava_sowmika_jpa.models.*;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.*;
import org.springframework.data.repository.query.Param;

public interface EnrollmentRepository extends CrudRepository<Enrollment, Integer>{
	  @Query("SELECT enrollment FROM Enrollment enrollment WHERE " +
	  "enrollment.student.id = :studentid") public List<Enrollment>
	  findEnrollmentByStudent (@Param("studentid") int studentid);
	 
}