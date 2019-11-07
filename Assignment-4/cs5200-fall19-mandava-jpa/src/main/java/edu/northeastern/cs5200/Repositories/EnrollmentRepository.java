package edu.northeastern.cs5200.Repositories;

import edu.northeastern.cs5200.Entities.Enrollment;
import org.springframework.data.repository.*;

public interface EnrollmentRepository extends CrudRepository<Enrollment, Integer>{
}