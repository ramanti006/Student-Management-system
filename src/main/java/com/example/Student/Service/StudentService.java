package com.example.Student.Service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.Student.Model.Student;

public abstract class StudentService {
	
	//for adding 1 student info in database
	public abstract Student addOne(Student stu);
	
	//for adding multiple student info in database as list
	public abstract List<Student> addMultiple(List<Student> stuli);
	
	
	//it will show each student record from table
	public abstract List<Student> showAll();
	
	//it will show one student record from table if present
	public abstract Student showOne(int id);
	
	////it will show student record from table.search by name
	public abstract Student showByName(String n);
	
	public abstract Student update(int id,Student stu);
	
	public abstract void deletedAll();
	
	public abstract boolean deleteById(int id);
	
	public abstract List<Student> showByDepartment(String n);
	
	public abstract List<Student> showByRoll(int a , int b);
	
	
    public abstract Page<Student> pagination(int pageNo ,int pageSize);
	
	public abstract List<Student> sorting(String id);
	
	


}
