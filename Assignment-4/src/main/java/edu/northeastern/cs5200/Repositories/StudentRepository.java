package edu.northeastern.cs5200.Repositories;

import org.springframework.data.repository.CrudRepository;

import edu.northeastern.cs5200.Models.Student;

public interface StudentRepository extends CrudRepository<Student, Integer> {}