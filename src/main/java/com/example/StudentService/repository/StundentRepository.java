package com.example.StudentService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.StudentService.model.Student;

public interface StundentRepository extends JpaRepository<Student, Long> {

	public Student findByName(String name);

}
