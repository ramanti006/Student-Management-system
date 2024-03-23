package com.example.Student.Repositery;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.Student.Model.Student;

@Repository
public interface StudentRepositery  extends JpaRepository<Student, Integer>{
	
	public Student findByname(String name);
	
    public List<Student> findAllByDepartment(String department);
	
	public List<Student> findByRollnoBetween(int a ,int b);
	
	
	@Query("select s from Student s WHERE s.department=:d")
	public List<Student> getAllByDepartment( @Param ("d") String department);
	
	//update email and phone number
	@Modifying
	@Query("update Student s set s.email =:e,s.rollno=:r where s.id=:i")
	public void updateStudentMail(@Param("e") String email,@Param("r") int rollno ,@Param("i")int id);


}
