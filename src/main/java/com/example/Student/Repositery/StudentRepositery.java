package com.example.Student.Repositery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Student.Model.Student;

@Repository
public interface StudentRepositery  extends JpaRepository<Student, Integer>{
	
	public Student findByname(String name);

}
