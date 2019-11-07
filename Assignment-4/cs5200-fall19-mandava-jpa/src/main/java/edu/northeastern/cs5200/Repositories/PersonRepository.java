package edu.northeastern.cs5200.Repositories;

import edu.northeastern.cs5200.Entities.Person;
import org.springframework.data.repository.*;

public interface PersonRepository extends CrudRepository<Person, Integer>{
}
