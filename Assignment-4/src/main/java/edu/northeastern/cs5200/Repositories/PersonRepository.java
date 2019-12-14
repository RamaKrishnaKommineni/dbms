package edu.northeastern.cs5200.Repositories;

import org.springframework.data.repository.CrudRepository;

import edu.northeastern.cs5200.Models.Person;

public interface PersonRepository extends CrudRepository<Person, Integer> {}